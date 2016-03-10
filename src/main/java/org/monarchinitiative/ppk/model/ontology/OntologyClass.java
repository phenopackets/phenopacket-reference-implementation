package org.monarchinitiative.ppk.model.ontology;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.util.Objects;

/**
 * An OntologyClass is any individual term or concept in any ontology.
 * 
 * Formally, this class corresponds to a Class as defined in https://www.w3.org/TR/owl2-syntax/#Classes
 * 
 * @author cjm
 *
 */
public class OntologyClass {
	
	@JsonProperty("id")
	@JsonPropertyDescription("A unique ontology class identifier, can be either URI or CURIE")
	String id;
	
	@JsonProperty("label")
	@JsonPropertyDescription("A string that contains the preferred natural language term to denote the class")
	String label;
	
	public OntologyClass() {
		super();
	}
	
	
	public OntologyClass(String id) {
		super();
		this.id = id;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		OntologyClass that = (OntologyClass) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(label, that.label);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, label);
	}

	@Override
	public String toString() {
		return "OntologyClass{" +
				"id='" + id + '\'' +
				", label='" + label + '\'' +
				'}';
	}
}
