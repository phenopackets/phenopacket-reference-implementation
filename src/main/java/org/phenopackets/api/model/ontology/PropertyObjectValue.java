package org.phenopackets.api.model.ontology;

public class PropertyObjectValue implements PropertyValue {

    String property;
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


}
