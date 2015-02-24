package com.ita.softserveinc.achiever.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.Question;
import com.ita.softserveinc.achiever.entity.Subtopic;

/**
 * @author Taras Hrytsko
 *
 */
@Component
public interface IQuestionService extends IGenericService<Question>{

	Question findByName(String name);

	Question findByQuestion(Question question);

	List<Question> findBySubtopic(Subtopic subtopic);

}
