package org.phenopackets.api.model.association;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import org.phenopackets.api.model.environment.Environment;
import org.phenopackets.api.model.meta.Evidence;

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

	private final String entityId;
	private final List<Evidence> evidence;

	private EnvironmentAssociation(Builder builder) {
		this.environment = builder.environment;
		this.entityId = builder.entityId;
		this.evidence = Collections.unmodifiableList(builder.evidence);
	}
	/**
	 * @return the environment
	 */
	public Environment getEnvironment() {
		return environment;
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
		EnvironmentAssociation that = (EnvironmentAssociation) o;
		return Objects.equals(environment, that.environment) &&
				Objects.equals(entityId, that.entityId) &&
				Objects.equals(evidence, that.evidence);
	}

	@Override
	public int hashCode() {
		return Objects.hash(environment, entityId, evidence);
	}

	@Override
	public String toString() {
		return "EnvironmentAssociation{" +
				"environment=" + environment +
				", entityId=" + entityId +
				", evidence=" + evidence +
				'}';
	}

	public static class Builder {

		private final Environment environment;
		private String entityId;
		private List<Evidence> evidence = new ArrayList<>();

		public Builder(Environment environment) {
			this.environment = environment;
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

		public EnvironmentAssociation build() {
			return new EnvironmentAssociation(this);
		}
	}

}
