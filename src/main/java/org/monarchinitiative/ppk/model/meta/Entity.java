package org.monarchinitiative.ppk.model.meta;

import java.util.Set;

import org.monarchinitiative.ppk.model.ontology.Individual;

public class Entity extends Individual {
	
	private String id;
	private String label;
	private EntityType type;
	
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
	/**
	 * @return the type
	 */
	public EntityType getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(EntityType type) {
		this.type = type;
	}
	
	

}
