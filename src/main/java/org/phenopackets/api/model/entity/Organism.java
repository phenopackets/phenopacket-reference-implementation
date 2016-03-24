package org.phenopackets.api.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import org.phenopackets.api.model.ontology.ClassInstance;
import org.phenopackets.api.model.ontology.OntologyClass;

/**
 * @author cjm
 */
public class Organism extends ClassInstance implements Entity {

    private String id;
    private String label;

    @JsonProperty("taxon")
    @JsonPropertyDescription("points to an instance of the taxon to which this belongs")
    private OntologyClass taxon;
    private OntologyClass strain;

    @JsonProperty("sex")
    @JsonPropertyDescription("code for the biological sex of the organism. Mappings from codes are in JSON-LD context")
    private String sex;

    @JsonProperty("date_of_birth")
    private String dateOfBirth;


    public void setId(String id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public EntityType getType() {
        return EntityType.ORGANISM;
    }

    /**
     * @return the taxon
     */
    public OntologyClass getTaxon() {
        return taxon;
    }

    /**
     * @param taxon the taxon to set
     */
    public void setTaxon(OntologyClass taxon) {
        this.taxon = taxon;
    }

    /**
     * @return the strain
     */
    public OntologyClass getStrain() {
        return strain;
    }

    /**
     * @param strain the strain to set
     */
    public void setStrain(OntologyClass strain) {
        this.strain = strain;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the dateOfBirth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Organism{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", taxon=" + taxon +
                ", strain=" + strain +
                ", sex='" + sex + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }
}
