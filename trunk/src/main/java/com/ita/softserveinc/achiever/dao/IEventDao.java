package com.ita.softserveinc.achiever.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.Event;

@Component
public interface IEventDao extends IGenericDao<Event>{
	
	List<Event> findEventCurrentWeek(String login, int week);

}
