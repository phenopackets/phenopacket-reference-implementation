package org.monarchinitiative.ppk.model.condition;

import org.monarchinitiative.ppk.model.meta.OntologyClass;

public class Condition {
	
	private OntologyClass phenotypeOntologyClass;
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
	public OntologyClass getPhenotypeOntologyClass() {
		return phenotypeOntologyClass;
	}

	/**
	 * @param phenotypeOntologyClass the phenotypeOntologyClass to set
	 */
	public void setPhenotypeOntologyClass(OntologyClass phenotypeOntologyClass) {
		this.phenotypeOntologyClass = phenotypeOntologyClass;
	}

	
	
}
