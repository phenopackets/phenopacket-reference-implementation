package org.monarchinitiative.ppk.model.association;

import org.monarchinitiative.ppk.model.condition.DiseaseOccurrence;
import org.monarchinitiative.ppk.model.entity.Entity;
import org.monarchinitiative.ppk.model.meta.Evidence;

import java.util.List;

public class DiseaseOccurrenceAssociation implements Association {
	
	private DiseaseOccurrence disease;
	private Entity entity;
	private List<Evidence> evidences;


	/**
	 * @return the disease
	 */
	public DiseaseOccurrence getDiseaseOcurrence() {
		return disease;
	}

	@Override
	public Entity getEntity() {
		return entity;
	}

	@Override
	public List<Evidence> getEvidence() {
		return evidences;
	}
}
