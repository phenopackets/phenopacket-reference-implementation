package org.monarchinitiative.ppk.model.condition;


/**
 * An individual occurrence of a phenotype (a type of condition)
 * 
 * @author cjm
 *
 */
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
