package org.monarchinitiative.ppk.model.organism;

import org.monarchinitiative.ppk.model.meta.Entity;
import org.monarchinitiative.ppk.model.ontology.OntologyClass;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

/**
 * @author cjm
 *
 */
public class Organism extends Entity {
	
	@JsonProperty("taxon")
	@JsonPropertyDescription("points to an instance of the taxon to which this belongs")
	private OntologyClass taxon;
	private OntologyClass strain;
	
	@JsonProperty("sex")
	@JsonPropertyDescription("code for the biological sex of the organism. Mappings from codes are in JSON-LD context")
	private String sex;
	
	/**
	 * @return the taxon
	 */
	public OntologyClass getTaxon() {
		return taxon;
	}
	/**
	 * @param taxon the taxon to set
	 */
	public void setTaxon(OntologyClass taxon) {
		this.taxon = taxon;
	}
	/**
	 * @return the strain
	 */
	public OntologyClass getStrain() {
		return strain;
	}
	/**
	 * @param strain the strain to set
	 */
	public void setStrain(OntologyClass strain) {
		this.strain = strain;
	}
	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
	

}
