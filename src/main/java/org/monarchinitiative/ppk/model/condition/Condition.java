package org.monarchinitiative.ppk.model.condition;

import org.monarchinitiative.ppk.model.ontology.OntologyClass;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public class Condition {
	
	@JsonPropertyDescription("A class in an ontology which this condition instantiates")
	@JsonProperty("type")
	private OntologyClass ontologyClass;
	private String description;

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the phenotypeOntologyClass
	 */
	public OntologyClass getOntologyClass() {
		return ontologyClass;
	}

	/**
	 * @param phenotypeOntologyClass the phenotypeOntologyClass to set
	 */
	public void setOntologyClass(OntologyClass phenotypeOntologyClass) {
		this.ontologyClass = phenotypeOntologyClass;
	}

	
	
}
