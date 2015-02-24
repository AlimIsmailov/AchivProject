package com.ita.softserveinc.achiever.service;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ita.softserveinc.achiever.dao.IEventDao;
import com.ita.softserveinc.achiever.entity.Event;
import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.Location;
import com.ita.softserveinc.achiever.exception.ElementExistsException;
import com.ita.softserveinc.achiever.tool.Consts;
import com.ita.softserveinc.achiever.paging.PagingCounter;
import com.ita.softserveinc.achiever.tool.EventFormBean;
import com.ita.softserveinc.achiever.tool.ScheduleParser;

/**
 * 
 * @author Vasyl Zazuliak
 * 
 */
@Service
public class EventServiceImpl implements IEventService {

	/** The event dao. */
	@Autowired
	private IEventDao eventDao;

	/** The group service. */
	@Autowired
	private IGroupService groupService;

	/** The location service. */
	@Autowired
	private ILocationService locationService;

	/** The user service. */
	@Autowired
	private IUserService userService;

	/** The parser of schedule. */
	@Autowired
	private ScheduleParser scheduleParser;

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory
			.getLogger(EventServiceImpl.class);

	/*
	 * create new Event in datebase
	 */
	@Transactional
	public void create(Event event) throws ElementExistsException {
		if (findNotExistEvent(event)) {
			eventDao.create(event);
		}
		logger.info("This element already exists!");
	}

	/*
	 * update exist Event in datebase
	 */
	@Transactional
	public Event update(Event event) {
		return findNotExistEvent(event) ? eventDao.update(event) : null;
	}

	/*
	 * Delete Event from datebase
	 */
	@Transactional
	public void delete(Event entity) {
		eventDao.delete(entity);
	}

	/*
	 * Find particular Event by ID in datebase
	 */
	@Override
	public Event findById(Long id) {
		return eventDao.findById(Event.class, id);
	}

	/*
	 * Find all Events in datebase
	 */
	@Override
	public List<Event> findAll() {
		return eventDao.findAll(Event.class);
	}

	/*
	 * Find Events in datebase by user between dates
	 */
	@Override
	public List<Event> findByUser(String login, DateTime start, DateTime end) {
		return eventDao.findEventsByUserBetweenDates(login, start, end);
	}

	/*
	 * Find exist Event for existance validation. Return true if Event not exist
	 * in datebase and false if it exist
	 */
	private boolean findNotExistEvent(Event event) {
		Location location = event.getLocation();
		DateTime start = event.getStartDatetime();
		DateTime end = event.getEndDatetime();
		return (eventDao.findExistEvent(location, start, end) == null);
	}

	/*
	 * Combine date and time from formBean and return full date
	 */
	@Override
	public DateTime convertDateTime(String eventDate, String time) {
		logger.info("EventDate on Service: " + eventDate);
		String subdate = eventDate.substring(eventDate.length() - 10);
		int substr = Integer.parseInt(time.substring(0, 2));
		DateTime tempdate = DateTime.parse(subdate,
				DateTimeFormat.forPattern(Consts.DATETIME_PATTERN_WITHOUT_TIME));
		DateTime dateTime = tempdate.withHourOfDay(substr);
		return dateTime;
	}

	/*
	 * Method receive from controller name of subject. Method return list of
	 * groups depending by subject (group, manager or location) In JSP modal for
	 * creation/update Event for particular subject, admin can't create/update
	 * Event for another group or non-user groups
	 */
	@Override
	public List<Group> findGroupForJsp(String scheduleParameter) {
		List<Group> groups = new ArrayList<Group>();
		if (groupService.findByName(scheduleParameter) != null) {
			groups.add(groupService.findByName(scheduleParameter));
			return groups;
		} else if (userService.findByLogin(scheduleParameter) != null) {
			return groupService.findByUser((userService
					.findByLogin(scheduleParameter)));
		}
		return groupService.findAll();
	}

	/*
	 * Method receive from controller name of subject. Return list of aLL Events
	 * for this subject (group, manager or location)
	 */
	@Override
	public List<Event> findEventsForDownload(String scheduleParameter) {
		if (groupService.findByName(scheduleParameter) != null) {
			return eventDao.findEventsByGroup(groupService.findByName(
					scheduleParameter).getId());
		} else if (userService.findByLogin(scheduleParameter) != null) {
			return eventDao.findEventsByUser(scheduleParameter);
		}
		return eventDao.findEventsByLocation(locationService.findByName(
				scheduleParameter).getId());
	}

	/*
	 * Method receive from controller name of subject, begin date and end date
	 * of selection. Return list of Events for this subject (group, manager or
	 * location)
	 */
	@Override
	public List<Event> findEventsForDownload(String scheduleParameter,
			DateTime start, DateTime end) {
		if (groupService.findByName(scheduleParameter) != null) {
			return eventDao.findEventsByGroupBetweenDates(groupService
					.findByName(scheduleParameter).getId(), start, end);
		} else if (userService.findByLogin(scheduleParameter) != null) {
			return eventDao.findEventsByUserBetweenDates(scheduleParameter,
					start, end);
		}
		return eventDao.findEventsByLocationBetweenDates(locationService
				.findByName(scheduleParameter).getId(), start, end);
	}

	/*
	 * Method return list of Events depending by subject (group, manager or
	 * location) If admin want to see schedule by particular location. Method
	 * return Events for this location
	 */
	@Override
	public List<Event> findCurrentWeekEvents(DateTime dateTime,
			String scheduleParameter) {
		DateTime startweek = dateTime.dayOfWeek().withMinimumValue();
		DateTime endweek = dateTime.dayOfWeek().withMaximumValue();

		if (groupService.findByName(scheduleParameter) != null) {
			return eventDao.findEventsByGroupBetweenDates(groupService
					.findByName(scheduleParameter).getId(), startweek, endweek);
		} else if (userService.findByLogin(scheduleParameter) != null) {
			return eventDao.findEventsByUserBetweenDates(scheduleParameter,
					startweek, endweek);
		}
		return eventDao.findEventsByLocationBetweenDates(locationService
				.findByName(scheduleParameter).getId(), startweek, endweek);
	}

	/*
	 * Method return subject name. If JSP return empty field of name, method use
	 * user login as subject name.
	 */
	@Override
	public String isLoginEmpty(String login, Principal principal) {
		return login.isEmpty() ? principal.getName() : login;
	}

	/*
	 * Create date with number of week in year. If JSP return empty field of
	 * week, method create new date.
	 */
	@Override
	public DateTime currentDateTime(String newWeek) {
		int week = StringUtils.isEmpty(newWeek) ? new DateTime()
				.getWeekOfWeekyear() : Integer.parseInt(newWeek);
		DateTime newDate = new DateTime().withWeekOfWeekyear(week);
		return newDate;
	}

	/*
	 * Create new Event, set parameters from FormBean and return it for flushing
	 * to datebase
	 */
	@Override
	public Event getEvent(EventFormBean eventFormBean) {
		Event event = new Event();
		getEventFromFormBean(eventFormBean, event);
		return event;
	}

	/*
	 * Search Event in Datebase, modify object with new parameters fromFormBean
	 * and return it for update
	 */
	@Override
	public Event getEditableEvent(EventFormBean eventFormBean, DateTime dateTime) {
		Event event = findById(eventFormBean.getEvent().getId());
		getEventFromFormBean(eventFormBean, event);
		return event;
	}

	/*
	 * Set Event parameters from FormBean.
	 */
	private void getEventFromFormBean(EventFormBean eventFormBean, Event event) {
		String date = eventFormBean.getEventDate();
		logger.info("EventDate in EventFormBean:"
				+ eventFormBean.getEventDate());
		String starttime = eventFormBean.getStarttime();
		logger.info("Startime in EventFormBean:" + eventFormBean.getStarttime());
		String endtime = eventFormBean.getEndtime();
		logger.info("Endtime in EventFormBean:" + eventFormBean.getEndtime());
		DateTime startTime = convertDateTime(date, starttime);
		DateTime endTime = convertDateTime(date, endtime);
		event.setStartDatetime(startTime);
		event.setEndDatetime(endTime);
		Group group = eventFormBean.getGroup();
		event.setGroup(groupService.findByName(group.getGroupName()));
		Location location = eventFormBean.getLocation();
		event.setLocation(locationService.findByName(location.getName()));
	}

	/*
	 * Select data from datebase and write it to CSV file
	 */
	@Override
	public void readEventsFromDb(List<Event> events) throws IOException {
		scheduleParser.readData(events);
	}

	/*
	 * Parse downloaded CSV file to and write Events to datebase
	 */
	@Override
	public void writeEventsToDb() throws IOException {
		scheduleParser.parseCSVFileLineByLine();
	}
}
