package org.phenopackets.api.io;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.junit.Test;
import org.phenopackets.api.PhenoPacket;
import org.phenopackets.api.util.ContextUtil;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.github.jsonldjava.core.JsonLdError;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

public class RDFTest {

	@Test
	public void testWriteRDF() throws IOException, JsonLdError {
		PhenoPacket packet = YamlReader
				.readFile("src/test/resources/context/phenopacket-with-context.yaml");
		Model model = RDFGenerator.toRDF(packet, null);
		assertThat(
				model.contains(
						ResourceFactory
								.createResource("http://myinstitution.example.org/person#1"),
						ResourceFactory
								.createProperty("http://www.w3.org/2000/01/rdf-schema#label"),
						ResourceFactory.createPlainLiteral("Joe Bloggs")),
				is(true));
		assertThat(
				model.contains(
						ResourceFactory
								.createResource("http://purl.obolibrary.org/obo/EFO_0004318"),
						ResourceFactory
								.createProperty("http://www.w3.org/2000/01/rdf-schema#label"),
						ResourceFactory.createPlainLiteral("smoking behavior")),
				is(true));
		PhenoPacket packet2 = YamlReader
				.readFile("src/test/resources/context/phenopacket-without-context.yaml");
		Model model2 = RDFGenerator.toRDF(packet2, null);
		assertThat(
				model2.listResourcesWithProperty(
						ResourceFactory
								.createProperty("http://www.w3.org/2000/01/rdf-schema#label"),
						ResourceFactory.createPlainLiteral("Joe Bloggs"))
						.next().getURI()
						.startsWith("http://phenopackets.org/local/"), is(true));
		PhenoPacket packet3 = YamlReader
				.readFile("src/test/resources/context/phenopacket-without-context.yaml");
		Model model3 = RDFGenerator.toRDF(packet3,
				"http://example.org/testbase/");
		assertThat(
				model3.contains(
						ResourceFactory
								.createResource("http://example.org/testbase/person#1"),
						ResourceFactory
								.createProperty("http://www.w3.org/2000/01/rdf-schema#label"),
						ResourceFactory.createPlainLiteral("Joe Bloggs")),
				is(true));

	}

	@Test
	public void testReadRDF() throws IOException, JsonLdError {
		// FIXME this does not really test the output
		PhenoPacket packet = YamlReader
				.readFile("src/test/resources/context/phenopacket-with-context.yaml");
		Model model = RDFGenerator.toRDF(packet, null);
		String packetID = packet.getId();
		PhenoPacket newPacket = RDFReader.readModel(model,
				ContextUtil.expandIdentifierAsValue(packetID, packet));
		ObjectMapper m = new ObjectMapper();
		m.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		m.setFilterProvider(new SimpleFilterProvider().addFilter(
				"PhenoPacketClass", SimpleBeanPropertyFilter.serializeAll()));
		ObjectWriter writer = m.writerWithDefaultPrettyPrinter();
		System.out.println(writer.writeValueAsString(newPacket));
	}

}
