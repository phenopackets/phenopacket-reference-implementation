package org.phenopackets.api.model.condition;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.phenopackets.api.PhenoPacket;
import org.phenopackets.api.io.JsonGenerator;
import org.phenopackets.api.io.YamlGenerator;
import org.phenopackets.api.io.YamlReader;
import org.phenopackets.api.model.entity.Person;
import org.phenopackets.api.util.DateTimeUtils;

public class TemporalRegionTest {

	@Test
	public void testAgeOfOnset() throws IOException {
		PhenoPacket packet = YamlReader.readFile(Paths.get("src/test/resources/condition/age-of-onset-example1.yaml").toFile());
		//System.out.println(JsonGenerator.render(packet));
		System.out.println(YamlGenerator.render(packet));
		List<Person> persons = packet.getPersons();
		assertTrue(persons.size() == 1);
		Phenotype phenotype = packet.getPhenotypeAssociations().get(0).getPhenotype();
		System.out.println(phenotype);
		TemporalRegion onset = phenotype.getTimeOfOnset();
		assertEquals("during development", onset.getDescription());
		assertEquals("HP:0003577", onset.getTypes().get(0).getId());
	}

	@Test
	public void testAgeOfOnsetAdvanced() throws IOException, ParseException {
		PhenoPacket packet = YamlReader.readFile(Paths.get("src/test/resources/condition/age-of-onset-example2.yaml").toFile());
		System.out.println(YamlGenerator.render(packet));
		List<Person> persons = packet.getPersons();
		assertTrue(persons.size() == 1);
		Phenotype phenotype = packet.getPhenotypeAssociations().get(0).getPhenotype();
		System.out.println(YamlGenerator.render(phenotype));
		TemporalRegion onset = phenotype.getTimeOfOnset();
		TemporalRegion offset = phenotype.getTimeOfFinishing();
		assertEquals("HP:0003623", onset.getTypes().get(0).getId());
		assertEquals("HP:0003621", offset.getTypes().get(0).getId());
		System.out.println(YamlGenerator.render(onset));
		assertEquals("2000-01-01", onset.getStartTime());
		assertEquals("2015-01-01", offset.getEndTime());
		Date s = DateTimeUtils.parseDateString(onset.getStartTime());
		System.out.println(s);
		Period p = DateTimeUtils.getPeriod(onset);
		System.out.println(p);
		Period p2 = DateTimeUtils.getDuration(phenotype);
		System.out.println(p2); // 15 years
	}

}
