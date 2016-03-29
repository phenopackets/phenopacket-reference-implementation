package org.phenopackets.api.model.ontology;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public class PropertyLiteralValue implements PropertyValue {

    @JsonProperty("property")
    @JsonPropertyDescription("the data property used")
    String property;
    
    // TODO - this is a string to accommodate protobuf
    String filler;

    /**
     * @return the property
     */
    public String getProperty() {
        return property;
    }

    /**
     * @param property the property to set
     */
    public void setProperty(String property) {
        this.property = property;
    }

    /**
     * @return the filler
     */
    public String getFiller() {
        return filler;
    }

    /**
     * @param filler the filler to set
     */
    public void setFiller(String filler) {
        this.filler = filler;
    }

    @JsonIgnore
    public Double getTest() {
        return 0.0;
    }
}
