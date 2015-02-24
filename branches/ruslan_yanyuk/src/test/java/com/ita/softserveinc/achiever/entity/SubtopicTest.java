package com.ita.softserveinc.achiever.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;



public class SubtopicTest {
	
	Subtopic subtopic = new Subtopic("List");
	Subtopic subtopic2 = new Subtopic("List");

	@Test
	public void testHashCode() {
		subtopic.setTopic(new Topic("Collections"));
		Topic topic = subtopic.getTopic();
		int expected = 31*subtopic.getName().hashCode()+topic.hashCode();
		int actual = subtopic.hashCode();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testHashCodeDifferentTopics() {
		subtopic.setTopic(new Topic("Java Collections"));
		subtopic2.setTopic(new Topic("C# Collections"));
		int hashCodeJava = subtopic.hashCode();
		int hashCodeC = subtopic2.hashCode();
		assertNotEquals(hashCodeJava, hashCodeC);
		
	}

	@Test
	public void testEqualsObject() {
		subtopic.setTopic(new Topic("Collections"));
		subtopic2.setTopic(new Topic("Collections"));
		assertTrue(subtopic.equals(subtopic2));
	}

	@Test
	public void testToString() {
		String expected = "Id: "+subtopic.getId()+" Name: List";
		String actual = subtopic.toString();
		assertEquals(expected, actual);
	}

}
