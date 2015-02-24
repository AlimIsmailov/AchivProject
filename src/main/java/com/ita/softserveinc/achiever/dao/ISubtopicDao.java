package com.ita.softserveinc.achiever.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.Subtopic;
import com.ita.softserveinc.achiever.entity.Topic;
import com.ita.softserveinc.achiever.exception.SubtopicException;

@Component
public interface ISubtopicDao extends IGenericDao<Subtopic>{
	
	Subtopic findByName(String name);

	List<Subtopic> findByTopic(Topic topic) throws SubtopicException;

}
