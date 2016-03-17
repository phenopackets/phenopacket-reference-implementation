package org.monarchinitiative.ppk.model.association;

import org.junit.Test;
import org.monarchinitiative.ppk.io.JsonGenerator;
import org.monarchinitiative.ppk.io.YamlGenerator;
import org.monarchinitiative.ppk.model.condition.Phenotype;
import org.monarchinitiative.ppk.model.entity.Person;

/**
 * @author Jules Jacobsen <jules.jacobsen@sanger.ac.uk>
 */
public class ConditionAssociationTest {

    @Test
    public void testTypesNotErasedWhenSerialised() throws Exception {
        Person person = new Person();
        person.setId("#1");

        Phenotype.Builder pb = new Phenotype.Builder();
        pb.addType("X:1").description("test description");

        Phenotype phenotype = pb.build();

        ConditionAssociation<Person, Phenotype> association =  new ConditionAssociation<>(person, phenotype);

        System.out.println(JsonGenerator.render(association));
        System.out.println(YamlGenerator.render(association));
    }
}