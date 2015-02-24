package com.ita.softserveinc.achiever.tool;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateParser {
	
	public Date parseDate(String date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate = null;
		dateFormat.setLenient(false);
		try {
			parsedDate = dateFormat.parse(date);
		} catch (ParseException e) {
			return null;
		}
		long time = parsedDate.getTime();
		return new Date(time);
	}
	
	public boolean isValidDates(Date startTime, Date endTime) {
		if ((startTime == null) || (endTime == null)) {
			return false;
		}
		if ((endTime.before(startTime)) || (endTime.equals(startTime))) {
			return false;
		}
		return true;
	}

}
