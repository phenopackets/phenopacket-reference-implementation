package org.phenopackets.api.io;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;

import org.junit.Test;
import org.phenopackets.api.PhenoPacket;
import org.phenopackets.api.model.association.Association;
import org.phenopackets.api.model.condition.Condition;
import org.phenopackets.api.model.condition.Phenotype;
import org.phenopackets.api.model.ontology.OntologyClass;

public class SchemaGeneratorTest extends AbstractSchemaTest {

	@Test
	public void makeSchemaTest() throws IOException {
		makeSchema(PhenoPacket.class, "json/phenopacket-schema.json");
		makeSchema(Condition.class, "json/condition-schema.json");
		makeSchema(Association.class, "json/association-schema.json");
		makeSchema(OntologyClass.class, "json/ontology-class-schema.json");
	}

	private void makeSchema(Class c, String fn) throws IOException {

		ObjectMapper m = new ObjectMapper();
		m.setFilterProvider(new SimpleFilterProvider().addFilter(
				"PhenoPacketClass", SimpleBeanPropertyFilter.serializeAll()));
		SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
		m.acceptJsonFormatVisitor(m.constructType(c), visitor);
		JsonSchema jsonSchema = visitor.finalSchema();
		String s = m.writerWithDefaultPrettyPrinter().writeValueAsString(
				jsonSchema);
		System.out.println(s);
		writeSchema(fn, s);
	}

}
