package com.ita.softserveinc.achiever.tool;

public final class Consts {
	
	public static final String CSV_FILENAME = "events.csv";
	public static final String DATETIME_PATTERN = "dd-MM-yyyy HH:ss";
	public static final String FULL_DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	public static final String DATETIME_PATTERN_WITHOUT_TIME = "dd-MM-yyyy";
	public static final String[] CSV_HEADER = {"StartTime", "EndTime", "Group", "Direction", "Location"};
	public static final int BUFFER_SIZE = 4096;
	
}
