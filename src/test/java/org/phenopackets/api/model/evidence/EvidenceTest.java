package org.phenopackets.api.model.evidence;

import org.junit.Before;
import org.junit.Test;
import org.phenopackets.api.PhenoPacket;
import org.phenopackets.api.io.YamlGenerator;
import org.phenopackets.api.io.YamlReader;
import org.phenopackets.api.model.association.PhenotypeAssociation;
import org.phenopackets.api.model.ontology.OntologyClass;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Jules Jacobsen <jules.jacobsen@sanger.ac.uk>
 */
public class EvidenceTest {

    private Evidence evidence;

    @Before
    public void setUp() {
        evidence = new Evidence();
        evidence.setTypes(Arrays.asList(new OntologyClass.Builder("ECO:0000033").setLabel("TAS").build()));
        evidence.setSupportingPublications(
                Arrays.asList(
                        new Publication.Builder()
                                .setId("ISBN-13:9780810911505")
                                .setTitle("All Mimsy were the Borogoves")
                                .build()
                )
        );
        evidence.setSupportingEntities(Arrays.asList("entity#1"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSupportingPublicationsIsImmutable() {
        evidence.getSupportingPublications().add(new Publication.Builder().setTitle("Crime and Punishment").build());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSupportingEntitiesIsImmutable() {
        evidence.getSupportingEntities().add("illegal entity");
    }

    @Test
    public void testYamlWrite() throws Exception {
        System.out.println(YamlGenerator.render(evidence));
    }

    @Test
    public void testYamlRead() throws Exception {
        PhenoPacket phenoPacket = YamlReader.readFile("src/test/resources/condition/person-phenotype-example2.yaml");
        PhenotypeAssociation phenotypeAssociation = phenoPacket.getPhenotypeAssociations().get(0);

        Evidence evidence = phenotypeAssociation.getEvidence().get(0);
        assertThat(evidence.getSupportingPublications().isEmpty(), is(false));

        Publication publication = evidence.getSupportingPublications().get(0);
        assertThat(publication.getId(), equalTo("PMID:23455423"));
        assertThat(publication.getTitle(), equalTo("Mutations in prion-like domains in hnRNPA2B1 and hnRNPA1 cause multisystem proteinopathy and ALS"));
    }

}