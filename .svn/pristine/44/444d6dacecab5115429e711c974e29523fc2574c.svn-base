package com.ita.softserveinc.achiever.dao;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import com.ita.softserveinc.achiever.entity.Event;

@Repository
public class EventDaoImpl extends GenericDaoImpl<Event> implements IEventDao {

	private final int WEEK = 1;

	public EventDaoImpl() {

	}

	public List<Event> findEventByWeek(String login, DateTime startweek,
			DateTime endweek) {

		List<Event> events = new ArrayList<Event>();
		events = entityManager
				.createNamedQuery("Event.findByUserCurrentWeek", Event.class)
				.setParameter("login", login)
				.setParameter("startDate", startweek)
				.setParameter("endDate", endweek).getResultList();
		return events;
	}

	public List<Event> findEventCurrentWeek(String login, int week) {

		DateTime datetime = new DateTime();
		DateTime startweek = datetime.withWeekOfWeekyear(week).dayOfWeek().withMinimumValue();
		DateTime endweek = datetime.withWeekOfWeekyear(week).dayOfWeek().withMaximumValue();

		return findEventByWeek(login, startweek, endweek);
	}

}