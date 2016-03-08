package org.monarchinitiative.ppk.model.meta;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

/**
 * An association connects an entity (for example, disease, person or variant) with either
 * another entity, or with some kind of descriptor (for example, phenotype).
 * 
 * All pieces of evidences are attached to associations
 * 
 * @author cjm
 *
 */
public abstract class Association {
	
	@JsonProperty("entity")
	private String entity;
	
	@JsonProperty("evidence_list")
	@JsonPropertyDescription("Any Association can have any number of pieces of evidence attached")
	private List<Evidence> evidenceList;

	/**
	 * @return the entity
	 */
	public String getEntity() {
		return entity;
	}

	/**
	 * @param entity the entity to set
	 */
	public void setEntity(String entity) {
		this.entity = entity;
	}

	/**
	 * @return the evidenceList
	 */
	public List<Evidence> getEvidenceList() {
		return evidenceList;
	}

	/**
	 * @param evidenceList the evidenceList to set
	 */
	public void setEvidenceList(List<Evidence> evidenceList) {
		this.evidenceList = evidenceList;
	}
	
	
	

}
