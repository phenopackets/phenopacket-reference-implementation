package org.phenopackets.api.model.association;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.phenopackets.api.PhenoPacket;
import org.phenopackets.api.io.YamlGenerator;
import org.phenopackets.api.io.YamlReader;
import org.phenopackets.api.model.environment.Environment;
import org.phenopackets.api.model.evidence.Evidence;
import org.phenopackets.api.model.evidence.Publication;
import org.phenopackets.api.model.ontology.OntologyClass;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

/**
 * @author Jules Jacobsen <jules.jacobsen@sanger.ac.uk>
 */
public class EnvironmentAssociationTest {

    private final Environment smoking;
    private final String person1Id;
    private final Evidence tas;
    private final EnvironmentAssociation smokingAssociation;

    public EnvironmentAssociationTest() {
        smoking = new Environment();
        smoking.setTypes(ImmutableList.of(new OntologyClass.Builder("EFO_0004318").setLabel("smoking behavior").build()));
        smoking.setDescription("Smoked since birth, gave up as a teenager");
        person1Id = "person#1";

        tas = new Evidence();
        tas.setTypes(ImmutableList.of(new OntologyClass.Builder("ECO:0000033").setLabel("TAS").build()));
        tas.setSupportingPublications(ImmutableList.of(new Publication.Builder().setId("PMID:00000000").build()));

        smokingAssociation = new EnvironmentAssociation.Builder(smoking).setEntityId(person1Id).addEvidence(tas).build();
    }

    @Test
    public void testBuilder() throws Exception {

        assertThat(smokingAssociation.getEnvironment(), equalTo(smoking));
        assertThat(smokingAssociation.getEntityId(), equalTo(person1Id));

        System.out.println(YamlGenerator.render(smokingAssociation));
    }

    @Test
    public void testReadFromYaml() throws Exception {
        PhenoPacket packet = YamlReader.readFile("src/test/resources/person-environment-association-test.yaml");
        System.out.println(YamlGenerator.render(packet));

        List<EnvironmentAssociation> environmentAssociations = packet.getEnvironmentAssociations();
        assertThat(environmentAssociations, hasItems());
        EnvironmentAssociation association = environmentAssociations.get(0);
        assertThat(association, equalTo(smokingAssociation));

    }

}