package org.monarchinitiative.ppk.model.environment;

import org.monarchinitiative.ppk.model.meta.Association;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

/**
 * An association between an entity (person, disease, etc) and an environment
 * 
 * @author cjm
 *
 */
public class EnvironmentAssociation extends Association {
	
	@JsonPropertyDescription("The environment which this association is about")
	private Environment environment;

	/**
	 * @return the environment
	 */
	public Environment getEnvironment() {
		return environment;
	}

	/**
	 * @param environment the environment to set
	 */
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
	

}
