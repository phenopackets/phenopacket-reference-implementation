package org.phenopackets.api.model.condition;

import java.util.List;

import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldType;

import org.phenopackets.api.model.ontology.ClassInstance;
import org.phenopackets.api.model.ontology.OntologyClass;
import org.phenopackets.api.model.ontology.PropertyLiteralValue;

@JsonldType("http://purl.obolibrary.org/obo/IAO_0000416")
public class Measurement extends ClassInstance {

    private OntologyClass unit;
    private double value;
    private List<PropertyLiteralValue> propertyValues;
    
    // TODO - trait

    /**
     * @return the unit
     */
    public OntologyClass getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(OntologyClass unit) {
        this.unit = unit;
    }

    /**
     * @return the magnitude
     */
    public double getValue() {
        return value;
    }

    /**
     * @param magnitude the magnitude to set
     */
    public void setValue(double magnitude) {
        this.value = magnitude;
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
