package org.monarchinitiative.ppk;

import org.junit.Test;
import org.monarchinitiative.ppk.io.JsonGenerator;
import org.monarchinitiative.ppk.io.YamlGenerator;
import org.monarchinitiative.ppk.model.condition.Phenotype;
import org.monarchinitiative.ppk.model.condition.PhenotypeAssociation;
import org.monarchinitiative.ppk.model.genome.Variant;
import org.monarchinitiative.ppk.model.meta.EntityType;
import org.monarchinitiative.ppk.model.ontology.OntologyClass;
import org.monarchinitiative.ppk.model.organism.Person;

import java.io.IOException;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

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
	public void testCanCapturePatientPhenotypesWithVariantInfo() throws IOException {

		Person person = new Person();
		person.setLabel("Joe Bloggs");
		// This is a bit silly - a person isn't always a patient. Plus why should I have to set it?
		// Being a person is an inherent property of being a person....
		person.setType(EntityType.patient);

		Phenotype phenotype = new Phenotype();
		phenotype.setTypes(Collections.singletonList(new OntologyClass.Builder("HP:0200055").setLabel("Small hands").build()));

		PhenotypeAssociation patientPhenotypeAssociation = new PhenotypeAssociation();
		patientPhenotypeAssociation.setPhenotype(phenotype);
		//shouldn't setEntity actually require an Entity? If I call person.getId() - id is null in this case - then the association is lost.
		patientPhenotypeAssociation.setEntity(person.getLabel());

		Variant personVariant = new Variant();
		personVariant.setDescriptionHGVS("c.1234A>G");

		//How can I associate the variant with the person? If there are multiple people in the phenopacket, must they all have the same variant?
		//In this case variants and phenotypes belong to the entity (the person), the person is described in the phenopacket.

		String id = "test-id";
		String title = "Patient X phenotypes and potentially causative variant";
		PhenoPacket pk = new PhenoPacket.Builder().id(id).title(title).build();
		pk.addEntity(person);
		pk.setPersons(Collections.singletonList(person));
		pk.addPhenotypeAssociation(patientPhenotypeAssociation);
		pk.setVariants(Collections.singletonList(personVariant));

		assertEquals(id, pk.getId());
		assertEquals(title, pk.getTitle());

		System.out.println(JsonGenerator.render(pk));
		System.out.println(YamlGenerator.render(pk));

	}
}
