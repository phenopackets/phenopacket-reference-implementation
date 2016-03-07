package org.monarchinitiative.ppk.model.person;

import org.monarchinitiative.ppk.model.meta.Entity;

/**
 * @author cjm
 *
 */
public class Person extends Entity {
	
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
