package org.monarchinitiative.ppk.model.condition;

import java.util.Date;

import org.monarchinitiative.ppk.model.ontology.ClassInstance;

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
	
	private Date startTime;
	private Date endTime;

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
	
}
