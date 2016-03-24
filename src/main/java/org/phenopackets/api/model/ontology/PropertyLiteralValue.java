package org.phenopackets.api.model.ontology;

public class PropertyLiteralValue implements PropertyValue {

    String property;
    Object filler;

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
    public Object getFiller() {
        return filler;
    }

    /**
     * @param filler the filler to set
     */
    public void setFiller(Object filler) {
        this.filler = filler;
    }

}
