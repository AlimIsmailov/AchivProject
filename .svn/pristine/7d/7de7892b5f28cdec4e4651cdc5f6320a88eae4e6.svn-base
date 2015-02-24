package com.ita.softserveinc.achiever.service;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.Event;

@Component
public interface IEventService extends IGenericService<Event>{

	List<Event> findByUserCurrentWeek(String login, int week);

	DateTime currentDateTime(String submitValue);

	DateTime convertDateTime(DateTime dateTime, String day, String time);
	
}
