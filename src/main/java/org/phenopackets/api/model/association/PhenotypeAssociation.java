package org.phenopackets.api.model.association;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.ImmutableList;
import org.phenopackets.api.model.condition.Phenotype;
import org.phenopackets.api.model.entity.Entity;
import org.phenopackets.api.model.meta.Evidence;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonDeserialize(builder = PhenotypeAssociation.Builder.class)
@JsonPropertyOrder({"entity", "phenotype", "evidence"})
public class PhenotypeAssociation implements Association {
	
	private final Phenotype phenotype;
	private final String entityId;
	private final List<Evidence> evidence;

    private PhenotypeAssociation(Builder builder) {
        this.phenotype = builder.phenotype;
        this.entityId = builder.entityId;
        this.evidence = ImmutableList.copyOf(builder.evidence);
    }

	public Phenotype getPhenotype() {
		return phenotype;
	}

    @Override
    public String getEntityId() {
        return entityId;
    }

	@Override
	public List<Evidence> getEvidence() {
		return evidence;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhenotypeAssociation that = (PhenotypeAssociation) o;
        return Objects.equals(phenotype, that.phenotype) &&
                Objects.equals(entityId, that.entityId) &&
                Objects.equals(evidence, that.evidence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phenotype, entityId, evidence);
    }

    @Override
    public String toString() {
        return "PhenotypeAssociation{" +
                "phenotype=" + phenotype +
                ", entityId=" + entityId +
                ", evidence=" + evidence +
                '}';
    }

    public static class Builder {

        private final Phenotype phenotype;

        @JsonProperty("entity")
        private String entityId;
        @JsonProperty
        private List<Evidence> evidence = new ArrayList<>();

        @JsonCreator
        public Builder(@JsonProperty("phenotype") Phenotype phenotype) {
            this.phenotype = phenotype;
        }

        public Builder setEntity(Entity entity) {
            this.entityId = entity.getId();
            return this;
        }

        public Builder setEntityId(String entityId) {
            this.entityId = entityId;
            return this;
        }

        public Builder setEvidence(List<Evidence> evidence) {
            this.evidence = evidence;
            return this;
        }

        public Builder addEvidence(Evidence evidence) {
            this.evidence.add(evidence);
            return this;
        }

        public PhenotypeAssociation build() {
            return new PhenotypeAssociation(this);
        }
    }
}
