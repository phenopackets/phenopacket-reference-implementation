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
		PhenoPacket pk = new PhenoPacket();

        Disease e = new Disease();
        e.setId("OMIM:101600");
        e.setLabel("Pfeiffer syndrome");
		pk.addEntity(e);

		Phenotype p = new Phenotype();
		OntologyClass oc = new OntologyClass.Builder("X:1").setLabel("foo").build();
		p.setTypes(Collections.singletonList(oc));
		p.setDescription("foo");

		PhenotypeAssociation pa = new PhenotypeAssociation.Builder(p).setEntity(e).build();
		pk.addPhenotypeAssociation(pa);

		return pk;
	}

	@Test
	public void test() throws Exception {
		YamlGenerator yg = new YamlGenerator();
		PhenoPacket pk = makePhenoPacket();
		System.out.println(yg.render(pk));
	}

}
