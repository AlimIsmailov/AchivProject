package com.ita.softserveinc.achiever.entity;

import static org.junit.Assert.*;

import org.junit.After;

import org.junit.Before;
import org.junit.Test;

import com.google.common.testing.EqualsTester;


import static org.mockito.Mockito.*;

public class QuizResultTest {

	private QuizResult quizResult1;
	private QuizResult quizResult2;
	private QuizResult quizResult3;
	private QuizResult quizResult4;
	private QuizResult quizResult5;

	@Before
	public void setUp() throws Exception {

		User user = mock(User.class);

		String nameResult = "Result";
		double grade = 250;

		quizResult1 = new QuizResult();
		quizResult1.setName(nameResult);
		quizResult1.setTotalGrade(grade);
		quizResult1.setUser(user);

		quizResult2 = new QuizResult();
		quizResult2.setName(nameResult);
		quizResult2.setTotalGrade(grade);
		quizResult2.setUser(user);

		quizResult3 = new QuizResult();
		quizResult3.setName("ResultOther");
		quizResult3.setTotalGrade(50.0);
		quizResult3.setUser(user);

		quizResult4 = new QuizResult();
		quizResult4.setName("ResultOther2");
		quizResult4.setTotalGrade(50.0);
		quizResult4.setUser(user);

		quizResult5 = new QuizResult();
		quizResult5.setUser(user);
	}

	@After
	public void tearDown() throws Exception {

		quizResult1 = quizResult2 = quizResult3 = quizResult4 = null;
	}

	@Test
	public void testToSTring() {

		String expected = "QuizResult{name=Result, user=0, totalGrade=250.0}";
		String actual = quizResult1.toString();
		assertEquals(expected, actual);

	}

	@Test
	public void testEqualsObject() {

		new EqualsTester().addEqualityGroup(quizResult1, quizResult2)
				.addEqualityGroup(quizResult3).addEqualityGroup(quizResult4)
				.addEqualityGroup(quizResult5).testEquals();
	}

}
