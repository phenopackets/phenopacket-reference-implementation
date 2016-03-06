package org.monarchinitiative.ppk.model.condition;

import org.monarchinitiative.ppk.model.meta.Association;

public class DiseaseOccurrenceAssociation extends Association {
	
	private DiseaseOccurrence disease;

	/**
	 * @return the disease
	 */
	public DiseaseOccurrence getDisease() {
		return disease;
	}

	/**
	 * @param disease the disease to set
	 */
	public void setDisease(DiseaseOccurrence disease) {
		this.disease = disease;
	}

	
	

}
