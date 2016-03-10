package org.monarchinitiative.ppk.model.condition;

import org.junit.Test;
import org.monarchinitiative.ppk.io.JsonGenerator;
import org.monarchinitiative.ppk.io.YamlGenerator;
import org.monarchinitiative.ppk.model.meta.Entity;
import org.monarchinitiative.ppk.model.meta.EntityType;
import org.monarchinitiative.ppk.model.ontology.OntologyClass;

public class PhenotypeTest {

	private final YamlGenerator yamlGenerator = new YamlGenerator();

	@Test
	public void test() throws Exception {
		Phenotype p = new Phenotype();
		OntologyClass oc = new OntologyClass.Builder("X:1").setLabel("foo").build();
		p.setDescription("foo");
		//p.setOntologyClassConjunction(oc);

		String output = yamlGenerator.render(p);
		System.out.println(output);
		
		Entity e = new Entity();
		e.setType(EntityType.disease);
		System.out.println(yamlGenerator.render(e));
		
	}
	
	@Test
	public void builderTest() throws Exception {
		Phenotype.Builder pb = new Phenotype.Builder();
		pb.addType("X:1").description("test description");
		Phenotype p = pb.build();

		String output = yamlGenerator.render(p);
		System.out.println(output);
		
		Entity e = new Entity();
		e.setType(EntityType.disease);
		System.out.println(yamlGenerator.render(e));
		System.out.println(JsonGenerator.render(p));
		
	}

}
