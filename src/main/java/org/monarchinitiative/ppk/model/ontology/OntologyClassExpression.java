package org.monarchinitiative.ppk.model.ontology;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Either a named ontology class or a boolean combination of ontology classes
 * 
 * In level 1, this is restricted to:
 * 
 * <code>
 * CE = C | not C 
 * </code>
 * 
 * See https://github.com/phenopackets/phenopacket-format/issues/20
 * 
 * In future levels we may want to allow bundled phenotypes, e.g.
 * 
 * <code>
 * CE = C | not C | CE and ... and CE
 * <code>
 * 
 * For the ZFIN use case, we need to allow class retsirctions. However, we will likely
 * go a templating route rather than full OWL
 * 
 * Note that ideally the object model would have named classes being a subclass of class expression;
 * However, until this is implemented https://github.com/FasterXML/jackson-module-jsonSchema/issues/5
 * A more json-schema friendly approach is required
 * 
 * see also http://programmerbruce.blogspot.com/2011/05/deserialize-json-with-jackson-into.html
 * 
 * https://github.com/joelittlejohn/jsonschema2pojo/issues/392
 * 
 * @author cjm
 *
 */

@JsonTypeInfo(  
	    use = JsonTypeInfo.Id.NAME,  
	    include = JsonTypeInfo.As.PROPERTY,  
	    property = "type")  
	@JsonSubTypes({  
	    @Type(value = OntologyClass.class, name = "ontology_class"),  
	    @Type(value = OntologyNegatedClassExpression.class, name = "complement") }) 
public class OntologyClassExpression {
	
	String description;
	

}
