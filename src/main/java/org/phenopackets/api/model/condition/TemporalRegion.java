package org.phenopackets.api.model.condition;


import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.phenopackets.api.model.ontology.ClassInstance;
import org.phenopackets.api.util.DateTimeUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

/**
 * A temporal region is an instance of any time interval, marked by a start boundary and
 * an end boundary.
 * 
 * A temporal region can be recorded by any combination of:
 * 
 *  - ontology classes (for example, anything from the 'age of onset' branch of HP)
 *  - start and end timestamps
 *  
 *  
 * Note that two TemporalRegions can be combined to give a duration. For example,
 * if a phenotype has an onset of TR1, and ceases during TR2,
 * then this can be visualized:
 * 
 * <pre>
 * 
 *     tr1s      tr1e                     tr2s                 tr2e
 *     |          |                        |                    |
 *     |          |                        |                    |
 *     [    TR1   ]>>>>>>>>>>>>>>>>>>>>>>>>[         TR2        ]
 * </pre>
 * 
 * Where tr1s is the {@link TemporalRegion.getStartTime()} of TR1, etc.
 * Note that the duration the organism has the phenotype is <code>tr2e-tr1s</code>
 * 
 * This allows us an arbitrary degree of fuzziness when recording onset and offset.
 * 
 * If all we know is that the {@link Condition} started at some point during the postnatal
 * period, we can have a large temporal extent for TR1 (or we can simply describe TR1
 * using the HPO class for postnatal onset). Alternatively, if we know the precise moment
 * at which the phenotype started, we can create an extent of 0, with tr1s==tr2s
 * 
 * 
 * @author cjm
 *
 */
public class TemporalRegion extends ClassInstance {
	
	@JsonProperty("start_time")
	@JsonPropertyDescription("the date or time at which the interval starts")
	private String startTime;
	
	@JsonProperty("end_time")
	@JsonPropertyDescription("the date or time at which the interval ends")
	private String endTime;

	/**
	 * @return the leftmost boundary of this temporal region
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
	 * @return the rightmost boundary of this temporal region
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
