package org.phenopackets.api.model.condition;

import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldType;
import org.phenopackets.api.model.ontology.ClassInstance;

/**
 * An instance of a type of assay that was performed to determine the presence or extent of
 * a phenotype
 *
 * @author cjm
 */
@JsonldType("http://purl.obolibrary.org/obo/OBI_0000070")
public class Assay extends ClassInstance {

}
