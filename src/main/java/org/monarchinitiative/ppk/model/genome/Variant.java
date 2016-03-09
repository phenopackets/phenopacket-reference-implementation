package org.monarchinitiative.ppk.model.genome;

import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldType;

import org.monarchinitiative.ppk.model.meta.Entity;

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
