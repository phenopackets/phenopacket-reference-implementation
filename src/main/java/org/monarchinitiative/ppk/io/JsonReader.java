package org.monarchinitiative.ppk.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.monarchinitiative.ppk.PhenoPacket;

import java.io.File;
import java.io.IOException;

public class JsonReader {
	
	public static PhenoPacket readFile(String fileName) throws IOException {
		return readFile(new File(fileName));
	}
	public static PhenoPacket readFile(File file) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(file, PhenoPacket.class);
	}

}
