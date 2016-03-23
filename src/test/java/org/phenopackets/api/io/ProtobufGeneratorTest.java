package org.phenopackets.api.io;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.protobuf.ProtobufMapper;
import com.fasterxml.jackson.dataformat.protobuf.schema.ProtobufSchema;
import com.fasterxml.jackson.dataformat.protobuf.schemagen.ProtobufSchemaGenerator;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.phenopackets.api.PhenoPacket;
import org.phenopackets.api.model.condition.Condition;

public class ProtobufGeneratorTest extends AbstractSchemaTest {

	@Test
	public void makeSchemaTest() throws IOException  {
		
		makeSchema(PhenoPacket.class, "proto/phenopacket.proto");
		makeSchema(Condition.class, "proto/condition.proto");
	}
	
	private void makeSchema(Class c, String fn) throws IOException  {
		
		ObjectMapper mapper = new ProtobufMapper();
		//TODO: make these compile and pass!
		ProtobufSchemaGenerator gen = new ProtobufSchemaGenerator();
		mapper.acceptJsonFormatVisitor(c, gen);
		ProtobufSchema schemaWrapper = gen.getGeneratedSchema();
		String s = schemaWrapper.getSource().toString();
		writeSchema(fn, s);
	
	}
	

	
}
