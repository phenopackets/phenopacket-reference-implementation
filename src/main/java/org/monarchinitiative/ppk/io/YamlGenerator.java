package org.monarchinitiative.ppk.io;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.monarchinitiative.ppk.model.condition.Condition;
import org.monarchinitiative.ppk.model.entity.Entity;

public class YamlGenerator {
	
	public static String render(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.addMixIn(Entity.class, EntityMixin.class);
        mapper.addMixIn(Condition.class, ConditionMixin.class);
		ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
		return writer.writeValueAsString(obj);
	}

}
