package org.phenopackets.api.model.entity;

import java.util.Objects;

import org.phenopackets.api.model.ontology.OntologyClass;

import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldType;

/**
 * See https://github.com/phenopackets/phenopacket-format/issues/10
 *
 * @author cjm
 */

@JsonldType("http://purl.obolibrary.org/obo/SO_0001059")
public class Variant extends GenomicEntity {

    @Override
    public EntityType getType() {
        return EntityType.VARIANT;
    }

    /**
     * This is highly preliminary, follow https://github.com/phenopackets/phenopacket-format/issues/10
     */
    private String descriptionHGVS;

    private String chromosome;

    private String assembly;

    private String isoform;

    private String exonId;

    private int startPosition;

    private int endPosition;

    private String refBases;

    private String altBases;

    /**
     * SO class denoting the mutation type (e.g., frameshift, insertion, etc)
     */
    private OntologyClass mutationType;

    /**
     * @return the descriptionHGVS
     */
    public String getDescriptionHGVS() {
        return descriptionHGVS;
    }

    /**
     * @param descriptionHGVS the descriptionHGVS to set
     */
    public void setDescriptionHGVS(String descriptionHGVS) {
        this.descriptionHGVS = descriptionHGVS;
    }

	public String getChromosome() {
		return chromosome;
	}

	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}

	public String getAssembly() {
		return assembly;
	}

	public void setAssembly(String assembly) {
		this.assembly = assembly;
	}

	public String getIsoform() {
		return isoform;
	}

	public void setIsoform(String isoform) {
		this.isoform = isoform;
	}

	public String getExonId() {
		return exonId;
	}

	public void setExonId(String exonId) {
		this.exonId = exonId;
	}

	public int getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}

	public int getEndPosition() {
		return endPosition;
	}

	public void setEndPosition(int endPosition) {
		this.endPosition = endPosition;
	}

	public String getRefBases() {
		return refBases;
	}

	public void setRefBases(String refBases) {
		this.refBases = refBases;
	}

	public String getAltBases() {
		return altBases;
	}

	public void setAltBases(String altBases) {
		this.altBases = altBases;
	}

	public OntologyClass getMutationType() {
		return mutationType;
	}

	public void setMutationType(OntologyClass mutationType) {
		this.mutationType = mutationType;
	}
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variant that = (Variant) o;
        return Objects.equals(descriptionHGVS, that.descriptionHGVS) &&
                Objects.equals(chromosome, that.chromosome) &&
                Objects.equals(assembly, that.assembly) &&
                Objects.equals(isoform, that.isoform) &&
                Objects.equals(exonId, that.exonId) &&
                Objects.equals(startPosition, that.startPosition) &&
                Objects.equals(endPosition, that.endPosition) &&
                Objects.equals(refBases, that.refBases) &&
                Objects.equals(altBases, that.altBases) &&
                Objects.equals(mutationType, that.mutationType);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(descriptionHGVS,
                chromosome,
                assembly,
                isoform,
                exonId,
                startPosition,
                endPosition,
                refBases,
                altBases,
                mutationType);
    }

    @Override
    public String toString() {
        return "Variant{" +
                "descriptionHGVS='" + descriptionHGVS + '\'' +
                ", chromosome='" + chromosome + '\'' +
                ", assembly='" + assembly + '\'' +
                ", isoform='" + isoform + '\'' +
                ", exonId='" + exonId + '\'' +
                ", startPosition='" + startPosition + '\'' +
                ", endPosition='" + endPosition + '\'' +
                ", refBases='" + refBases + '\'' +
                ", altBases='" + altBases + '\'' +
                ", mutationType='" + mutationType + '\'' +
                '}';
    }
}
