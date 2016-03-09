package org.monarchinitiative.ppk.io;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

public class JsonYamlConverter {
	
	public static String renderYaml(Object obj) throws IOException {

		// First make JSON representation, using data bindings
		String json = JsonGenerator.render(obj);
		
		// now read, bypassing data bindings
		ObjectMapper m = new ObjectMapper();
		Map<String, Object> map = m.readValue(json, new TypeReference<Map<String, String>>(){});
		//JsonNode genericObj = m.readTree(s);
		
		// now write the generic obj as yaml
		Yaml yaml = makeYaml();
		return yaml.dump(map);
	}

	public Object renderJson(Object obj) throws JsonProcessingException {
		Yaml yaml = makeYaml();
		String json = JsonGenerator.render(obj);
		return yaml.parse(new StringReader(json));
	}

	private static Yaml makeYaml() {
		Representer representer = new Representer();
		representer.getPropertyUtils().setSkipMissingProperties(true);
		Constructor constructor = new Constructor();
		return new Yaml(constructor, representer);
	}

}
