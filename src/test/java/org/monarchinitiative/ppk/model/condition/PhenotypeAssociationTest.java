package org.monarchinitiative.ppk.model.condition;

import org.junit.Test;
import org.monarchinitiative.ppk.PhenoPacket;
import org.monarchinitiative.ppk.io.JsonGenerator;
import org.monarchinitiative.ppk.io.YamlGenerator;
import org.monarchinitiative.ppk.io.YamlReader;
import org.monarchinitiative.ppk.model.organism.Person;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class PhenotypeAssociationTest {

	@Test
	public void test() throws IOException {
		Phenotype.Builder pb = new Phenotype.Builder();
		pb.addType("X:1").description("test description");

		Phenotype p = pb.build();
		System.out.println("DESC=" + p.getDescription());
		System.out.println("TYPES=" + p.getTypes());

		PhenotypeAssociation pa = new PhenotypeAssociation();
		pa.setEntity("E:1");
		pa.setPhenotype(p);
		
		System.out.println(JsonGenerator.render(pa));
		System.out.println(YamlGenerator.render(pa));

	}
	
	/**
	 *
	 * @throws IOException
	 */
	@Test
	public void testFromFile() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		PhenoPacket packet = YamlReader.readFile(classLoader.getResource("condition/person-phenotype-example1.yaml").getFile());
		System.out.println(YamlGenerator.render(packet));
		List<Person> persons = packet.getPersons();
		assertTrue(persons.size() == 3);
		// order should be preserved
		assertTrue(persons.get(0).getSex().equals("M"));
		System.out.println(persons.get(0).getId());
//		assertEquals("#1", persons.get(0).getId());  //TODO - FIXME
	}


}
