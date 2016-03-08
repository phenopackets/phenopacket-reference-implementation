package org.monarchinitiative.ppk.model.packet;

import static org.junit.Assert.*;

import org.junit.Test;

public class PacketTest {

	@Test
	public void test() {
		String id = "test-id";
		String title = "test-title";
		Packet pk = new Packet.Builder().id(id).title(title).build();
		assertEquals(id, pk.getId());
		assertEquals(title, pk.getTitle());
		
	}

}
