package org.phenopackets.api.model.evidence;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.collect.ImmutableList;

import org.phenopackets.api.model.ontology.ClassInstance;

import java.util.List;
import java.util.Objects;

/**
 * An instance of a type of evidence that supports an association
 * <p/>
 * The evidence model follows the GO model
 *
 * @author cjm
 */
public class Evidence extends ClassInstance {

    @JsonProperty("supporting_entities")
    @JsonInclude(Include.NON_EMPTY)
    private List<String> supportingEntities;

    @JsonProperty("source")
    @JsonPropertyDescription("publications may be represented inside or outside the packet, so a key rather than object is used")
    @JsonInclude(Include.NON_EMPTY)
    private List<Publication> supportingPublications;

    /**
     * @return the supportingEntities
     */
    public List<String> getSupportingEntities() {
        return supportingEntities;
    }

    /**
     * @param supportingEntities the supportingEntities to set
     */
    public void setSupportingEntities(List<String> supportingEntities) {
        this.supportingEntities = ImmutableList.copyOf(supportingEntities);
    }

    /**
     * @return the supportingPublications
     */
    public List<Publication> getSupportingPublications() {
        return supportingPublications;
    }

    /**
     * @param supportingPublications the supportingPublications to set
     */
    public void setSupportingPublications(List<Publication> supportingPublications) {
        this.supportingPublications = ImmutableList.copyOf(supportingPublications);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evidence evidence = (Evidence) o;
        return Objects.equals(supportingEntities, evidence.supportingEntities) &&
                Objects.equals(supportingPublications, evidence.supportingPublications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supportingEntities, supportingPublications);
    }

    @Override
    public String toString() {
        return "Evidence{" +
                "supportingEntities=" + supportingEntities +
                ", supportingPublications=" + supportingPublications +
                '}';
    }
}
