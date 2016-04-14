package org.phenopackets.api.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.phenopackets.api.PhenoPacket;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {

	public static PhenoPacket readFile(String fileName) throws IOException {
		return readFile(new File(fileName));
	}

	public static PhenoPacket readFile(File file) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(file, PhenoPacket.class);
	}

	public static PhenoPacket readInputStream(InputStream stream) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(stream, PhenoPacket.class);
	}
	
}
