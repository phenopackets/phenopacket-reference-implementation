package org.monarchinitiative.ppk.model.condition;

import static org.junit.Assert.*;

import org.junit.Test;
import org.monarchinitiative.ppk.model.condition.Phenotype.Builder;
import org.monarchinitiative.ppk.model.meta.Entity;
import org.monarchinitiative.ppk.model.meta.EntityType;
import org.monarchinitiative.ppk.model.ontology.OntologyClass;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PhenotypeTest {

	@Test
	public void test() {
		Phenotype p = new Phenotype();
		OntologyClass oc = new OntologyClass();
		oc.setId("X:1");
		oc.setLabel("foo");
		p.setDescription("foo");
		//p.setOntologyClassConjunction(oc);
		
		Representer representer = new Representer();
		representer.getPropertyUtils().setSkipMissingProperties(true);

		
		Constructor constructor = new Constructor();
		constructor.addTypeDescription(new TypeDescription(Phenotype.class, "!test"));
		Yaml yaml = new Yaml(constructor, representer);

		String output = yaml.dump(p);
		System.out.println(output);
		
		Entity e = new Entity();
		e.setType(EntityType.disease);
		System.out.println(yaml.dump(e));
		
	}
	
	@Test
	public void builderTest() throws JsonProcessingException {
		Phenotype.Builder pb = new Phenotype.Builder();
		pb.addType("X:1").description("test description");
		Phenotype p = pb.build();
		
		Representer representer = new Representer();
		representer.getPropertyUtils().setSkipMissingProperties(true);

		
		Constructor constructor = new Constructor();
		constructor.addTypeDescription(new TypeDescription(Phenotype.class, "!test"));
		Yaml yaml = new Yaml(constructor, representer);

		String output = yaml.dump(p);
		System.out.println(output);
		
		Entity e = new Entity();
		e.setType(EntityType.disease);
		System.out.println(yaml.dump(e));
		
		ObjectMapper m = new ObjectMapper();
		String s = m.writerWithDefaultPrettyPrinter().writeValueAsString(p);
		System.out.println(s);
		
	}

}
