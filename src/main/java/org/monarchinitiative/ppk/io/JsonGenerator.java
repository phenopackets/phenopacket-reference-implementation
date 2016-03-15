package org.monarchinitiative.ppk.io;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.monarchinitiative.ppk.model.condition.Condition;
import org.monarchinitiative.ppk.model.entity.Entity;

/**
 * @author Jules Jacobsen <jules.jacobsen@sanger.ac.uk>
 */
public class JsonGenerator {

    public static String render(Object obj) throws JsonProcessingException {
        return prettyJsonString(obj);
    }

    private static String prettyJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixIn(Entity.class, EntityMixin.class);
        mapper.addMixIn(Condition.class, ConditionMixin.class);
        ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
        return writer.writeValueAsString(obj);
    }
}
