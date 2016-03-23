package org.phenopackets.api;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.phenopackets.api.io.JsonGenerator;
import org.phenopackets.api.io.YamlGenerator;
import org.phenopackets.api.io.YamlReader;
import org.phenopackets.api.model.association.PhenotypeAssociation;
import org.phenopackets.api.model.condition.Phenotype;
import org.phenopackets.api.model.condition.TemporalRegion;
import org.phenopackets.api.model.entity.Person;
import org.phenopackets.api.model.entity.Variant;
import org.phenopackets.api.model.meta.Evidence;
import org.phenopackets.api.model.meta.Publication;
import org.phenopackets.api.model.ontology.OntologyClass;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
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
	public void testFromYaml() throws IOException {
		PhenoPacket packet = YamlReader.readFile(Paths.get("src/test/resources/condition/person-phenotype-example2.yaml").toFile());
		System.out.println(YamlGenerator.render(packet));

		List<Person> persons = packet.getPersons();
		assertThat(persons.size(), equalTo(3));

		Person person1 = persons.get(0);
		assertThat(person1.getId(), equalTo("#1"));
		assertThat(person1.getSex(), equalTo("M"));
		assertThat(person1.getDateOfBirth(), equalTo("1999-01-01"));

		Person person2 = persons.get(1);
		assertThat(person2.getId(), equalTo("#2"));
		assertThat(person2.getSex(), equalTo("M"));
		assertThat(person2.getDateOfBirth(), nullValue());

		Person person3 = persons.get(2);
		assertThat(person3.getId(), equalTo("#3"));
		assertThat(person3.getSex(), equalTo("M"));
		assertThat(person3.getDateOfBirth(), nullValue());

		List<PhenotypeAssociation> phenotypeAssociations = packet.getPhenotypeAssociations();
		assertThat(phenotypeAssociations.size(), equalTo(2));
		PhenotypeAssociation phenotypeAssociation = phenotypeAssociations.get(0);
		assertThat(phenotypeAssociation.getEntityId(), equalTo("#1"));

		Phenotype phenotype = phenotypeAssociation.getPhenotype();
		assertThat(phenotype.getDescription(), equalTo("additional notes on this phenotype here"));
		List<OntologyClass> phenotypeTypes = phenotype.getTypes();
		assertThat(phenotypeTypes.size(), equalTo(1));
		OntologyClass phenotypeClass = phenotypeTypes.get(0);
		assertThat(phenotypeClass.getId(), equalTo("HP:0003560"));
		assertThat(phenotypeClass.getLabel(), equalTo("Muscular dystrophy"));

		TemporalRegion onset = phenotype.getTimeOfOnset();
		List<OntologyClass> onsetTypes = onset.getTypes();
		assertThat(onsetTypes.size(), equalTo(1));
		OntologyClass onsetClass = onsetTypes.get(0);
		assertThat(onsetClass.getId(), equalTo("HP:0003584"));
		assertThat(onsetClass.getLabel(), equalTo("Late onset"));

		Evidence evidence = phenotypeAssociation.getEvidence().get(0);
		List<OntologyClass> evidenceTypes = evidence.getTypes();
		assertThat(evidenceTypes.size(), equalTo(1));
		OntologyClass evidenceClass = evidenceTypes.get(0);
		//TODO: this is probably wrong in the source file. Shouldn't it be getId == "ECO:0000033", getLabel == "TAS"
		assertThat(evidenceClass.getId(), equalTo("TAS"));
		assertThat(evidenceClass.getLabel(), equalTo(""));

		List<Publication> supportingPublications = evidence.getSupportingPublications();
		assertThat(supportingPublications.size(), equalTo(1));
		Publication publication = supportingPublications.get(0);
		assertThat(publication.getId(), equalTo("PMID:23455423"));
		assertThat(publication.getTitle(), equalTo("Mutations in prion-like domains in hnRNPA2B1 and hnRNPA1 cause multisystem proteinopathy and ALS"));

		PhenotypeAssociation phenotypeAssociation2 = phenotypeAssociations.get(1);
		assertThat(phenotypeAssociation2.getEntityId(), equalTo("#1"));

		Phenotype phenotype2 = phenotypeAssociation2.getPhenotype();
		assertThat(phenotype2.getDescription(), nullValue());
		List<OntologyClass> phenotypeTypes2 = phenotype2.getTypes();
		assertThat(phenotypeTypes2.size(), equalTo(1));
		OntologyClass phenotypeClass2 = phenotypeTypes2.get(0);
		assertThat(phenotypeClass2.getId(), equalTo("HP:0007354"));
		assertThat(phenotypeClass2.getLabel(), equalTo("Amyotrophic lateral sclerosis"));
	}

	@Test
	public void testFromYaml_PersonVariantPhenotype() throws IOException {
		PhenoPacket packet = YamlReader.readFile("src/test/resources/person-variant-phenotype-example1.yaml");

		System.out.println("Reading in YAML:");
		System.out.println(YamlGenerator.render(packet));

		assertThat(packet.getId(), equalTo("phenopkt#1"));
		assertThat(packet.getTitle(), equalTo("Patient X phenotypes and potentially causative variant"));

		List<Person> persons = packet.getPersons();
		assertThat(persons.size(), equalTo(1));
		Person person = persons.get(0);
		assertThat(person.getId(), equalTo("person#1"));
		assertThat(person.getSex(), equalTo("M"));

		List<Variant> variants = packet.getVariants();
		assertThat(variants.size(), equalTo(1));
		Variant variant = variants.get(0);
		assertThat(variant.getId(), equalTo("variant#1"));
		assertThat(variant.getDescriptionHGVS(), equalTo("c.1234A>G"));

		List<PhenotypeAssociation> phenotypeAssociations = packet.getPhenotypeAssociations();
		assertThat(phenotypeAssociations.size(), equalTo(1));
		PhenotypeAssociation phenotypeAssociation = phenotypeAssociations.get(0);
		assertThat(phenotypeAssociation.getEntityId(), equalTo("person#1"));

		Phenotype phenotype = phenotypeAssociation.getPhenotype();
		List<OntologyClass> phenotypeTypes = phenotype.getTypes();
		assertThat(phenotypeTypes.size(), equalTo(1));
		OntologyClass phenotypeClass = phenotypeTypes.get(0);
		assertThat(phenotypeClass.getId(), equalTo("HP:0200055"));
		assertThat(phenotypeClass.getLabel(), equalTo("Small hands"));

		Evidence evidence = phenotypeAssociation.getEvidence().get(0);
		List<OntologyClass> evidenceTypes = evidence.getTypes();
		assertThat(evidenceTypes.size(), equalTo(1));
		OntologyClass evidenceClass = evidenceTypes.get(0);
		assertThat(evidenceClass.getId(), equalTo("ECO:0000033"));
		assertThat(evidenceClass.getLabel(), equalTo("TAS"));

		List<Publication> supportingPublications = evidence.getSupportingPublications();
		assertThat(supportingPublications.size(), equalTo(1));
		Publication publication = supportingPublications.get(0);
		assertThat(publication.getId(), equalTo("PMID:23455423"));

		System.out.println("Writing out YAML:");
		System.out.println(YamlGenerator.render(packet));

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
