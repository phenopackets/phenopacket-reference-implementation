package org.phenopackets.api.model.ontology;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;
import org.phenopackets.api.PhenoPacket;
import org.phenopackets.api.io.YamlGenerator;
import org.phenopackets.api.io.YamlReader;
import org.phenopackets.api.model.condition.Phenotype;
import org.phenopackets.api.model.entity.Person;

public class ClassInstanceTest {

	/**
	 *
	 * @throws IOException
	 */
	@Test
	public void testNegationFromFile() throws IOException {
		PhenoPacket packet = YamlReader.readFile(Paths.get("src/test/resources/condition/person-phenotype-negation-example.yaml").toFile());
		System.out.println(YamlGenerator.render(packet));
		Phenotype phenotype = 
				packet.getPhenotypeAssociations().get(0).getPhenotype();
		List<OntologyClass> types = phenotype.getTypes();
		List<OntologyClass> ntypes = phenotype.getNegatedTypes();
		System.out.println(YamlGenerator.render(types));
		assertEquals(2, types.size());
		assertEquals(1, ntypes.size());
		assertEquals("HP:0001714", ntypes.get(0).getId());
		
	}

}
