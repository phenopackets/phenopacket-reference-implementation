package org.monarchinitiative.ppk.model.condition;

import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldProperty;

import org.monarchinitiative.ppk.model.environment.Environment;
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
	
	@JsonProperty("has_location")
	@JsonPropertyDescription("the location in an organism or cell in which the phenotype manifests")
	@JsonldProperty("http://purl.obolibrary.org/obo/BFO_0000066")
	private OrganismalSite hasLocation;
	
	@JsonProperty("onset")
	@JsonPropertyDescription("the time region in which the condition is first manifest")
	private TemporalRegion timeOfOnset;

	@JsonProperty("offset")
	@JsonPropertyDescription("the time region in which the condition ceases to manifest")
	private TemporalRegion timeOfFinishing;
	
	@JsonProperty("severity")
	@JsonPropertyDescription("the degree to which the phenotype is manifest, related to the concept of expressivity, see http://www.ncbi.nlm.nih.gov/books/NBK22090/")
	private ConditionSeverity severity;
	
	@JsonProperty("environment")
	@JsonPropertyDescription("the environment in which the phenotype is expressed")
	private Environment environment;

	public Condition(Builder builder) {
		super(builder);
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
	
	
	
	/**
	 * @return the hasLocation
	 */
	public OrganismalSite getHasLocation() {
		return hasLocation;
	}

	/**
	 * @param hasLocation the hasLocation to set
	 */
	public void setHasLocation(OrganismalSite hasLocation) {
		this.hasLocation = hasLocation;
	}



	public abstract static class Builder extends ClassInstance.Builder {
		protected TemporalRegion timeOfOnset;
		protected TemporalRegion timeOfFinishing;
		
		
	}

	
	
}
