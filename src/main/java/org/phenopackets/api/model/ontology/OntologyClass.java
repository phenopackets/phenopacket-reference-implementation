package org.phenopackets.api.model.ontology;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;

/**
 * An OntologyClass is any individual term or concept in any ontology.
 * 
 * Formally, this class corresponds to a Class as defined in https://www.w3.org/TR/owl2-syntax/#Classes
 * 
 * @author cjm
 *
 */
@JsonDeserialize(builder = OntologyClass.Builder.class)
public class OntologyClass {
	
	@JsonProperty("id")
	@JsonPropertyDescription("A unique ontology class identifier, can be either URI or CURIE")
	final String id;
	
	@JsonProperty("label")
	@JsonPropertyDescription("A string that contains the preferred natural language term to denote the class")
	final String label;
	
	
	
	private OntologyClass(Builder builder) {
		this.id = builder.id;
		this.label = builder.label;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
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

    public static class Builder {

        String id;
        @JsonProperty
        String label = "";

        @JsonCreator
        public Builder(@JsonProperty("id") String id) {
            this.id = id;
        }

        public Builder setLabel(String label) {
            this.label = label;
            return this;
        }

        public OntologyClass build() {
            return new OntologyClass(this);
        }
    }
}
