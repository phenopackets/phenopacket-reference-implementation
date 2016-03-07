package org.monarchinitiative.ppk.model.ontology;

import java.util.List;

/**
 * A class expression in Conjunctive Normal Form https://en.wikipedia.org/wiki/Conjunctive_normal_form
 * 
 * @author cjm
 *
 */
public class Individual {
	
	List<OntologyClass> classList;
	List<OntologyClass> negatedClassList;
	
	String description;

	/**
	 * @return the classList
	 */
	public List<OntologyClass> getClassList() {
		return classList;
	}

	/**
	 * @param classList the classList to set
	 */
	public void setClassList(List<OntologyClass> classList) {
		this.classList = classList;
	}

	/**
	 * @return the negatedClassList
	 */
	public List<OntologyClass> getNegatedClassList() {
		return negatedClassList;
	}

	/**
	 * @param negatedClassList the negatedClassList to set
	 */
	public void setNegatedClassList(List<OntologyClass> negatedClassList) {
		this.negatedClassList = negatedClassList;
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
	
	
	

}
