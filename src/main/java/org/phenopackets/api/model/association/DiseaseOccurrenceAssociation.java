package org.phenopackets.api.model.association;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.phenopackets.api.model.condition.DiseaseOccurrence;
import org.phenopackets.api.model.meta.Evidence;

import java.util.List;

@JsonPropertyOrder({"entity", "disease", "evidence"})
public class DiseaseOccurrenceAssociation implements Association {
	
	private DiseaseOccurrence disease;
	private String entityId;
	private List<Evidence> evidences;


	/**
	 * @return the disease
	 */
	public DiseaseOccurrence getDiseaseOcurrence() {
		return disease;
	}

	@Override
	public String getEntityId() {
		return entityId;
	}

	@Override
	public List<Evidence> getEvidence() {
		return evidences;
	}
}
