package org.phenopackets.api.model.entity;

import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldType;
import org.phenopackets.api.model.ontology.OntologyClass;

/**
 * @author cjm
 *
 */

@JsonldType("http://purl.obolibrary.org/obo/SO_0000000")
public class GenomicEntity implements Entity {

	private String id;
	private String label;

	private OntologyClass taxon;

	public void setId(String id) {
		this.id = id;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setTaxon(OntologyClass taxon) {
		this.taxon = taxon;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public EntityType getType() {
		//TODO - this isn't right...
		return EntityType.GENOTYPE;
	}
}
