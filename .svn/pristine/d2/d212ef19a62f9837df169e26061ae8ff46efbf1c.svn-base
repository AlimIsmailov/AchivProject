package com.ita.softserveinc.achiever.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.QuizResult;
import com.ita.softserveinc.achiever.entity.Subtopic;
import com.ita.softserveinc.achiever.exception.NotEnoughElementsExeption;
import com.ita.softserveinc.achiever.tool.GenerateTestFormBean;

/**
 * @author Ruslan Didyk
 */
@Component
public interface IQuizResultService extends IGenericService<QuizResult> {

	List<QuizResult> findAllByUser(String login);

	void generateTest(GenerateTestFormBean testFormBean)
			throws NotEnoughElementsExeption;

	void generateTest(String name, Group group, Subtopic subtopic,
			int countOfQuestions, boolean review) throws NotEnoughElementsExeption;

	List<Integer> getCount();

	void saveAndCheckTest(QuizResult quizResult,
			Map<String, List<String>> testsData);

	void shuffleAnswers(QuizResult quiz);

}
