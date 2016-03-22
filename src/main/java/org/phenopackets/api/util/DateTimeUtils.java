package org.phenopackets.api.util;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.phenopackets.api.model.condition.Condition;
import org.phenopackets.api.model.condition.TemporalRegion;

/**
 * 
 *
 * see https://github.com/phenopackets/phenopacket-reference-implementation/issues/10
 * 
 * @author cjm
 *
 */
public class DateTimeUtils {
	
	static String[] patterns = {
			"yyyy-MM-dd",
			"yyyy-MM-dd HH:mm:ss" //$NON-NLS-1$
	};
	
 	
	/**
	 * @param dateString, ISO-8601
	 * @return date object
	 * @throws ParseException
	 */
	public static Date parseDateString(String dateString) throws ParseException {
		return DateUtils.parseDate(dateString, patterns);
	}
	
	
	/**
	 * @param d
	 * @return
	 */
	public static LocalDate dateToLocalDate(Date d) {
		return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	/**
	 * @param tr 
	 * @return the rightmost boundary of this temporal region, as a java date
	 * @throws ParseException
	 */
	public static Date getStartTimeAsDate(TemporalRegion tr) throws ParseException {
		return DateTimeUtils.parseDateString(tr.getStartTime());
	}
	
	/**
	 * @param tr 
	 * @return the rightmost boundary of this temporal region, as a java date
	 * @throws ParseException
	 */
	public static Date getEndTimeAsDate(TemporalRegion tr) throws ParseException {
		return DateTimeUtils.parseDateString(tr.getEndTime());
	}


	/**
	 * @param tr
	 * @return
	 * @throws ParseException
	 */
	public static LocalDate getStartTimeAsLocalDate(TemporalRegion tr) throws ParseException {
		return DateTimeUtils.dateToLocalDate(getStartTimeAsDate(tr));
	}
	/**
	 * @param tr
	 * @return
	 * @throws ParseException
	 */
	public static LocalDate getEndTimeAsLocalDate(TemporalRegion tr) throws ParseException {
		return DateTimeUtils.dateToLocalDate(getEndTimeAsDate(tr));
	}

	/**
	 * Note that this is not the period of the condition; it is the period of an
	 * individual temporal region. See {@link Condition.getDuration()} for duration
	 * @param tr 
	 * 
	 * @return period between start and end
	 * @throws ParseException
	 */
	public static Period getPeriod(TemporalRegion tr) throws ParseException {
		return Period.between(getStartTimeAsLocalDate(tr), getEndTimeAsLocalDate(tr));
	}
	
	/**
	 * @param condition
	 * @return period between lowest estimate of onset start and uppermost estimate of osset
	 * @throws ParseException
	 */
	public static Period getDuration(Condition condition) throws ParseException {
		return Period.between(getStartTimeAsLocalDate(condition.getTimeOfOnset()),
				getEndTimeAsLocalDate(condition.getTimeOfFinishing()));
	}
	

}
