package org.phenopackets.api.model.ontology;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Jules Jacobsen <jules.jacobsen@sanger.ac.uk>
 */
public class OntologyClassTest {

    @Test
    public void testBuilder() {
        String id = "id";
        String label = "term";
        OntologyClass ontologyClass = new OntologyClass.Builder(id).setLabel(label).build();
        assertThat(ontologyClass.getId(), equalTo(id));
        assertThat(ontologyClass.getLabel(), equalTo(label));
    }

    @Test
    public void testConvenienceStaticFactoryMethod() {
        String id = "id";
        String label = "term";
        OntologyClass fromBuilder = new OntologyClass.Builder(id).setLabel(label).build();
        OntologyClass fromOf = OntologyClass.of(id, label);
        assertThat(fromOf, equalTo(fromBuilder));
    }
}