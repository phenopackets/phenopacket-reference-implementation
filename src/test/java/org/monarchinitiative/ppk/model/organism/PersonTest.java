package org.monarchinitiative.ppk.model.organism;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.monarchinitiative.ppk.PhenoPacket;
import org.monarchinitiative.ppk.io.YamlReader;

public class PersonTest {

	/**
	 * TODO: is this the best strategy for testing? See https://github.com/phenopackets/phenopacket-reference-implementation/issues/14
	 * 
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		PhenoPacket packet = YamlReader.readFile(classLoader.getResource("organism/persons-example1.yaml").getFile());
		
		List<Person> persons = packet.getPersons();
		assertTrue(persons.size() == 3);
		// order is preserved
		assertTrue(persons.get(0).getSex().equals("M"));
		assertTrue(persons.get(0).getId().equals("#1"));
	}

}
