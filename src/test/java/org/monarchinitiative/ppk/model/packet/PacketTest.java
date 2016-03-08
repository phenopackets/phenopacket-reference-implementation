package org.monarchinitiative.ppk.model.packet;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.monarchinitiative.ppk.io.JsonYamlConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PacketTest {

	@Test
	public void test() throws IOException {
		String id = "test-id";
		String title = "test-title";
		Packet pk = new Packet.Builder().id(id).title(title).build();
		assertEquals(id, pk.getId());
		assertEquals(title, pk.getTitle());
		
		ObjectMapper m = new ObjectMapper();
		String s = m.writerWithDefaultPrettyPrinter().writeValueAsString(pk);
		System.out.println(s);
		
		System.out.println(JsonYamlConverter.renderYAML(pk));

		
	}

}
