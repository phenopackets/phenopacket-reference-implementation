package org.monarchinitiative.ppk.model.genome;

import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldType;

import org.monarchinitiative.ppk.model.meta.Entity;
import org.monarchinitiative.ppk.model.ontology.OntologyClass;

/**
 * @author cjm
 *
 */

@JsonldType("http://purl.obolibrary.org/obo/SO_0000000")
public class GenomicEntity extends Entity {
	
	private OntologyClass taxon;


}
