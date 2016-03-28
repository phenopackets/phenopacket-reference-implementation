package org.phenopackets.api.model.condition;

import java.util.List;

import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldType;

import org.phenopackets.api.model.ontology.ClassInstance;
import org.phenopackets.api.model.ontology.OntologyClass;
import org.phenopackets.api.model.ontology.PropertyLiteralValue;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

/**
 * Represents a measurement of a phenotype or trait
 * 
 * @author cjm
 *
 */
@JsonldType("http://purl.obolibrary.org/obo/IAO_0000416")
public class Measurement extends ClassInstance {

    @JsonProperty("unit")
    @JsonPropertyDescription("the unit of measurement")
    private String unit;

    // TODO - protobuf does not allow generic Numbers
    //  encoding an int as a double is not ideal.
    //  we could potentially have the serialization allow two different properties
    @JsonProperty("value")
    @JsonPropertyDescription("the value of the measurement")
    private String value;


    @JsonProperty("property_values")
    @JsonPropertyDescription("a list of (property,value) pairs")
    private List<PropertyLiteralValue> propertyValues;

    // TODO - trait

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the magnitude to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the propertyValues
     */
    public List<PropertyLiteralValue> getPropertyValues() {
        return propertyValues;
    }

    /**
     * @param propertyValues the propertyValues to set
     */
    public void setPropertyValues(List<PropertyLiteralValue> propertyValues) {
        this.propertyValues = propertyValues;
    }



}
