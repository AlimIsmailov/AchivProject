package com.ita.softserveinc.achiever.entity;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.google.common.testing.EqualsTester;


/**
 * @author Taras Hrytsko
 * 
 */
@RunWith (value=JUnit4.class)
public class QuestionTest extends TestCase {
	private static Subtopic subtopic = new Subtopic("Lists", new Topic(
			"Collections"));
	private static Subtopic subtopic2 = new Subtopic("Synchronized", new Topic(
			"Threads"));

	@Test
	public void constructorWithKey() {
		assertEquals("Lists", subtopic.getName());
	}

	@Test
	public void equalsContract() {

		Question question = new Question("question", "answer", subtopic);
		Question question2 = new Question("question", "answer", subtopic);
		Question question3 = new Question("question2", "answer2", subtopic2);
		Question question4 = new Question("question2", "answer2", subtopic2);

		new EqualsTester().addEqualityGroup(question, question2)
				.addEqualityGroup(question3, question4).testEquals();
	}

	@Test
	public void testToString() {
		Question question = new Question("question", "answer", subtopic);
		String expected = "Question{id=null, question=question, correct answer=answer}";
		String actual = question.toString();
		assertEquals(expected, actual);

	}

}
