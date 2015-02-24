package com.ita.softserveinc.achiever.service;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.softserveinc.achiever.dao.IEventDao;
import com.ita.softserveinc.achiever.entity.Event;

@Service
public class EventServiceImpl implements IEventService {

	@Autowired
	private IEventDao EventDao;

	@Transactional
	public void create(Event entity) {
		EventDao.create(entity);
	}

	@Transactional
	public Event update(Event entity) {
		return EventDao.update(entity);
	}

	@Transactional
	public void delete(Event entity) {
		EventDao.delete(entity);
	}

	public Event findById(Long id) {
		return EventDao.findById(Event.class, id);
	}

	public List<Event> findAll() {
		return EventDao.findAll(Event.class);
	}

	@Override
	public DateTime convertDateTime(DateTime dateTime, String day, String time) {

		final int MINUTES = 0;

		int currentDay = Integer.parseInt(day);
		int currentStartTime = Integer.parseInt(time);
		DateTime startTime = dateTime.withDayOfWeek(currentDay)
				.withHourOfDay(currentStartTime).withMinuteOfHour(MINUTES);
		return startTime;

	}

	@Override
	public List<Event> findByUserCurrentWeek(String login, int week) {
		return EventDao.findEventCurrentWeek(login, week);
	}

	@Override
	public DateTime currentDateTime(String submitValue) {

		DateTime datetime = new DateTime();
		DateTime currentdatetime;
		int week = datetime.getWeekOfWeekyear();
		if (submitValue.equalsIgnoreCase("Next Week")) {
			week++;
		} else if (submitValue.equalsIgnoreCase("Previous week")) {
			week--;
		}
		currentdatetime = datetime.withWeekOfWeekyear(week);
		return currentdatetime;
	}

}
