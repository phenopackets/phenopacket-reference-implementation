package org.monarchinitiative.ppk.model.condition;

import org.monarchinitiative.ppk.model.ontology.OntologyClass;
import org.monarchinitiative.ppk.model.ontology.ClassInstance;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

/**
 * An abstract class that encompasses both {@link DiseaseOccurrence}s and {@link Phenotype}s
 * 
 * @author cjm
 *
 */
public abstract class Condition extends ClassInstance {
	
	@JsonProperty("onset")
	private TemporalRegion timeOfOnset;

	@JsonProperty("offset")
	private TemporalRegion timeOfFinishing;

	public Condition(Builder builder) {
		this.timeOfOnset = builder.timeOfOnset;
		this.timeOfFinishing = builder.timeOfFinishing;
	}

	public Condition() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the timeOfOnset
	 */
	public TemporalRegion getTimeOfOnset() {
		return timeOfOnset;
	}

	/**
	 * @param timeOfOnset the timeOfOnset to set
	 */
	public void setTimeOfOnset(TemporalRegion timeOfOnset) {
		this.timeOfOnset = timeOfOnset;
	}

	/**
	 * @return the timeOfFinishing
	 */
	public TemporalRegion getTimeOfFinishing() {
		return timeOfFinishing;
	}

	/**
	 * @param timeOfFinishing the timeOfFinishing to set
	 */
	public void setTimeOfFinishing(TemporalRegion timeOfFinishing) {
		this.timeOfFinishing = timeOfFinishing;
	}
	
	public abstract static class Builder extends ClassInstance.Builder {
		protected TemporalRegion timeOfOnset;
		protected TemporalRegion timeOfFinishing;
		
		
	}

	
	
}
