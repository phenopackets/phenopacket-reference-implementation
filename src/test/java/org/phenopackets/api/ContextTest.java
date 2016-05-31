package org.phenopackets.api;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.Test;
import org.phenopackets.api.io.JsonGenerator;
import org.phenopackets.api.io.JsonReader;
import org.phenopackets.api.io.YamlReader;
import org.phenopackets.api.util.ContextUtil;

import com.github.jsonldjava.core.Context;
import com.github.jsonldjava.core.JsonLdError;

public class ContextTest {

	@Test
	public void testRetrieveDefaultContext() throws JsonLdError {
		assertThat(ContextUtil.getDefaultContext(), notNullValue());
	}

	@Test
	public void testMissingContext() throws IOException, JsonLdError {
		PhenoPacket packet = YamlReader
				.readFile("src/test/resources/context/phenopacket-without-context.yaml");
		assertNull("Phenopacket with no context should return null.",
				packet.getContext());
		assertThat(ContextUtil.getJSONLDContext(packet),
				equalTo(ContextUtil.getDefaultContext()));
	}

	@Test
	public void testRoundtripOfContext() throws IOException {
		PhenoPacket originalPacket = YamlReader
				.readFile("src/test/resources/context/phenopacket-with-context.yaml");
		String jsonString = JsonGenerator.render(originalPacket);
		InputStream stream = new ByteArrayInputStream(
				jsonString.getBytes(StandardCharsets.UTF_8));
		PhenoPacket newPacket = JsonReader.readInputStream(stream);
		assertThat(originalPacket.getContext(), instanceOf(List.class));
		assertThat(newPacket.getContext(), equalTo(originalPacket.getContext()));
	}

	@Test
	public void testIdentiferExpansion() throws IOException, JsonLdError {
		PhenoPacket packet = YamlReader
				.readFile("src/test/resources/context/phenopacket-with-context.yaml");
		Context context = ContextUtil.getJSONLDContext(packet);
		String person1 = ContextUtil.expandIdentifierAsValue(packet
				.getEnvironmentAssociations().get(0).getEntityId(), context);
		assertThat(person1,
				equalTo("http://myinstitution.example.org/person#1"));
		String textNotInDocument = "IdentiferNotInDocument";
		assertThat(
				ContextUtil.expandIdentifierAsValue(textNotInDocument, context),
				equalTo("http://myinstitution.example.org/" + textNotInDocument));
		assertThat(ContextUtil.expandIdentifierAsValue("rdfs:label", context),
				equalTo("http://www.w3.org/2000/01/rdf-schema#label"));
		assertThat(ContextUtil.expandIdentifierAsValue("label", context),
				not("http://www.w3.org/2000/01/rdf-schema#label"));
		assertThat(ContextUtil.expandIdentifierAsValue("label", context),
				equalTo("http://myinstitution.example.org/label"));
		assertThat(
				ContextUtil.expandIdentifierAsPropertyOrType("label", context),
				equalTo("http://www.w3.org/2000/01/rdf-schema#label"));
		String curieNotInDocument = "dim:sum";
		assertThat(ContextUtil.expandIdentifierAsValue(curieNotInDocument,
				context), equalTo(curieNotInDocument));
	}

	@Test
	public void testIdentiferCompaction() throws IOException, JsonLdError {
		PhenoPacket packet = YamlReader
				.readFile("src/test/resources/context/phenopacket-with-context.yaml");
		Context context = ContextUtil.getJSONLDContext(packet);
		assertThat(ContextUtil.compactIdentifierAsValue(
				"http://myinstitution.example.org/person#1", context),
				equalTo("person#1"));
		String textNotInDocument = "IdentiferNotInDocument";
		assertThat(ContextUtil.compactIdentifierAsValue(textNotInDocument,
				context), equalTo(textNotInDocument));
		assertThat(ContextUtil.compactIdentifierAsValue("rdfs:label", context),
				equalTo("rdfs:label"));
		assertThat(ContextUtil.compactIdentifierAsPropertyOrType(
				"http://myinstitution.example.org/person#1", context),
				not("person#1"));
	}

}
