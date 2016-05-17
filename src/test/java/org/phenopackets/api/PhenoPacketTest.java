package org.phenopackets.api;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.phenopackets.api.io.JsonGenerator;
import org.phenopackets.api.io.YamlGenerator;
import org.phenopackets.api.io.YamlReader;
import org.phenopackets.api.model.association.DiseaseOccurrenceAssociation;
import org.phenopackets.api.model.association.EnvironmentAssociation;
import org.phenopackets.api.model.association.PhenotypeAssociation;
import org.phenopackets.api.model.association.VariantAssociation;
import org.phenopackets.api.model.condition.DiseaseOccurrence;
import org.phenopackets.api.model.condition.DiseaseStage;
import org.phenopackets.api.model.condition.Phenotype;
import org.phenopackets.api.model.condition.TemporalRegion;
import org.phenopackets.api.model.entity.Disease;
import org.phenopackets.api.model.entity.Organism;
import org.phenopackets.api.model.entity.Person;
import org.phenopackets.api.model.entity.Variant;
import org.phenopackets.api.model.environment.Environment;
import org.phenopackets.api.model.evidence.Evidence;
import org.phenopackets.api.model.evidence.Publication;
import org.phenopackets.api.model.ontology.OntologyClass;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class PhenoPacketTest {

    /**
     * Helper method for cutting down on verbosity of OntologyClass builder.
     *
     * @param id
     * @param label
     * @return
     */
    private OntologyClass ontologyClass(String id, String label) {
        return OntologyClass.of(id, label);
    }

    private Person getPerson() {
        Person person = new Person();
        person.setId("person#1");
        person.setLabel("Joe Bloggs");
        person.setSex("M");
        return person;
    }

    private Disease getDisease() {
        Disease disease = new Disease();
        disease.setId("OMIM:101600");
        disease.setLabel("Pfeiffer syndrome");
        disease.setTypes(ImmutableList.of(OntologyClass.of("EFO:0000508", "genetic disorder")));
        return disease;
    }

    private Phenotype getDiseasePhenotype() {
        Phenotype diseasePhenotype = new Phenotype();
        diseasePhenotype.setTypes(ImmutableList.of(
                OntologyClass.of("HP:0000272", "Malar flattening"),
                OntologyClass.of("HP:0005347", "Cartilaginous trachea"),
                OntologyClass.of("HP:0001249", "Intellectual disability"),
                OntologyClass.of("HP:0005048", "Synostosis of carpal bones"),
                OntologyClass.of("HP:0004440", "Coronal craniosynostosis"),
                OntologyClass.of("HP:0001156", "Brachydactyly syndrome")
        ));
        return diseasePhenotype;
    }

    private DiseaseOccurrence getDiseaseOccurrence() {
        DiseaseOccurrence diseaseOccurrence = new DiseaseOccurrence();
        DiseaseStage stage = new DiseaseStage();
        stage.setDescription("Childhood onset");
        stage.setTypes(ImmutableList.of(
                OntologyClass.of("HP:0011463", "Childhood onset")
        ));
        diseaseOccurrence.setStage(stage);
        return diseaseOccurrence;
    }

    @Test
    public void testId() throws IOException {
        String id = "test-id";
        PhenoPacket pk = new PhenoPacket.Builder().id(id).build();
        assertThat(pk.getId(), equalTo(id));
    }

    @Test
    public void testTitle() throws IOException {
        String title = "test-title";
        PhenoPacket pk = new PhenoPacket.Builder().title(title).build();
        assertThat(pk.getTitle(), equalTo(title));
    }

    @Test
    public void testCanAddAndGetPersons() {
        Person person = getPerson();
        PhenoPacket packet = new PhenoPacket.Builder().addPerson(person).build();
        assertThat(packet.getPersons(), hasItem(person));
    }

    @Test
    public void testCanAddAndGetOrganisms() {
        Organism organism = new Organism();
        organism.setId("MGI:3043397");
        organism.setLabel("Ndrg1<tm1Myta>/Ndrg1<tm1Myta> [involves: 129S1/Sv * 129X1/SvJ * C57BL/6]");
        organism.setTaxon(ontologyClass("NCBI:10090", "Mus musculus"));

        PhenoPacket packet = new PhenoPacket.Builder().addOrganism(organism).build();
        assertThat(packet.getOrganisms(), hasItem(organism));
    }

    @Test
    public void testCanAddAndGetVariants() {
        Variant variant = new Variant();
        variant.setId("variant#1");
        PhenoPacket packet = new PhenoPacket.Builder().addVariant(variant).build();
        assertThat(packet.getVariants(), hasItem(variant));
    }

    @Test
    public void testCanAddAndGetDiseases() throws Exception {
        Disease disease = getDisease();

        PhenoPacket packet = new PhenoPacket.Builder().addDisease(disease).build();
        System.out.println(YamlGenerator.render(packet));
        assertThat(packet.getDiseases(), hasItem(disease));
    }

    @Test
    public void testPhenotypeAssociation() throws Exception {
        Phenotype phenotype = new Phenotype();
        phenotype.setTypes(ImmutableList.of(
                ontologyClass("HP:0000272", "Malar flattening")
        ));

        PhenotypeAssociation association = new PhenotypeAssociation.Builder(phenotype).setEntity(getPerson()).build();
        PhenoPacket packet = new PhenoPacket.Builder().addPhenotypeAssociation(association).build();
        assertThat(packet.getPhenotypeAssociations(), hasItem(association));
    }

    @Test
    public void testDiseaseOccurrenceAssociation() throws Exception {
        DiseaseOccurrence occurrence = new DiseaseOccurrence();
        Disease disease = getDisease();
        DiseaseOccurrenceAssociation association = new DiseaseOccurrenceAssociation.Builder(occurrence).setEntity(disease).build();

        PhenoPacket packet = new PhenoPacket.Builder().addDiseaseOccurrenceAssociation(association).build();
        assertThat(packet.getDiseaseOccurrenceAssociations(), hasItem(association));
    }

    @Test
    public void testEnvironmentAssociations() throws Exception {
        EnvironmentAssociation environmentAssociation = new EnvironmentAssociation.Builder(new Environment())
                .setEntity(getPerson())
                .build();

        PhenoPacket packet = new PhenoPacket.Builder()
                .addEnvironmentAssociation(environmentAssociation)
                .build();

        assertThat(packet.getEnvironmentAssociations(), hasItem(environmentAssociation));
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

        Person person = getPerson();

        Phenotype phenotype = new Phenotype();
        phenotype.setTypes(ImmutableList.of(OntologyClass.of("HP:0200055", "Small hands")));
        TemporalRegion congenitalOnset = new TemporalRegion();
        congenitalOnset.setDescription("during development");
        congenitalOnset.setTypes(ImmutableList.of(OntologyClass.of("HP:0003577", "Congenital onset")));
        phenotype.setTimeOfOnset(congenitalOnset);

        Evidence journalOneEvidence = new Evidence();
        journalOneEvidence.setTypes(ImmutableList.of(ontologyClass("ECO:0000033", "TAS")));
        Publication pub = Publication.newBuilder().setId("PMID:23455423").build();
        journalOneEvidence.setSupportingPublications(ImmutableList.of(pub));

        PhenotypeAssociation patientPhenotypeAssociation = new PhenotypeAssociation.Builder(phenotype)
                .setEntity(person)
                .addEvidence(journalOneEvidence)
                .build();

        Variant variant = new Variant();
        variant.setId("variant#1");
        variant.setLabel("c.1234A>G");
        variant.setDescriptionHGVS("c.1234A>G");

        //How can I associate the variant with the person? If there are multiple people in the phenopacket, must they all have the same variant?
        //In this case variants and phenotypes belong to the entity (the person), the person is described in the phenopacket.

        VariantAssociation variantAssociation = new VariantAssociation.Builder(variant)
                .setEntity(person)
                .addEvidence(journalOneEvidence)
                .build();

        String id = "test-id";
        String title = "Patient phenotype with age of onset and potentially causative variant";
        PhenoPacket pk = new PhenoPacket.Builder()
                .id(id)
                .title(title)
                .addPerson(person)
                .addVariant(variant)
                .addPhenotypeAssociation(patientPhenotypeAssociation)
                .addVariantAssociation(variantAssociation)
                .build();

        assertEquals(id, pk.getId());
        assertEquals(title, pk.getTitle());

        System.out.println(JsonGenerator.render(pk));
        System.out.println(YamlGenerator.render(pk));

    }

    /**
     * Code for the example shown on the wiki - use this to test it and update the wiki if the API changes.
     * @throws Exception
     */
    @Test
    public void testDiseaseAssociatedWithPhenotypesAndTimeOfOnset() throws Exception {
        Disease disease = getDisease();

        DiseaseOccurrence diseaseOccurrence = getDiseaseOccurrence();
        DiseaseOccurrenceAssociation diseaseOccurrenceAssociation = new DiseaseOccurrenceAssociation.Builder(diseaseOccurrence)
                .setEntity(disease)
                .build();

        Phenotype diseasePhenotype = getDiseasePhenotype();
        PhenotypeAssociation diseasePhenotypeAssociation = new PhenotypeAssociation.Builder(diseasePhenotype)
                .setEntity(disease)
                .build();

        PhenoPacket pk = new PhenoPacket.Builder()
                .id("id1")
                .title("Description of a disease its phenotype and time of onset")
                .addDisease(disease)
                .addDiseaseOccurrenceAssociation(diseaseOccurrenceAssociation)
                .addPhenotypeAssociation(diseasePhenotypeAssociation)
                .build();

        System.out.println(YamlGenerator.render(pk));
    }

}
