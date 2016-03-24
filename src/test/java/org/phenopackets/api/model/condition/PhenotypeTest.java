package org.phenopackets.api.model.condition;

import org.junit.Test;
import org.phenopackets.api.io.JsonGenerator;
import org.phenopackets.api.io.YamlGenerator;
import org.phenopackets.api.model.entity.Disease;
import org.phenopackets.api.model.entity.Entity;
import org.phenopackets.api.model.ontology.OntologyClass;

public class PhenotypeTest {

    @Test
    public void test() throws Exception {
        Phenotype p = new Phenotype();
        OntologyClass oc = new OntologyClass.Builder("X:1").setLabel("foo").build();
        p.setDescription("foo");
        //p.setOntologyClassConjunction(oc);

        System.out.println(YamlGenerator.render(p));

        Entity e = new Disease();

        System.out.println(YamlGenerator.render(e));

    }

    @Test
    public void builderTest() throws Exception {
        Phenotype.Builder pb = new Phenotype.Builder();
        pb.addType("X:1").description("test description");
        Phenotype p = pb.build();

        System.out.println(JsonGenerator.render(p));
        System.out.println(YamlGenerator.render(p));

        Entity e = new Disease();

        System.out.println(JsonGenerator.render(e));
        System.out.println(YamlGenerator.render(e));

    }

}
