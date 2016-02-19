package org.monarchinitiative.ppk.io;

import static org.junit.Assert.*;

import org.junit.Test;
import org.monarchinitiative.ppk.model.condition.Phenotype;
import org.monarchinitiative.ppk.model.meta.Association;
import org.monarchinitiative.ppk.model.meta.Entity;
import org.monarchinitiative.ppk.model.meta.EntityType;
import org.monarchinitiative.ppk.model.meta.OntologyClass;
import org.monarchinitiative.ppk.model.packet.Packet;
import org.monarchinitiative.ppk.model.packet.PhenotypeAssociation;
import org.monarchinitiative.ppk.model.packet.Profile;

public class YAMLGeneratorTest {

	public Packet makePacket() {
		Packet pk = new Packet();

		Entity e = new Entity();
		e.setType(EntityType.disease);
		pk.addEntity(e);

		Phenotype p = new Phenotype();
		OntologyClass oc = new OntologyClass();
		oc.setId("X:1");
		oc.setLabel("foo");
		p.setDescription("foo");
		p.setPhenotypeOntologyClass(oc);

		PhenotypeAssociation pa = new PhenotypeAssociation();
		pa.setEntity(e);
		pa.setPhenotype(p);
		pk.addPhenotypeAssociation(pa);

		return pk;
	}

	@Test
	public void test() {
		YAMLGenerator yg = new YAMLGenerator();
		Packet pk = makePacket();
		System.out.println(yg.render(pk));
	}

}
