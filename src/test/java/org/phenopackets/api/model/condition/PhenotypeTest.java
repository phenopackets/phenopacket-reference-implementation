package org.phenopackets.api.model.condition;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.phenopackets.api.io.JsonGenerator;
import org.phenopackets.api.io.YamlGenerator;
import org.phenopackets.api.model.ontology.OntologyClass;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class PhenotypeTest {

    @Test
    public void test() throws Exception {
        Phenotype p = new Phenotype();
        OntologyClass oc = new OntologyClass.Builder("X:1").setLabel("foo").build();
        p.setDescription("foo");
        //p.setOntologyClassConjunction(oc);

        System.out.println(YamlGenerator.render(p));
    }

    @Test
    public void builderTest() throws Exception {
        Phenotype.Builder pb = new Phenotype.Builder();
        pb.addType("X:1").description("test description");
        Phenotype p = pb.build();

        System.out.println(JsonGenerator.render(p));
        System.out.println(YamlGenerator.render(p));
    }

    @Test
    public void testPhenotype() throws Exception {
        OntologyClass malarFlattening = OntologyClass.of("HP:0000272", "Malar flattening");

        Phenotype phenotype = new Phenotype();
        phenotype.setTypes(ImmutableList.of(
                malarFlattening
        ));
        assertThat(phenotype.getTypes(), hasItem(malarFlattening));

        System.out.println(JsonGenerator.render(phenotype));
        System.out.println(YamlGenerator.render(phenotype));
    }

    @Test
    public void testNegatedPhenotype() throws Exception {
        OntologyClass intellectualDisability = OntologyClass.of("HP:0001249", "Intellectual disability");

        Phenotype phenotype = new Phenotype();
        phenotype.setNegatedTypes(ImmutableList.of(
                intellectualDisability
        ));
        assertThat(phenotype.getNegatedTypes(), hasItem(intellectualDisability));

        System.out.println(JsonGenerator.render(phenotype));
        System.out.println(YamlGenerator.render(phenotype));
    }

    @Test
    public void testPositiveAndNegatedPhenotype() throws Exception {
        OntologyClass intellectualDisability = OntologyClass.of("HP:0001249", "Intellectual disability");
        OntologyClass malarFlattening = OntologyClass.of("HP:0000272", "Malar flattening");

        Phenotype phenotype = new Phenotype();
        phenotype.setTypes(ImmutableList.of(malarFlattening));
        phenotype.setNegatedTypes(ImmutableList.of(intellectualDisability));

        assertThat(phenotype.getTypes(), hasItem(malarFlattening));
        assertThat(phenotype.getNegatedTypes(), hasItem(intellectualDisability));

        System.out.println(JsonGenerator.render(phenotype));
        System.out.println(YamlGenerator.render(phenotype));
    }

}
