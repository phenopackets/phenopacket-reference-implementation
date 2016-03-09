package org.monarchinitiative.ppk.model.packet;

import org.junit.Test;
import org.monarchinitiative.ppk.io.JsonGenerator;
import org.monarchinitiative.ppk.io.JsonYamlConverter;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PacketTest {

	@Test
	public void test() throws IOException {
		String id = "test-id";
		String title = "test-title";
		Packet pk = new Packet.Builder().id(id).title(title).build();
		assertEquals(id, pk.getId());
		assertEquals(title, pk.getTitle());

		System.out.println(JsonGenerator.render(pk));
		System.out.println(JsonYamlConverter.renderYaml(pk));

	}

}
