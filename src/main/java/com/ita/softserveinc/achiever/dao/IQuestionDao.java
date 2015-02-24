package com.ita.softserveinc.achiever.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.Question;
import com.ita.softserveinc.achiever.entity.Subtopic;

@Component
public interface IQuestionDao extends IGenericDao<Question>{
	
	Question findByName(String name);

	Question findByQuestion(Question question);

	List<Question> findBySubtopic(Subtopic subtopic);

}
