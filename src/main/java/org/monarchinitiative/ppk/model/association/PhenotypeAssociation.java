package org.monarchinitiative.ppk.model.association;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.monarchinitiative.ppk.model.condition.Phenotype;
import org.monarchinitiative.ppk.model.entity.Entity;
import org.monarchinitiative.ppk.model.meta.Evidence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@JsonDeserialize(builder = PhenotypeAssociation.Builder.class)
public class PhenotypeAssociation implements Association {
	
	private final Phenotype phenotype;
	private final Entity entity;
	private final List<Evidence> evidence;

    private PhenotypeAssociation(Builder builder) {
        this.phenotype = builder.phenotype;
        this.entity = builder.entity;
        this.evidence = Collections.unmodifiableList(builder.evidence);
    }

	public Phenotype getPhenotype() {
		return phenotype;
	}

	@Override
    public Entity getEntity() {
		return entity;
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
                Objects.equals(entity, that.entity) &&
                Objects.equals(evidence, that.evidence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phenotype, entity, evidence);
    }

    @Override
    public String toString() {
        return "PhenotypeAssociation{" +
                "phenotype=" + phenotype +
                ", entity=" + entity +
                ", evidence=" + evidence +
                '}';
    }

    public static class Builder {

        private final Phenotype phenotype;
        @JsonProperty
        private Entity entity;
        @JsonProperty
        private List<Evidence> evidence = new ArrayList<>();

        @JsonCreator
        public Builder(@JsonProperty("phenotype") Phenotype phenotype) {
            this.phenotype = phenotype;
        }

        public Builder setEntity(Entity entity) {
            this.entity = entity;
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
