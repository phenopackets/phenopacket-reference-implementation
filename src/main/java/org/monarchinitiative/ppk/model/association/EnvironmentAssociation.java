package org.monarchinitiative.ppk.model.association;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import org.monarchinitiative.ppk.model.entity.Entity;
import org.monarchinitiative.ppk.model.environment.Environment;
import org.monarchinitiative.ppk.model.meta.Evidence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * An association between an entity (person, disease, etc) and an environment
 * 
 * @author cjm
 *
 */
public class EnvironmentAssociation implements Association {
	
	@JsonPropertyDescription("The environment which this association is about")
	private final Environment environment;

	private final Entity entity;
	private final List<Evidence> evidence;

	private EnvironmentAssociation(Builder builder) {
		this.environment = builder.environment;
		this.entity = builder.entity;
		this.evidence = Collections.unmodifiableList(builder.evidence);
	}
	/**
	 * @return the environment
	 */
	public Environment getEnvironment() {
		return environment;
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
		EnvironmentAssociation that = (EnvironmentAssociation) o;
		return Objects.equals(environment, that.environment) &&
				Objects.equals(entity, that.entity) &&
				Objects.equals(evidence, that.evidence);
	}

	@Override
	public int hashCode() {
		return Objects.hash(environment, entity, evidence);
	}

	@Override
	public String toString() {
		return "EnvironmentAssociation{" +
				"environment=" + environment +
				", entity=" + entity +
				", evidence=" + evidence +
				'}';
	}

	public static class Builder {

		private final Environment environment;
		private Entity entity;
		private List<Evidence> evidence = new ArrayList<>();

		public Builder(Environment environment) {
			this.environment = environment;
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

		public EnvironmentAssociation build() {
			return new EnvironmentAssociation(this);
		}
	}

}
