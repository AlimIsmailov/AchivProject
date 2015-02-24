package com.ita.softserveinc.achiever.dao;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.Event;
import com.ita.softserveinc.achiever.entity.Location;

@Component
public interface IEventDao extends IGenericDao<Event>{
	
	List<Event> findEventsByUser(String login);
	
	List<Event> findEventsByGroup(long groupId);
	
	List<Event> findEventsByLocation(long locationId);
	
	List<Event> findEventsByUserBetweenDates(String login, DateTime start,
			DateTime end);

	List<Event> findEventsByGroupBetweenDates(long groupId, DateTime start,
			DateTime end);
	
	List<Event> findEventsByLocationBetweenDates(long locationId, DateTime start,
			DateTime end);
	
	Event findExistEvent(Location location, DateTime start, DateTime end);



}
