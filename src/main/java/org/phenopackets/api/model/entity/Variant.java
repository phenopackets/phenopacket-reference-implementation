package org.phenopackets.api.model.entity;

import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldType;

/**
 * 
 *  See https://github.com/phenopackets/phenopacket-format/issues/10
 *  
 *  
 * @author cjm
 *
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
	String descriptionHGVS;

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
	
	

}
