package org.monarchinitiative.ppk.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.monarchinitiative.ppk.PhenoPacket;
import org.monarchinitiative.ppk.model.entity.Entity;

import java.io.File;
import java.io.IOException;

public class YamlReader {
	
	public static PhenoPacket readFile(String fileName) throws IOException {
		return readFile(new File(fileName));
	}
	public static PhenoPacket readFile(File file) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
		objectMapper.addMixIn(Entity.class, EntityMixin.class);
//		objectMapper.addMixIn(Condition.class, ConditionMixin.class);
		return objectMapper.readValue(file, PhenoPacket.class);
	}

}
