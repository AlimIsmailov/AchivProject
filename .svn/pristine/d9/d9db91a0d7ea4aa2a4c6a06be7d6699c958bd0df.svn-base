package com.ita.softserveinc.achiever.service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.Event;
import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.exception.ElementExistsException;
import com.ita.softserveinc.achiever.exception.InvalidDateException;
import com.ita.softserveinc.achiever.tool.EventFormBean;

@Component
public interface IEventService extends IGenericService<Event>{

	Event getEditableEvent(EventFormBean eventFormBean, DateTime date);

	Event getEvent(EventFormBean eventFormBean) throws ElementExistsException,
	InvalidDateException;
	
	List<Event> findCurrentWeekEvents(DateTime datetime, String varFromJsp);

	DateTime convertDateTime(String eventDate, String time);

	DateTime currentDateTime(String week);

	void writeEventsToDb() throws IOException;

	void readEventsFromDb(List <Event> events) throws IOException;

	List<Event> findByUser(String login, DateTime start, DateTime end);

	List<Event> findEventsForDownload(String varFromJsp);

	List<Event> findEventsForDownload(String varFromJsp, DateTime start,
			DateTime end);

	List<Group> findGroupForJsp(String fromJsp);

	String isLoginEmpty(String login, Principal principal);
}
