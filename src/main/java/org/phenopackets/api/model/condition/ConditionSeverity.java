package org.phenopackets.api.model.condition;

import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldType;
import org.phenopackets.api.model.ontology.ClassInstance;

/**
 * the degree to which the phenotype or disease is manifest.
 * <p/>
 * related to the concept of expressivity, see http://www.ncbi.nlm.nih.gov/books/NBK22090/
 * <p/>
 * Note that this is modeled as in instance of a severity class, in which the class is taken
 * from an ontology such as PATO or HPO. Each condition has its own unique severity instance.
 * 
 * In humans, all instances of this should be of of type http://purl.obolibrary.org/obo/HP_0012824
 * 
 * For other species, the generic http://purl.obolibrary.org/obo/PATO_0000049 can be used
 *
 * @author cjm
 */

@JsonldType("http://purl.obolibrary.org/obo/PATO_0000049")
public class ConditionSeverity extends ClassInstance {

}
