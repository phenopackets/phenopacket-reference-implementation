package org.phenopackets.api.model.condition;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.Period;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.phenopackets.api.PhenoPacket;
import org.phenopackets.api.io.YamlGenerator;
import org.phenopackets.api.io.YamlReader;
import org.phenopackets.api.model.entity.Person;
import org.phenopackets.api.model.environment.Environment;
import org.phenopackets.api.util.DateTimeUtils;

public class ConditionFrequencyTest {

	@Test
	public void test() throws IOException {
		PhenoPacket packet = YamlReader.readFile(Paths.get("src/test/resources/condition/organism-phenotype-penetrance-expressivity-example.yaml").toFile());
		System.out.println(YamlGenerator.render(packet));
		Phenotype phenotype = packet.getPhenotypeAssociations().get(0).getPhenotype();
		System.out.println(YamlGenerator.render(phenotype));
	
		ConditionFrequency frequency = phenotype.getFrequency();
		assertEquals(2, frequency.getTypes().size());
		
	}

}
