package org.phenopackets.api.model.condition;

import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldType;
import org.phenopackets.api.model.ontology.ClassInstance;

/**
 * the frequency with which the phenotype occurs within a given group.
 * <br/>
 * related to the concept of penetrance, see http://www.ncbi.nlm.nih.gov/books/NBK22090/
 * <br/>
 * Note that this should only be used in associations with groups, rather than individuals
 *
 * @author cjm
 */

@JsonldType("http://purl.obolibrary.org/obo/PATO_0000044")
public class ConditionFrequency extends ClassInstance {

}
