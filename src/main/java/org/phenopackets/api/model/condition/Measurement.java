package org.phenopackets.api.model.condition;

import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldType;
import org.phenopackets.api.model.ontology.ClassInstance;
import org.phenopackets.api.model.ontology.OntologyClass;

@JsonldType("http://purl.obolibrary.org/obo/IAO_0000416")
public class Measurement extends ClassInstance {
	
	private OntologyClass unit;
	private double magnitude;
	// TODO - trait

	/**
	 * @return the unit
	 */
	public OntologyClass getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(OntologyClass unit) {
		this.unit = unit;
	}

	/**
	 * @return the magnitude
	 */
	public double getMagnitude() {
		return magnitude;
	}

	/**
	 * @param magnitude the magnitude to set
	 */
	public void setMagnitude(double magnitude) {
		this.magnitude = magnitude;
	}
	
	

}
