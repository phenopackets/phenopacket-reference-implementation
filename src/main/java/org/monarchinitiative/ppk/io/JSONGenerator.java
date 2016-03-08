package org.monarchinitiative.ppk.io;

import org.monarchinitiative.ppk.model.condition.Phenotype;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONGenerator {
	
	public static String render(Object obj) throws JsonProcessingException {
		
		ObjectMapper m = new ObjectMapper();
		String s = m.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		return s;
	}

}
