package com.ita.softserveinc.achiever.service;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.Event;

@Component
public interface IEventService extends IGenericService<Event>{

	DateTime convertDateTime(String day, String time);
	
	List<Event> findByUserCurrentWeek(String login, int week);

	DateTime currentDateTime(String submitValue);
	
}
