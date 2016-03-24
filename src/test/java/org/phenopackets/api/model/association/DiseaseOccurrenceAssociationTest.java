package org.phenopackets.api.model.association;

import org.junit.Test;
import org.phenopackets.api.io.YamlGenerator;
import org.phenopackets.api.model.condition.DiseaseOccurrence;
import org.phenopackets.api.model.condition.DiseaseStage;
import org.phenopackets.api.model.entity.Disease;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;

/**
 * @author Jules Jacobsen <jules.jacobsen@sanger.ac.uk>
 */
public class DiseaseOccurrenceAssociationTest {

    private DiseaseOccurrence getDiseaseOccurrence() {
        DiseaseOccurrence occurrence = new DiseaseOccurrence();
        DiseaseStage stage = new DiseaseStage();
        stage.setDescription("Teenage onset");
        occurrence.setStage(stage);
        return occurrence;
    }

    private Disease getDisease() {
        Disease disease = new Disease();
        disease.setId("OMIM:12345");
        disease.setLabel("Nerd syndrome");
        return disease;
    }

    @Test
    public void testBuildAndGetDiseaseOccurrence() {
        DiseaseOccurrence occurrence = getDiseaseOccurrence();
        DiseaseOccurrenceAssociation association = new DiseaseOccurrenceAssociation.Builder(occurrence).build();
        assertThat(association.getDiseaseOcurrence(), equalTo(occurrence));
    }

    @Test
    public void testBuildAndGetIdWithEntity() {
        DiseaseOccurrence occurrence = getDiseaseOccurrence();
        Disease disease = getDisease();

        DiseaseOccurrenceAssociation association = new DiseaseOccurrenceAssociation.Builder(occurrence)
                .setEntity(disease)
                .build();
        assertThat(association.getEntityId(), equalTo(disease.getId()));
    }

    @Test
    public void testBuildAndGetIdWithEntityId() {
        DiseaseOccurrence occurrence = getDiseaseOccurrence();
        String entityId = "OMIM:12345";
        DiseaseOccurrenceAssociation association = new DiseaseOccurrenceAssociation.Builder(occurrence)
                .setEntityId(entityId)
                .build();
        assertThat(association.getEntityId(), equalTo(entityId));
    }

    @Test
    public void testToString() throws Exception {
        DiseaseOccurrence occurrence = getDiseaseOccurrence();
        Disease disease = getDisease();

        DiseaseOccurrenceAssociation association = new DiseaseOccurrenceAssociation.Builder(occurrence)
                .setEntity(disease)
                .build();

        System.out.println(association);
        assertThat(association.toString(), startsWith("DiseaseOccurrenceAssociation"));
    }

    @Test
    public void testYamlWrite() throws Exception {
        DiseaseOccurrence occurrence = getDiseaseOccurrence();
        Disease disease = getDisease();

        DiseaseOccurrenceAssociation association = new DiseaseOccurrenceAssociation.Builder(occurrence)
                .setEntity(disease)
                .build();

        System.out.println(YamlGenerator.render(association));
    }
}