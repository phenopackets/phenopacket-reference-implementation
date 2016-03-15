package org.monarchinitiative.ppk.io;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.monarchinitiative.ppk.model.condition.DiseaseOccurrence;
import org.monarchinitiative.ppk.model.condition.DiseaseStage;
import org.monarchinitiative.ppk.model.condition.Phenotype;

/**
 * @author Jules Jacobsen <jules.jacobsen@sanger.ac.uk>
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Phenotype.class, name = "phenotype"),
        @JsonSubTypes.Type(value = DiseaseStage.class, name = "disease_stage"),
        @JsonSubTypes.Type(value = DiseaseOccurrence.class, name = "disease_occurrence")})
public abstract class ConditionMixin {
}
