package org.monarchinitiative.ppk.model.condition;

import org.monarchinitiative.ppk.model.ontology.OntologyClass;
import org.monarchinitiative.ppk.model.ontology.Individual;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public class Condition extends Individual {
	
	String timeOfOnset;

	/**
	 * @return the timeOfOnset
	 */
	public String getTimeOfOnset() {
		return timeOfOnset;
	}

	/**
	 * @param timeOfOnset the timeOfOnset to set
	 */
	public void setTimeOfOnset(String timeOfOnset) {
		this.timeOfOnset = timeOfOnset;
	}
	
	

	
	
}
