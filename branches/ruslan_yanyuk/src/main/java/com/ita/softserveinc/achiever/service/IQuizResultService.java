package com.ita.softserveinc.achiever.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.QuizResult;
import com.ita.softserveinc.achiever.entity.Subtopic;
import com.ita.softserveinc.achiever.exception.NotEnoughtElementsExeption;
/**
 * @author Ruslan Didyk
 */
@Component
public interface IQuizResultService extends IGenericService<QuizResult>{

	List<QuizResult> findAllByUser(String login);

	QuizResult findOne(QuizResult quizResult);

	void generateTest(String name, Group group, Subtopic subtopic, int countOfQuestions) throws NotEnoughtElementsExeption;
}
