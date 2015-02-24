package com.ita.softserveinc.achiever.service;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.softserveinc.achiever.dao.IEventDao;
import com.ita.softserveinc.achiever.entity.Event;
import com.ita.softserveinc.achiever.exception.ElementExistsException;

@Service
public class EventServiceImpl implements IEventService{

	@Autowired
	private IEventDao EventDao;
	
	@Transactional
	public void create(Event entity) {
		try {
			EventDao.create(entity);
		} catch (ElementExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public DateTime convertDateTime (String day, String time) {
		
		final int MINUTES = 0;
				
		int currentDay = Integer.parseInt(day);
		int currentStartTime = Integer.parseInt(time);
		DateTime dateTime = new DateTime();
		DateTime startTime = dateTime.withDayOfWeek(currentDay)
				.withHourOfDay(currentStartTime).withMinuteOfHour(MINUTES);
		return startTime;
		
	}
	
	@Override
	public List<Event> findByUserCurrentWeek(String login, int week) {
		return EventDao.findEventCurrentWeek(login, week);
	}
	
	@Override
	public DateTime currentDateTime (String submitValue) {
		
		
		DateTime datetime = new DateTime ();
		DateTime currentdatetime = datetime;
		int week = datetime.getWeekOfWeekyear();
				
		if (submitValue.equalsIgnoreCase("Next Week")) {
			week++;
			currentdatetime = datetime.withWeekOfWeekyear(week);
		} else if (submitValue.equalsIgnoreCase("Renew")) {
			week--;
			currentdatetime = datetime.withWeekOfWeekyear(week);
		}
		return currentdatetime;
	}

}
