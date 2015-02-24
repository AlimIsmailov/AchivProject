package com.ita.softserveinc.achiever.entity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.testing.EqualsTester;


public class UserAnswerTest {

	private UserAnswer userAnswer1;
	private UserAnswer userAnswer2;
	private UserAnswer userAnswer3;
	private UserAnswer userAnswer4;
	private UserAnswer userAnswer5;

	private QuizResult mockedQuizResult = mock(QuizResult.class);
	private Question mockedQuestion = mock(Question.class);

	@Before
	public void setUp() throws Exception {

		String answer = "userAnswer";
		double grade = 100;

		Question question = new Question();
		question.setName("question");

		QuizResult quizResult = new QuizResult();
		quizResult.setName("name");

		userAnswer1 = new UserAnswer();
		userAnswer1.setAnswerGrade(grade);
		userAnswer1.addAnswer(new Answer(answer,true));
		userAnswer1.setQuestion(question);
		userAnswer1.setQuizResult(quizResult);

		userAnswer2 = new UserAnswer();
		userAnswer2.setAnswerGrade(grade);
		userAnswer2.addAnswer(new Answer(answer,true));
		userAnswer2.setQuestion(question);
		userAnswer2.setQuizResult(quizResult);

		userAnswer3 = new UserAnswer();
		userAnswer3.setAnswerGrade(200.0);
		userAnswer3.addAnswer(new Answer("UserAnswer3",true));
		userAnswer3.setQuizResult(mockedQuizResult);
		userAnswer3.setQuestion(mockedQuestion);

		userAnswer4 = new UserAnswer();
		userAnswer4.setAnswerGrade(200.0);
		userAnswer3.addAnswer(new Answer("UserAnswer4",true));
		userAnswer4.setQuestion(mockedQuestion);
		userAnswer4.setQuizResult(mockedQuizResult);

		userAnswer5 = new UserAnswer();
		userAnswer5.setQuestion(question);
		userAnswer5.setQuizResult(quizResult);

	}

	@After
	public void tearDown() throws Exception {
		userAnswer1 = userAnswer2 = userAnswer3 = userAnswer4 = userAnswer5 = null;
	}

	@Test
	public void testToString() {

		String expected = "UserAnswer{question=question, userAnswer=userAnswer, answerGrade=100.0, quizResult=name}";
		String actual = userAnswer1.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void testEqualsObject() {
		new EqualsTester().addEqualityGroup(userAnswer1, userAnswer2)
				.addEqualityGroup(userAnswer3).addEqualityGroup(userAnswer4)
				.addEqualityGroup(userAnswer5).testEquals();
	}

}
