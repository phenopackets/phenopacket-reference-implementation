package org.monarchinitiative.ppk;

import org.junit.Test;
import org.monarchinitiative.ppk.io.JsonGenerator;
import org.monarchinitiative.ppk.io.YamlGenerator;
import org.monarchinitiative.ppk.io.YamlReader;
import org.monarchinitiative.ppk.model.association.PhenotypeAssociation;
import org.monarchinitiative.ppk.model.condition.Phenotype;
import org.monarchinitiative.ppk.model.entity.Person;
import org.monarchinitiative.ppk.model.entity.Variant;
import org.monarchinitiative.ppk.model.meta.Evidence;
import org.monarchinitiative.ppk.model.ontology.OntologyClass;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

	@Test
	public void testFromFile() throws IOException {
		PhenoPacket packet = YamlReader.readFile(Paths.get("src/test/resources/condition/person-phenotype-example2.yaml").toFile());
		System.out.println(YamlGenerator.render(packet));
		List<Person> persons = packet.getPersons();
		assertTrue(persons.size() == 3);
		// order is preserved
		assertTrue(persons.get(0).getSex().equals("M"));
		System.out.println(persons.get(0).getId());
		//assertEquals("#1", persons.get(0).getId());  TODO - FIXME
	}

	@Test
	public void testCanCapturePatientPhenotypesWithVariantInfo() throws IOException {

		Person person = new Person();
		person.setId("person#1");
		person.setLabel("Joe Bloggs");

		Phenotype phenotype = new Phenotype();
		phenotype.setTypes(Collections.singletonList(new OntologyClass.Builder("HP:0200055").setLabel("Small hands").build()));

		PhenotypeAssociation patientPhenotypeAssociation = new PhenotypeAssociation.Builder(phenotype)
																						.setEntity(person)
				.addEvidence(new Evidence())
				.addEvidence(new Evidence())
																						.build();
		Variant personVariant = new Variant();
		personVariant.setId("variant#1");
		personVariant.setLabel("c.1234A>G");
		personVariant.setDescriptionHGVS("c.1234A>G");

		//How can I associate the variant with the person? If there are multiple people in the phenopacket, must they all have the same variant?
		//In this case variants and phenotypes belong to the entity (the person), the person is described in the phenopacket.

		String id = "test-id";
		String title = "Patient X phenotypes and potentially causative variant";
		PhenoPacket pk = new PhenoPacket.Builder().id(id).title(title).build();
		pk.addEntity(person);
		pk.setPersons(Collections.singletonList(person));
		pk.addPhenotypeAssociation(patientPhenotypeAssociation);
		pk.addEntity(personVariant);
		pk.setVariants(Collections.singletonList(personVariant));

		assertEquals(id, pk.getId());
		assertEquals(title, pk.getTitle());

		System.out.println(JsonGenerator.render(pk));
		System.out.println(YamlGenerator.render(pk));

	}
}
