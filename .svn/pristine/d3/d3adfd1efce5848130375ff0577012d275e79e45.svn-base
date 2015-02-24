package com.ita.softserveinc.achiever.entity;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.common.testing.EqualsTester;


public class GroupTest {
	private Direction directionJava;
	private Direction directionRuby;
	private Group LV113;
	private Group LV114;
	private Group testLV113;
	private Group testLV114;

	@Before
	public void setUp() {
		directionJava = new Direction("Java");
		directionRuby = new Direction("Ruby");
		LV113 = new Group("LV-113");
		LV113.setDirection(directionJava);
		testLV113 = new Group("LV-113");
		testLV113.setDirection(directionJava);
		LV114 = new Group("LV-114");
		LV114.setDirection(directionRuby);
		testLV114 = new Group("LV-114");
		testLV114.setDirection(directionRuby);
	}

	@Test
	public void constructorWithKey() {
		assertEquals("LV-113", LV113.getGroupName());
	}

	@Test
	public void equalsContract() {
		new EqualsTester().addEqualityGroup(LV113, testLV113)
				.addEqualityGroup(LV114, testLV114).testEquals();
	}

	@Test
	public void testToString() {
		String expected = "Group{name=LV-113, direction=Java}";
		String actual = LV113.toString();
		assertEquals(expected, actual);
	}
}
