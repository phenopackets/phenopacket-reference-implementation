package org.monarchinitiative.ppk.model.ontology;

import java.util.ArrayList;
import java.util.List;

import org.monarchinitiative.ppk.model.condition.Phenotype;
import org.monarchinitiative.ppk.model.condition.TemporalRegion;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

/**
 * An abstract class for anything that can be described as a boolean combination of ontology classes
 * 
 * @author cjm
 *
 */
public abstract class ClassInstance {
	
	@JsonProperty("types")
	@JsonPropertyDescription("Any instance can be positively described as the intersection of any number of ontology classes.")
	List<OntologyClass> typeList;

	@JsonProperty("negated_types")
	@JsonPropertyDescription("Any instance can be assigned any number of negative classes.")
	List<OntologyClass> negatedTypeList;
	
	@JsonProperty("description")
	@JsonPropertyDescription("An optional free text description that can enhance the ontology class based description")
	String description;

	/**
	 * @return the classList
	 */
	public List<OntologyClass> getTypeList() {
		return typeList;
	}

	/**
	 * @param classList the classList to set
	 */
	public void setTypeList(List<OntologyClass> classList) {
		this.typeList = classList;
	}

	/**
	 * @return the negatedClassList
	 */
	public List<OntologyClass> getNegatedTypeList() {
		return negatedTypeList;
	}

	/**
	 * @param negatedClassList the negatedClassList to set
	 */
	public void setNegatedTypeList(List<OntologyClass> negatedClassList) {
		this.negatedTypeList = negatedClassList;
	}

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
	
	public static class Builder {
		private List<OntologyClass> typeList = new ArrayList<>(); 
		private List<OntologyClass> negatedTypeList;
		private String description;
		
		public Builder addType(String id) {
			OntologyClass c = new OntologyClass(id);
			typeList.add(c);
			return this;
		}
		
		public Builder description(String description) {
			this.description = description;
			return this;
		}

		
		
	}
	
	public ClassInstance(ClassInstance.Builder builder) {
		this.typeList = builder.typeList;
		this.negatedTypeList = builder.negatedTypeList;
		this.description = builder.description;
	}
	public ClassInstance() {
		
	}

}
