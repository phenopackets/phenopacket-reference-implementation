package org.monarchinitiative.ppk.model.packet;

import java.util.ArrayList;
import java.util.List;

import org.monarchinitiative.ppk.model.meta.Association;
import org.monarchinitiative.ppk.model.meta.Entity;

public class Packet {
	
	private List<Entity> entities;
	
	private List<PhenotypeAssociation> phenotype_profile;

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
	public List<PhenotypeAssociation> getPhenotype_profile() {
		return phenotype_profile;
	}

	/**
	 * @param phenotype_profile the phenotype_profile to set
	 */
	public void setPhenotype_profile(List<PhenotypeAssociation> phenotype_profile) {
		this.phenotype_profile = phenotype_profile;
	}

	public void addPhenotypeAssociation(PhenotypeAssociation a) {
		if (phenotype_profile == null)
			phenotype_profile = new ArrayList<PhenotypeAssociation>();
		phenotype_profile.add(a);
	}

	
	

}
