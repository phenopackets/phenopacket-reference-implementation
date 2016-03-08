package org.monarchinitiative.ppk.model.meta;

import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldId;
import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldProperty;

import java.util.Set;

import org.monarchinitiative.ppk.model.ontology.ClassInstance;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

/**
 * An entity encompasses persons or non-human organisms, variants, diseases, genes, cohorts, etc
 * 
 * @author cjm
 *
 */
public class Entity extends ClassInstance {
	
	
	public Entity(Builder builder) {
		super(builder);
		// TODO Auto-generated constructor stub
	}
	public Entity() {
		super();
	}
	@JsonProperty("id")
	@JsonPropertyDescription("A unique identifier for the entity, can be either URI or CURIE")
	@JsonldId
	private String id;
	
	@JsonProperty("label")
	@JsonPropertyDescription("A string that contains the preferred natural language term to denote the entity")
    @JsonldProperty("http://www.w3.org/2000/01/rdf-schema#label")
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
