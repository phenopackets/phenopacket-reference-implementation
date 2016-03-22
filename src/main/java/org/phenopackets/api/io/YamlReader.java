package org.phenopackets.api.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.phenopackets.api.PhenoPacket;

import java.io.File;
import java.io.IOException;

public class YamlReader {
	
	public static PhenoPacket readFile(String fileName) throws IOException {
		return readFile(new File(fileName));
	}
	public static PhenoPacket readFile(File file) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
		return objectMapper.readValue(file, PhenoPacket.class);
	}

}
