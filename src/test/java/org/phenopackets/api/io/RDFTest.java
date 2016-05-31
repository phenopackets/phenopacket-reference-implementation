package org.phenopackets.api.io;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.riot.Lang;
import org.junit.Test;
import org.phenopackets.api.PhenoPacket;

public class RDFTest {

	@Test
	public void testWriteRDF() throws IOException {
		PhenoPacket packet = YamlReader
				.readFile("src/test/resources/context/phenopacket-with-context.yaml");
		System.out.println(RDFGenerator.render(packet, Lang.TURTLE));
		Model model = RDFGenerator.toRDF(packet);
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
	}

}
