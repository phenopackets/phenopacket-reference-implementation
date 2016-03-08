package org.monarchinitiative.ppk.model.organism;

import org.monarchinitiative.ppk.model.meta.Entity;
import org.monarchinitiative.ppk.model.ontology.OntologyClass;

/**
 * @author cjm
 *
 */
public class Organism extends Entity {
	
	private OntologyClass taxon;
	private OntologyClass strain;
	
	/**
	 * @return the taxon
	 */
	public OntologyClass getTaxon() {
		return taxon;
	}
	/**
	 * @param taxon the taxon to set
	 */
	public void setTaxon(OntologyClass taxon) {
		this.taxon = taxon;
	}
	/**
	 * @return the strain
	 */
	public OntologyClass getStrain() {
		return strain;
	}
	/**
	 * @param strain the strain to set
	 */
	public void setStrain(OntologyClass strain) {
		this.strain = strain;
	}
	
	
	

}
