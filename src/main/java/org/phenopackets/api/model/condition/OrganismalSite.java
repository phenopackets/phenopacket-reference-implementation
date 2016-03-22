package org.phenopackets.api.model.condition;

import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldType;
import org.phenopackets.api.model.ontology.ClassInstance;

/**
 * An instance of a particular site on or in an organism. This may be
 * a whole organ, a cell type or even a subcellular location.
 * 
 * The type fields for this class are typically drawn from ontologies such
 * as Uberon and CL.
 * 
 * @author cjm
 *
 */
@JsonldType("http://purl.obolibrary.org/obo/CARO_0000000")
public class OrganismalSite extends ClassInstance {

}
