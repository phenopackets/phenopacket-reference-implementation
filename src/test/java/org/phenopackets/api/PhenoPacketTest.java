package org.phenopackets.api;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.phenopackets.api.io.JsonGenerator;
import org.phenopackets.api.io.YamlGenerator;
import org.phenopackets.api.io.YamlReader;
import org.phenopackets.api.model.association.PhenotypeAssociation;
import org.phenopackets.api.model.condition.Phenotype;
import org.phenopackets.api.model.entity.Person;
import org.phenopackets.api.model.entity.Variant;
import org.phenopackets.api.model.meta.Evidence;
import org.phenopackets.api.model.meta.Publication;
import org.phenopackets.api.model.ontology.OntologyClass;

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
		assertEquals("#1", persons.get(0).getId());
	}

	@Test
	public void testCanCapturePatientPhenotypesWithVariantInfo() throws IOException {

		Person person = new Person();
		person.setId("person#1");
		person.setLabel("Joe Bloggs");

		Phenotype phenotype = new Phenotype();
		phenotype.setTypes(ImmutableList.of(new OntologyClass.Builder("HP:0200055").setLabel("Small hands").build()));

		Evidence journalOneEvidence = new Evidence();
		journalOneEvidence.setTypes(ImmutableList.of(new OntologyClass.Builder("ECO:0000033").setLabel("TAS").build()));
		Publication pub = new Publication();
		pub.setId("PMID:23455423");
		journalOneEvidence.setSupportingPublications(ImmutableList.of(pub));

		PhenotypeAssociation patientPhenotypeAssociation = new PhenotypeAssociation.Builder(phenotype)
																						.setEntity(person)
																						.setEvidence(Collections.singletonList(journalOneEvidence))
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
