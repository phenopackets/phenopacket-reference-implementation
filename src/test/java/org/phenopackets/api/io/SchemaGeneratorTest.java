package org.phenopackets.api.io;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import org.junit.Test;
import org.phenopackets.api.PhenoPacket;
import org.phenopackets.api.model.condition.Phenotype;
import org.phenopackets.api.model.ontology.OntologyClass;

public class SchemaGeneratorTest {

	@Test
	public void makeSchemaTest() throws JsonProcessingException {
		
		makeSchema(PhenoPacket.class);
	}
	
	private void makeSchema(Class c) throws JsonProcessingException {
		
		ObjectMapper m = new ObjectMapper();
		SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
		m.acceptJsonFormatVisitor(m.constructType(c), visitor);
		JsonSchema jsonSchema = visitor.finalSchema();
		String s = m.writerWithDefaultPrettyPrinter().writeValueAsString(jsonSchema);
		System.out.println(s);
	}

	@Test
	public void makePhenotypeSchemaTest() throws JsonProcessingException {
		
		makeSchema(Phenotype.class);
	}

	@Test
	public void makeOntologySchemaTest() throws JsonProcessingException {
		
		makeSchema(OntologyClass.class);
	}

}
