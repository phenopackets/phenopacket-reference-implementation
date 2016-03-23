package org.phenopackets.api.io;

import org.junit.Test;
import org.phenopackets.api.PhenoPacket;
import org.phenopackets.api.model.association.PhenotypeAssociation;
import org.phenopackets.api.model.condition.Phenotype;
import org.phenopackets.api.model.entity.Disease;
import org.phenopackets.api.model.ontology.OntologyClass;

import java.util.Collections;

public class YamlGeneratorTest {

	private PhenoPacket makePhenoPacket() {

        Disease disease = new Disease();
        disease.setId("OMIM:101600");
        disease.setLabel("Pfeiffer syndrome");

		Phenotype p = new Phenotype();
        p.setTypes(Collections.singletonList(new OntologyClass.Builder("X:1").setLabel("foo").build()));
		p.setDescription("foo");

		PhenotypeAssociation pa = new PhenotypeAssociation.Builder(p).setEntity(disease).build();
        return new PhenoPacket.Builder()
                .addDisease(disease)
                .addPhenotypeAssociation(pa)
                .build();
	}

	@Test
	public void test() throws Exception {
		YamlGenerator yg = new YamlGenerator();
		PhenoPacket pk = makePhenoPacket();
		System.out.println(yg.render(pk));
	}

}
