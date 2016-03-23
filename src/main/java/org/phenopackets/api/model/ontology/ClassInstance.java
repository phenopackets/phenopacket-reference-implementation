package org.phenopackets.api.model.ontology;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class for anything that can be described as a boolean combination of ontology classes
 *
 * @author cjm
 */
public abstract class ClassInstance {

    @JsonProperty("types")
    @JsonPropertyDescription("Any instance can be positively described as the intersection of any number of ontology classes.")
    List<OntologyClass> types;

    @JsonProperty("negated_types")
    @JsonPropertyDescription("Any instance can be assigned any number of negative classes.")
    List<OntologyClass> negatedTypes;

    @JsonProperty("description")
    @JsonPropertyDescription("An optional free text description that can enhance the ontology class based description")
    String description;

    /**
     * @return the classList
     */
    public List<OntologyClass> getTypes() {
        return types;
    }

    /**
     * @param classes the classList to set
     */
    public void setTypes(List<OntologyClass> classes) {
        this.types = ImmutableList.copyOf(classes);
    }

    /**
     * @return the negatedClassList
     */
    public List<OntologyClass> getNegatedTypes() {
        return negatedTypes;
    }

    /**
     * @param negatedClasses the negatedClassList to set
     */
    public void setNegatedTypes(List<OntologyClass> negatedClasses) {
        this.negatedTypes = ImmutableList.copyOf(negatedClasses);
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public static class Builder {
        private List<OntologyClass> typeList = new ArrayList<>();
        private List<OntologyClass> negatedTypeList;
        private String description;

        //TODO: enable this to work with negatedTypes too
        public Builder addType(String id) {
            OntologyClass c = new OntologyClass.Builder(id).build();
            typeList.add(c);
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }
    }

    public ClassInstance(Builder builder) {
        this.types = builder.typeList;
        this.negatedTypes = builder.negatedTypeList;
        this.description = builder.description;
    }

    public ClassInstance() {

    }

}
