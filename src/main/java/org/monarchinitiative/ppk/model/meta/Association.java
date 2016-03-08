package org.monarchinitiative.ppk.model.meta;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An association connects an entity (for example, disease, person or variant) with either
 * another entity, or with some kind of descriptor (for example, phenotype)
 * 
 * @author cjm
 *
 */
public abstract class Association {
	
	@JsonProperty("entity")
	private Entity entity;
	
	@JsonProperty("evidence_list")
	private List<Evidence> evidenceList;

	/**
	 * @return the entity
	 */
	public Entity getEntity() {
		return entity;
	}

	/**
	 * @param entity the entity to set
	 */
	public void setEntity(Entity entity) {
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
