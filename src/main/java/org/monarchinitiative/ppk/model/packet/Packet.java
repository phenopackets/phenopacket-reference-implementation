package org.monarchinitiative.ppk.model.packet;

import java.util.ArrayList;
import java.util.List;

import org.monarchinitiative.ppk.model.condition.DiseaseOccurrenceAssociation;
import org.monarchinitiative.ppk.model.condition.PhenotypeAssociation;
import org.monarchinitiative.ppk.model.meta.Association;
import org.monarchinitiative.ppk.model.meta.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Packet {
	
	//TODO - have a distinct list for each type
	@JsonProperty("entities")
	private List<Entity> entities;
	
	@JsonProperty("phenotype_profile")
	private List<PhenotypeAssociation> phenotypeAssociationList;
	
	@JsonProperty("diagnosis_profile")
	private List<DiseaseOccurrenceAssociation> diseaseOccurrenceAssociationList;

	/**
	 * @return the entities
	 */
	public List<Entity> getEntities() {
		return entities;
	}

	/**
	 * @param entities the entities to set
	 */
	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

	public void addEntity(Entity entity) {
		if (entities == null)
			entities = new ArrayList<Entity>();
		entities.add(entity);
	}

	/**
	 * @return the phenotype_profile
	 */
	@JsonProperty("phenotype_profile")
	public List<PhenotypeAssociation> getPhenotypeAssociationList() {
		return phenotypeAssociationList;
	}

	/**
	 * @param phenotype_profile the phenotype_profile to set
	 */
	@JsonProperty("phenotype_profile")
	public void setPhenotypeAssociationList(List<PhenotypeAssociation> phenotype_profile) {
		this.phenotypeAssociationList = phenotype_profile;
	}

	@JsonProperty("phenotype_profile")
	public void addPhenotypeAssociation(PhenotypeAssociation a) {
		if (phenotypeAssociationList == null)
			phenotypeAssociationList = new ArrayList<PhenotypeAssociation>();
		phenotypeAssociationList.add(a);
	}

	
	

}
