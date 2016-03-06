package org.monarchinitiative.ppk.io;

import org.junit.Test;
import org.monarchinitiative.ppk.model.packet.Packet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;

public class SchemaGeneratorTest {

	@Test
	public void makeSchemaTest() throws JsonProcessingException {
		
		ObjectMapper m = new ObjectMapper();
		SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
		m.acceptJsonFormatVisitor(m.constructType(Packet.class), visitor);
		JsonSchema jsonSchema = visitor.finalSchema();
		String s = m.writerWithDefaultPrettyPrinter().writeValueAsString(jsonSchema);
		System.out.println(s);
	}

}
