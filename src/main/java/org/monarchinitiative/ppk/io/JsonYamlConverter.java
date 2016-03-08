package org.monarchinitiative.ppk.io;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonYamlConverter {
	
	public static String renderYAML(Object obj) throws IOException {

		// First make JSON representation, using data bindings
		ObjectMapper m = new ObjectMapper();
		Object value;
		String s = m.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		
		// now read, bypassing data bindings
		Map<String, Object> map = new HashMap<String, Object>();
		m = new ObjectMapper();
		map = m.readValue(s, new TypeReference<Map<String, String>>(){});
		//JsonNode genericObj = m.readTree(s);
		
		// now write the generic obj as yaml
		Representer representer = new Representer();
		representer.getPropertyUtils().setSkipMissingProperties(true);
		Constructor constructor = new Constructor();
		Yaml yaml = new Yaml(constructor, representer);
		return yaml.dump(map);
	}
	
	public Object renderJSON(Object obj) throws JsonProcessingException {
		ObjectMapper m = new ObjectMapper();
		Object value;
		String s = m.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		Representer representer = new Representer();
		representer.getPropertyUtils().setSkipMissingProperties(true);
		Constructor constructor = new Constructor();
		Yaml yaml = new Yaml(constructor, representer);
		return yaml.parse(new StringReader(s));		
	}

}
