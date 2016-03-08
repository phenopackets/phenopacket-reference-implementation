package org.monarchinitiative.ppk.model.condition;

import org.monarchinitiative.ppk.model.ontology.OntologyClass;

public class Phenotype extends Condition {
	
	public Phenotype(Phenotype.Builder builder) {
		super(builder);
	}

	public Phenotype() {
		super();
	}



	public static class Builder extends Condition.Builder {
		
		public Phenotype build() {
			return new Phenotype(this);
		}
	}

	
	
}
