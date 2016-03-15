package org.monarchinitiative.ppk.model.association;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import org.monarchinitiative.ppk.model.entity.Entity;
import org.monarchinitiative.ppk.model.meta.Evidence;

import java.util.List;

/**
 * An association connects an entity (for example, disease, person or variant) with either
 * another entity, or with some kind of descriptor (for example, phenotype).
 * 
 * All pieces of evidences are attached to associations
 * 
 * @author cjm
 * @author Jules Jacobsen <jules.jacobsen@sanger.ac.uk>
 */
public interface Association {

	@JsonProperty("entity")
	public Entity getEntity();

	@JsonProperty("evidence")
	@JsonPropertyDescription("Any Association can have any number of pieces of evidence attached")
	public List<Evidence> getEvidence();

}
