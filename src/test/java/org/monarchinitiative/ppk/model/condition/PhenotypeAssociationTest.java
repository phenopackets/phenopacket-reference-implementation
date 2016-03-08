package org.monarchinitiative.ppk.model.condition;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.monarchinitiative.ppk.io.JSONGenerator;
import org.monarchinitiative.ppk.io.JsonYamlConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PhenotypeAssociationTest {

	@Test
	public void test() throws IOException {
		Phenotype.Builder pb = new Phenotype.Builder();
		pb.addType("X:1").description("test description");
		//System.out.println("DESC="+pb.description);
		//System.out.println("TYPES="+pb.typeList);
		Phenotype p = pb.build();
		System.out.println("DESC="+p.getDescription());
		System.out.println("TYPES="+p.getTypeList());

		PhenotypeAssociation pa = new PhenotypeAssociation();
		pa.setEntity("E:1");
		pa.setPhenotype(p);
		
		System.out.println(JSONGenerator.render(pa));
		System.out.println(JsonYamlConverter.renderYAML(pa));

	}

}
