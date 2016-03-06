package org.monarchinitiative.ppk.model.condition;

import org.monarchinitiative.ppk.model.meta.Association;

public class PhenotypeAssociation extends Association {
	
	private Phenotype phenotype;

	/**
	 * @return the phenotype
	 */
	public Phenotype getPhenotype() {
		return phenotype;
	}

	/**
	 * @param phenotype the phenotype to set
	 */
	public void setPhenotype(Phenotype phenotype) {
		this.phenotype = phenotype;
	}
	
	

}
