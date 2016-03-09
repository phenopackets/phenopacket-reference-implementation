package org.monarchinitiative.ppk.model.meta;

import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldProperty;

import java.util.List;

import org.monarchinitiative.ppk.model.ontology.ClassInstance;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

/**
 * An instance of a type of evidence that supports an association
 * 
 * The evidence model follows the GO model
 * 
 * @author cjm
 *
 */
public class Evidence extends ClassInstance {
	
	@JsonProperty("supporting_entities")
	private List<String> supportingEntities;
	
	@JsonProperty("source")
	@JsonPropertyDescription("publications may be represented insider or outside the packet, so a key rather than object is used")
	private List<String> supportingPublications;

	/**
	 * @return the supportingEntities
	 */
	public List<String> getSupportingEntities() {
		return supportingEntities;
	}

	/**
	 * @param supportingEntities the supportingEntities to set
	 */
	public void setSupportingEntities(List<String> supportingEntities) {
		this.supportingEntities = supportingEntities;
	}

	/**
	 * @return the supportingPublications
	 */
	public List<String> getSupportingPublications() {
		return supportingPublications;
	}

	/**
	 * @param supportingPublications the supportingPublications to set
	 */
	public void setSupportingPublications(List<String> supportingPublications) {
		this.supportingPublications = supportingPublications;
	}
	
	
	
	
}
