package org.phenopackets.api;

import static com.google.common.base.Preconditions.checkNotNull;

import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldId;
import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldProperty;

import java.util.ArrayList;
import java.util.List;

import org.phenopackets.api.model.association.DiseaseOccurrenceAssociation;
import org.phenopackets.api.model.association.EnvironmentAssociation;
import org.phenopackets.api.model.association.PhenotypeAssociation;
import org.phenopackets.api.model.association.VariantAssociation;
import org.phenopackets.api.model.entity.Disease;
import org.phenopackets.api.model.entity.Organism;
import org.phenopackets.api.model.entity.Person;
import org.phenopackets.api.model.entity.Variant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.ImmutableList;

/**
 * Top level container
 *
 * @author cjm
 * @author Jules Jacobsen <jules.jacobsen@sanger.ac.uk>
 */
@JsonDeserialize(builder = PhenoPacket.Builder.class)
@JsonPropertyOrder({"id", "title"})
@JsonFilter("PhenoPacketClass")
public class PhenoPacket {

    @JsonldId
    @JsonProperty("id")
    private final String id;

    @JsonldProperty("http://purl.org/dc/elements/1.1/title")
    @JsonProperty("title")
    private final String title;
    
    /**
     * The JSON-LD context for this document. This needs to be an unstructured
     * Object, since it could be either a list or a map. We don't want to store
     * it here as a Context because we want to roundtrip it the way it is written
     * in the document.
     */
    @JsonProperty("@context")
    private final Object context;

    /*
     * due to typing in yaml not using a 'type' fields in the same way as json
     * this doesn't serialise and deserialise in the way we want to represent the entities,
     * so we need to specify them explicitly in distinct lists. Plus it makes the API cleaner
     * as you had to add an entity twice.
     */

    // ---- ENTITIES ----
    private final List<Person> persons;
    private final List<Organism> organisms;
    private final List<Variant> variants;
    private final List<Disease> diseases;

    // ---- PROFILES/ASSOCIATIONS ----
    private final List<PhenotypeAssociation> phenotypeAssociations;
    private final List<DiseaseOccurrenceAssociation> diseaseOccurrenceAssociations;
    private final List<VariantAssociation> variantAssociations;
    private final List<EnvironmentAssociation> environmentAssociations;

    private PhenoPacket(final Builder builder) {
        id = builder.id;
        title = builder.title;
        context = builder.context;

        variants = ImmutableList.copyOf(builder.variants);
        persons = ImmutableList.copyOf(builder.persons);
        organisms = ImmutableList.copyOf(builder.organisms);
        diseases = ImmutableList.copyOf(builder.diseases);

        phenotypeAssociations = ImmutableList.copyOf(builder.phenotypeAssociations);
        diseaseOccurrenceAssociations = ImmutableList.copyOf(builder.diseaseOccurrenceAssociations);
        environmentAssociations = ImmutableList.copyOf(builder.environmentAssociations);
        variantAssociations = ImmutableList.copyOf(builder.variantAssociations);
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * @return the local context
     */
    @JsonInclude(Include.NON_EMPTY)
    public Object getContext() {
    	return context;
    }

    /**
     * @return the variants
     */
    @JsonInclude(Include.NON_EMPTY)
    public List<Variant> getVariants() {
        return variants;
    }

    /**
     * @return the persons
     */
    @JsonInclude(Include.NON_EMPTY)
    public List<Person> getPersons() {
        return persons;
    }

    /**
     * @return the organisms
     */
    @JsonInclude(Include.NON_EMPTY)
    public List<Organism> getOrganisms() {
        return organisms;
    }

    @JsonInclude(Include.NON_EMPTY)
    public List<Disease> getDiseases() {
        return diseases;
    }

    /**
     * @return the phenotype_profile
     */
    @JsonInclude(Include.NON_EMPTY)
    @JsonProperty("phenotype_profile")
    public List<PhenotypeAssociation> getPhenotypeAssociations() {
        return phenotypeAssociations;
    }

    /**
     * @return the diseaseOccurrenceAssociations
     */
    @JsonInclude(Include.NON_EMPTY)
    @JsonProperty("diagnosis_profile")
    public List<DiseaseOccurrenceAssociation> getDiseaseOccurrenceAssociations() {
        return diseaseOccurrenceAssociations;
    }

    @JsonInclude(Include.NON_EMPTY)
    @JsonProperty("environment_profile")
    public List<EnvironmentAssociation> getEnvironmentAssociations() {
        return environmentAssociations;
    }

    @JsonInclude(Include.NON_EMPTY)
    @JsonProperty("variant_profile")
    public List<VariantAssociation> getVariantAssociations() {
        return variantAssociations;
    }

    /**
     * Create and return a new phenopacket builder.
     *
     * @return a new phenopacket builder
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Create and return a new builder initialized from the fields in the specified phenopacket.
     *
     * @param phenoPacket phenopacket to initialize from, must not be null
     * @return a new phenopacket builder initialized from the fields in the specified phenopacket
     */
    public static Builder newBuilder(final PhenoPacket phenoPacket) {
        return new Builder(phenoPacket);
    }

    public static class Builder {
        @JsonProperty
        private String id;
        @JsonProperty
        private String title;
        @JsonProperty("@context")
        private Object context;

        // ---- ENTITIES ----
        @JsonProperty
        private List<Variant> variants;
        @JsonProperty
        private List<Person> persons;
        @JsonProperty
        private List<Organism> organisms;
        @JsonProperty
        private List<Disease> diseases;

        // ---- PROFILES/ASSOCIATIONS ----
        @JsonProperty("phenotype_profile")
        private List<PhenotypeAssociation> phenotypeAssociations;

        @JsonProperty("diagnosis_profile")
        private List<DiseaseOccurrenceAssociation> diseaseOccurrenceAssociations;

        @JsonProperty("environment_profile")
        private List<EnvironmentAssociation> environmentAssociations;

        @JsonProperty("variant_profile")
        private List<VariantAssociation> variantAssociations;

        @JsonCreator
        public Builder() {
            this.variants = new ArrayList<>();
            this.persons = new ArrayList<>();
            this.organisms = new ArrayList<>();
            this.diseases = new ArrayList<>();

            this.phenotypeAssociations = new ArrayList<>();
            this.diseaseOccurrenceAssociations = new ArrayList<>();
            this.environmentAssociations = new ArrayList<>();
            this.variantAssociations = new ArrayList<>();
        }

        /**
         * Create a new phenopacket builder initialized from the fields in the specified phenopacket.
         *
         * @param phenoPacket phenopacket to initialize from, must not be null
         */
        public Builder(final PhenoPacket phenoPacket) {
            checkNotNull(phenoPacket);
            this.id = phenoPacket.getId();
            this.title = phenoPacket.getTitle();
            this.context = phenoPacket.getContext();

            this.variants = ImmutableList.copyOf(phenoPacket.getVariants());
            this.persons = ImmutableList.copyOf(phenoPacket.getPersons());
            this.organisms = ImmutableList.copyOf(phenoPacket.getOrganisms());
            this.diseases = ImmutableList.copyOf(phenoPacket.getDiseases());

            this.phenotypeAssociations = ImmutableList.copyOf(phenoPacket.getPhenotypeAssociations());
            this.diseaseOccurrenceAssociations = ImmutableList.copyOf(phenoPacket.getDiseaseOccurrenceAssociations());
            this.environmentAssociations = ImmutableList.copyOf(phenoPacket.getEnvironmentAssociations());
            this.variantAssociations = ImmutableList.copyOf(phenoPacket.getVariantAssociations());
        }

        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public Builder title(final String title) {
            this.title = title;
            return this;
        }
        
        public Builder context(final Object context) {
        	this.context = context;
        	return this;
        }

        public Builder addVariant(final Variant variant) {
            variants.add(variant);
            return this;
        }

        /**
         * @param variants the variants to set
         */
        public Builder setVariants(final List<Variant> variants) {
            this.variants = variants;
            return this;
        }

        public Builder addPerson(final Person person) {
            persons.add(person);
            return this;
        }

        /**
         * @param persons the persons to set
         */
        public Builder setPersons(final List<Person> persons) {
            this.persons = persons;
            return this;
        }

        public Builder addOrganism(final Organism organism) {
            organisms.add(organism);
            return this;
        }

        /**
         * @param organisms the organisms to set
         */
        public Builder setOrganisms(final List<Organism> organisms) {
            this.organisms = organisms;
            return this;
        }

        public Builder addDisease(final Disease disease) {
            diseases.add(disease);
            return this;
        }

        public Builder setDiseases(final List<Disease> diseases) {
            this.diseases = diseases;
            return this;
        }

        public Builder addPhenotypeAssociation(final PhenotypeAssociation a) {
            phenotypeAssociations.add(a);
            return this;
        }

        public Builder addVariantAssociation(final VariantAssociation a) {
        	variantAssociations.add(a);
            return this;
        }

        /**
         * @param phenotypeAssociations the phenotype_profile to set
         */
        public Builder setPhenotypeAssociations(final List<PhenotypeAssociation> phenotypeAssociations) {
            this.phenotypeAssociations = phenotypeAssociations;
            return this;
        }

        public Builder setVariantAssociations(final List<VariantAssociation> variantAssociations) {
            this.variantAssociations = variantAssociations;
            return this;
        }

        public Builder addDiseaseOccurrenceAssociation(final DiseaseOccurrenceAssociation diseaseOccurrenceAssociation) {
            this.diseaseOccurrenceAssociations.add(diseaseOccurrenceAssociation);
            return this;
        }

        /**
         * @param diseaseOccurrenceAssociations the diseaseOccurrenceAssociationList to set
         */
        public Builder setDiseaseOccurrenceAssociations(final List<DiseaseOccurrenceAssociation> diseaseOccurrenceAssociations) {
            this.diseaseOccurrenceAssociations = diseaseOccurrenceAssociations;
            return this;
        }

        public Builder addEnvironmentAssociation(final EnvironmentAssociation environmentAssociation) {
            environmentAssociations.add(environmentAssociation);
            return this;
        }

        public Builder setEnvironmentAssociations(final List<EnvironmentAssociation> environmentAssociations) {
            this.environmentAssociations = environmentAssociations;
            return this;
        }

        public PhenoPacket build() {
            return new PhenoPacket(this);
        }
    }
}
