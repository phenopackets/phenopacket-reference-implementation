package org.monarchinitiative.ppk.io;

import org.junit.Test;
import org.monarchinitiative.ppk.PhenoPacket;
import org.monarchinitiative.ppk.model.condition.Phenotype;
import org.monarchinitiative.ppk.model.condition.PhenotypeAssociation;
import org.monarchinitiative.ppk.model.meta.Entity;
import org.monarchinitiative.ppk.model.meta.EntityType;
import org.monarchinitiative.ppk.model.ontology.OntologyClass;

import java.util.Collections;

public class YamlGeneratorTest {

	private PhenoPacket makePhenoPacket() {
		PhenoPacket pk = new PhenoPacket();

		Entity e = new Entity();
		e.setType(EntityType.disease);
		pk.addEntity(e);

		Phenotype p = new Phenotype();
		//OntologyClassConjunction occ = new OntologyClassConjunction();
		OntologyClass oc = new OntologyClass.Builder("X:1").setLabel("foo").build();
		p.setTypes(Collections.singletonList(oc));
		p.setDescription("foo");
		//p.setOntologyClassConjunction(occ);

		PhenotypeAssociation pa = new PhenotypeAssociation();
		pa.setEntity(e.getId());
		pa.setPhenotype(p);
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
