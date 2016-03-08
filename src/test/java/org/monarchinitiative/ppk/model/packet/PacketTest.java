package org.monarchinitiative.ppk.model.packet;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PacketTest {

	@Test
	public void test() throws JsonProcessingException {
		String id = "test-id";
		String title = "test-title";
		Packet pk = new Packet.Builder().id(id).title(title).build();
		assertEquals(id, pk.getId());
		assertEquals(title, pk.getTitle());
		
		ObjectMapper m = new ObjectMapper();
		String s = m.writerWithDefaultPrettyPrinter().writeValueAsString(pk);
		System.out.println(s);

		
	}

}
