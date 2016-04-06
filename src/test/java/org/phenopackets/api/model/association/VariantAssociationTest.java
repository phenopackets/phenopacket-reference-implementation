package org.phenopackets.api.model.association;

import java.io.IOException;

import org.junit.Test;
import org.phenopackets.api.io.JsonGenerator;
import org.phenopackets.api.io.YamlGenerator;
import org.phenopackets.api.model.entity.Person;
import org.phenopackets.api.model.entity.Variant;

public class VariantAssociationTest {

    @Test
    public void test() throws IOException {
        Variant variant = new Variant();
        variant.setDescriptionHGVS("test HGVS");
        variant.setId("NCBI:2256");
        variant.setLabel("FGFR3");

        Person e = new Person();
        e.setId("E:1");
        Association va = new VariantAssociation.Builder(variant).setEntity(e).build();

        System.out.println(JsonGenerator.render(va));
        System.out.println(YamlGenerator.render(va));

    }

}
