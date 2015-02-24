package com.ita.softserveinc.achiever.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.common.testing.EqualsTester;


public class DirectionTest {
	
	@Test
	public void constructorWithKey() {
		Direction ui = new Direction("UI");
		assertEquals("UI", ui.getName());
	}
	
	@Test
	public void equalsContract() {
		Direction java1 = new Direction("Java");
		Direction java2 = new Direction("Java");
		Direction cSharp1 = new Direction("C#");
		Direction cSharp2 = new Direction("C#");
		Direction cPlusPlus1 = new Direction("C++");
		Direction cPlusPlus2 = new Direction("C++");
		new EqualsTester().addEqualityGroup(java1, java2)
				.addEqualityGroup(cSharp1, cSharp2)
				.addEqualityGroup(cPlusPlus1, cPlusPlus2).testEquals();
	}

	@Test
	public void testToString() {
		Direction ruby = new Direction("Ruby");
		String expected = "Direction{Name=Ruby}";
		String actual = ruby.toString();
		assertEquals(expected, actual);
	}

}
