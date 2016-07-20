package org.phenopackets.api.model.association;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import org.phenopackets.api.model.entity.Entity;
import org.phenopackets.api.model.evidence.Evidence;

import java.util.List;

/**
 * An association connects an entity (for example, disease, person or variant) with either
 * another entity, or with some kind of descriptor (for example, phenotype).
 * <p/>
 * All pieces of evidences are attached to associations
 *
 * @author cjm
 * @author Jules Jacobsen <jules.jacobsen@sanger.ac.uk>
 */
public interface Association<T extends Entity> {

    @JsonProperty("entity")
    public String getEntityId();

    @JsonProperty("evidence")
    @JsonPropertyDescription("Any Association can have any number of pieces of evidence attached")
    public List<Evidence> getEvidence();
    
    @JsonProperty("contributor")
    public String getContributorId();
    
    @JsonProperty("date")
    public String getDate();

}
