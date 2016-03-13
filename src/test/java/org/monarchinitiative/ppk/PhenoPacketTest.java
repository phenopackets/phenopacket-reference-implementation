package org.monarchinitiative.ppk;

import org.junit.Test;
import org.monarchinitiative.ppk.io.JsonGenerator;
import org.monarchinitiative.ppk.io.YamlGenerator;
import org.monarchinitiative.ppk.io.YamlReader;
import org.monarchinitiative.ppk.model.organism.Person;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class PhenoPacketTest {

	@Test
	public void test() throws IOException {
		String id = "test-id";
		String title = "test-title";
		PhenoPacket pk = new PhenoPacket.Builder().id(id).title(title).build();
		assertEquals(id, pk.getId());
		assertEquals(title, pk.getTitle());

		System.out.println(JsonGenerator.render(pk));
		System.out.println(YamlGenerator.render(pk));

	}
	
	//@Test
	public void testFromFile() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		PhenoPacket packet = YamlReader.readFile(classLoader.getResource("condition/person-phenotype-example2.yaml").getFile());
		System.out.println(YamlGenerator.render(packet));
		List<Person> persons = packet.getPersons();
		assertTrue(persons.size() == 3);
		// order is preserved
		assertTrue(persons.get(0).getSex().equals("M"));
		System.out.println(persons.get(0).getId());
		//assertEquals("#1", persons.get(0).getId());  TODO - FIXME
	}


}
