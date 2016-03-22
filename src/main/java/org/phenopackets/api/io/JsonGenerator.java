package org.phenopackets.api.io;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * @author Jules Jacobsen <jules.jacobsen@sanger.ac.uk>
 */
public class JsonGenerator {

    public static String render(Object obj) throws JsonProcessingException {
        return prettyJsonString(obj);
    }

    private static String prettyJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
        return writer.writeValueAsString(obj);
    }
}
