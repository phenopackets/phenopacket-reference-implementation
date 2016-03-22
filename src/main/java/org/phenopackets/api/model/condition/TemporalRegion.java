package org.phenopackets.api.model.condition;


import org.phenopackets.api.model.ontology.ClassInstance;

/**
 * A temporal region is an instance of any time interval, marked by a start boundary and
 * an end boundary.
 * 
 * A temporal region can be recorded by any combination of:
 * 
 *  - ontology classes (for example, anything from the 'age of onset' branch of HP)
 *  - start and end timestamps
 * 
 * @author cjm
 *
 */
public class TemporalRegion extends ClassInstance {
	
	private String startTime;
	private String endTime;

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
	
}
