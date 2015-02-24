package com.ita.softserveinc.achiever.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.Answer;
import com.ita.softserveinc.achiever.entity.Question;
/**
 * @author Ruslan Didyk
 */
@Component
public interface IAnswerService extends IGenericService<Answer>{

	Answer findByAnswer(Answer answer);

	List<Answer> findByQuestion(Question question);

}
