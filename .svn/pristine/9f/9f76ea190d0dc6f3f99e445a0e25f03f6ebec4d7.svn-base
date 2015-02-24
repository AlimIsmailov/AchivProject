package com.ita.softserveinc.achiever.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.Subtopic;
import com.ita.softserveinc.achiever.entity.Topic;

@Component
public interface ISubtopicService extends IGenericService<Subtopic>{
	
	Subtopic findByName(String string);

	List<Subtopic> findAll();
	
	Subtopic findById(Long id);
	
	Subtopic update(Subtopic entity);
	
	void create(Subtopic entity);

	List<Subtopic> findByTopic(Topic topic);
}
