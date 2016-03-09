package org.monarchinitiative.ppk.io;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonGenerator {

	public static String render(Object obj) throws JsonProcessingException {
		return prettyJsonString(obj);
	}

	private static String prettyJsonString(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
		return writer.writeValueAsString(obj);
	}
}
