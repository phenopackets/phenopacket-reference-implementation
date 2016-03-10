package org.monarchinitiative.ppk.model.condition;

import org.junit.Test;
import org.monarchinitiative.ppk.io.JsonGenerator;
import org.monarchinitiative.ppk.io.YamlGenerator;

import java.io.IOException;

public class PhenotypeAssociationTest {

	@Test
	public void test() throws IOException {
		Phenotype.Builder pb = new Phenotype.Builder();
		pb.addType("X:1").description("test description");

		Phenotype p = pb.build();
		System.out.println("DESC=" + p.getDescription());
		System.out.println("TYPES=" + p.getTypes());

		PhenotypeAssociation pa = new PhenotypeAssociation();
		pa.setEntity("E:1");
		pa.setPhenotype(p);
		
		System.out.println(JsonGenerator.render(pa));
		System.out.println(YamlGenerator.render(pa));

	}

}
