package com.ita.softserveinc.achiever.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.ita.softserveinc.achiever.entity.Event;
import com.ita.softserveinc.achiever.entity.Location;

@Repository
public class EventDaoImpl extends GenericDaoImpl<Event> implements IEventDao {

	@Override
	public List<Event> findEventsByUser(String login) {
		
		List<Event> events = new ArrayList<Event>();
		events = entityManager
				.createNamedQuery("Event.findByUser", Event.class)
				.setParameter("login", login).getResultList();
		return events;
	}
	
	@Override
	public List<Event> findEventsByGroup(long groupId) {
		
		List<Event> events = new ArrayList<Event>();
		events = entityManager
				.createNamedQuery("Event.findByGroup", Event.class)
				.setParameter("group", groupId).getResultList();
		return events;
	}
	
	@Override
	public List<Event> findEventsByLocation(long locationId) {
		
		List<Event> events = new ArrayList<Event>();
		events = entityManager
				.createNamedQuery("Event.findByLocation", Event.class)
				.setParameter("location", locationId).getResultList();
		return events;
	}
	
	@Override
	public List<Event> findEventsByUserBetweenDates(String login,
			DateTime startdate, DateTime enddate) {

		List<Event> events = new ArrayList<Event>();
		events = entityManager
				.createNamedQuery("Event.findByUserBetweenDates", Event.class)
				.setParameter("login", login)
				.setParameter("startDate", startdate)
				.setParameter("endDate", enddate).getResultList();
		return events;
	}

	@Override
	public List<Event> findEventsByGroupBetweenDates(long groupId, DateTime startdate, DateTime enddate) {

		List<Event> events = new ArrayList<Event>();
		events = entityManager
				.createNamedQuery("Event.findByGroupBetweenDates",
						Event.class).setParameter("group", groupId)
				.setParameter("startDate", startdate)
				.setParameter("endDate", enddate).getResultList();
		return events;
	}


	@Override
	public List<Event> findEventsByLocationBetweenDates(long locationId,
			DateTime startdate, DateTime enddate) {

		List<Event> events = new ArrayList<Event>();
		events = entityManager
				.createNamedQuery("Event.findByLocationBetweenDates",
						Event.class).setParameter("location", locationId)
				.setParameter("startDate", startdate)
				.setParameter("endDate", enddate).getResultList();
		return events;
	}


	@Override
	public Event findExistEvent(Location location, DateTime start, DateTime end) {

		Query query = entityManager
				.createNamedQuery("Event.existValidation", Event.class)
				.setParameter("startDatetime", start)
				.setParameter("endDatetime", end)
				.setParameter("location", location);
		List<Event> result = query.getResultList();
		return CollectionUtils.isEmpty(result ) ? null : result.get(0);
	}
}