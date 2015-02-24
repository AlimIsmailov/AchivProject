package com.ita.softserveinc.achiever.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.common.testing.EqualsTester;


public class TopicTest {
	
	private Direction java = new Direction("Java");
	private Topic operators = new Topic("Operators");
	private Topic arrays = new Topic("Arrays");
	private Topic basics = new Topic("Basics");
	private Topic operatorsTest = new Topic("Operators");
	private Topic arraysTest = new Topic("Arrays");
	private Topic basicsTest = new Topic("Basics");
	
	@Test
	public void equalsContract() {
		
		new EqualsTester().addEqualityGroup(operators, operatorsTest)
				.addEqualityGroup(arrays, arraysTest)
				.addEqualityGroup(basics, basicsTest).testEquals();
	}
	
    @Test
    public void toStringTest() {
            operators.addDirection(java);
    		String testing = "Topic{name=Operators, direction=Direction{Java}]";
            assertEquals(testing, operators.toString());
    }
}
