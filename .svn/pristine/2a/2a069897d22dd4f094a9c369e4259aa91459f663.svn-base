package com.ita.softserveinc.achiever.tool;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import com.ita.softserveinc.achiever.dao.IEventDao;
import com.ita.softserveinc.achiever.entity.Event;
import com.ita.softserveinc.achiever.exception.ElementExistsException;
import com.ita.softserveinc.achiever.service.EventServiceImpl;
import com.ita.softserveinc.achiever.service.IEventService;
import com.ita.softserveinc.achiever.service.IGroupService;
import com.ita.softserveinc.achiever.service.ILocationService;

/**
 * 
 * @author Vasyl Zazuliak
 * 
 */
@Component
public class ScheduleParser {

	@Autowired
	private IGroupService groupService;
	@Autowired
	private ILocationService locationService;
	@Autowired
	private IEventService eventService;
	@Autowired
	private IEventDao eventdao;
	@Autowired
	private DateParser dateParser;

	private static final Logger logger = LoggerFactory
			.getLogger(EventServiceImpl.class);

	/*
	 * Receive List of events and write to CSV file
	 */
	public void readData(List<Event> events) throws IOException {

		String csv = Consts.CSV_FILENAME;
		CSVWriter csvWriter = new CSVWriter(new FileWriter(csv));
		List<String[]> data = toStringArray(events);
		csvWriter.writeAll(data);
		csvWriter.close();
	}

	/*
	 * Create CSV file. Write file header with name of parameters. Get
	 * parameters from each Event, set parameters to String array and write to
	 * CSV
	 */
	public List<String[]> toStringArray(List<Event> events) {
		List<String[]> records = new ArrayList<String[]>();
		// add header record
		records.add(Consts.CSV_HEADER);
		Iterator<Event> it = events.iterator();
		while (it.hasNext()) {
			Event event = it.next();
			records.add(new String[] { event.getStartDatetime().toString(Consts.DATETIME_PATTERN),
					event.getEndDatetime().toString(Consts.DATETIME_PATTERN),
					event.getGroup().getGroupName(),
					event.getGroup().getDirection().getName(),
					event.getLocation().getName() });
		}
		return records;
	}

	/*
	 * Write data from CSV file to datebase line by line
	 */
	public void parseCSVFileLineByLine() throws IOException {
		// create CSVReader object
		CSVReader reader = new CSVReader(new FileReader(Consts.CSV_FILENAME),
				',');

		// read line by line
		String[] record = null;
		// skip header row
		reader.readNext();

		while ((record = reader.readNext()) != null) {
			Event event = new Event();
			event.setEndDatetime(DateTime.parse(record[1],
					DateTimeFormat.forPattern(Consts.DATETIME_PATTERN)));
			event.setStartDatetime(DateTime.parse(record[0],
					DateTimeFormat.forPattern(Consts.DATETIME_PATTERN)));
			event.setGroup(groupService.findByName(record[2]));
			event.setLocation(locationService.findByName(record[4]));
			try {
				eventService.create(event);
			} catch (ElementExistsException e) {
				logger.info("Event doesn't created");
			}
		}
		reader.close();
	}
}
