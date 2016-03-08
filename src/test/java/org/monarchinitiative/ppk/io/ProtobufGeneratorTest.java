package org.monarchinitiative.ppk.io;

import org.junit.Test;
import org.monarchinitiative.ppk.model.packet.Packet;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.protobuf.ProtobufMapper;
import com.fasterxml.jackson.dataformat.protobuf.schema.ProtobufSchema;
import com.fasterxml.jackson.dataformat.protobuf.schemagen.ProtobufSchemaGenerator;

public class ProtobufGeneratorTest {

	@Test
	public void makeSchemaTest() throws JsonMappingException  {
		
		makeSchema(Packet.class);
	}
	
	public void makeSchema(Class c) throws JsonMappingException  {
		
		ObjectMapper mapper = new ProtobufMapper();
		//TODO: make these compile and pass!
		ProtobufSchemaGenerator gen = new ProtobufSchemaGenerator();
		mapper.acceptJsonFormatVisitor(c, gen);
		ProtobufSchema schemaWrapper = gen.getGeneratedSchema();
		System.out.println(schemaWrapper.getSource().toString());
	
	}

	
}
