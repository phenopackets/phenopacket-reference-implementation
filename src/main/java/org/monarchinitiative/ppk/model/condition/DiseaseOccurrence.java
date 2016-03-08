package org.monarchinitiative.ppk.model.condition;

import org.monarchinitiative.ppk.model.ontology.OntologyClass;

/**
 * A DiseaseOccurrences is a particular instant or incidence of a disease class.
 * This occurrence is specific to an individual organism.
 * 
 * @author cjm
 *
 */
public class DiseaseOccurrence extends Condition {
	
	private DiseaseStage stage;

	/**
	 * @return the stage
	 */
	public DiseaseStage getStage() {
		return stage;
	}

	/**
	 * @param stage the stage to set
	 */
	public void setStage(DiseaseStage stage) {
		this.stage = stage;
	}
	
	
	
	
}
