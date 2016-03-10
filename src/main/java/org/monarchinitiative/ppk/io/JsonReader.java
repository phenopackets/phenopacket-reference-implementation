package org.monarchinitiative.ppk.io;

import java.io.File;
import java.io.IOException;

import org.monarchinitiative.ppk.PhenoPacket;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class JsonReader {
	
	public static PhenoPacket readFile(String fileName) throws IOException {
		return readFile(new File(fileName));
	}
	public static PhenoPacket readFile(File file) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(file, PhenoPacket.class);
	}

}
