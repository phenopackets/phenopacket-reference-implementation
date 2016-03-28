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
import org.phenopackets.api.model.ontology.PropertyLiteralValue;
import org.phenopackets.api.util.DateTimeUtils;

public class MeasurementTest {

	@Test
	public void test() throws IOException {
		PhenoPacket packet = YamlReader.readFile(Paths.get("src/test/resources/condition/measurement-example1.yaml").toFile());
		System.out.println(YamlGenerator.render(packet));
		Phenotype phenotype = packet.getPhenotypeAssociations().get(0).getPhenotype();
		System.out.println(YamlGenerator.render(phenotype));
		TemporalRegion onset = phenotype.getTimeOfOnset();
		TemporalRegion offset = phenotype.getTimeOfFinishing();
		assertEquals("MmusDv:0000061", onset.getTypes().get(0).getId());
		
		List<Measurement> ms = phenotype.getMeasurements();
		assertEquals(1, ms.size());
		Measurement m = ms.get(0);
		System.out.println(YamlGenerator.render(m));
		m.getValue().equals(61.4);
		assertEquals(1, m.getPropertyValues().size());
		PropertyLiteralValue pv = m.getPropertyValues().get(0);
        assertEquals("standard_error", pv.getProperty());
        assertEquals(2.38, pv.getFiller());
		
	}

}
