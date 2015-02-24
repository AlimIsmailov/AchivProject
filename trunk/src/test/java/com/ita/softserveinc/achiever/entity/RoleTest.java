package com.ita.softserveinc.achiever.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.common.testing.EqualsTester;


/**
 * 
 * @author Ostap Palianytsia
 * 
 */
public class RoleTest {

	/**
	 * Test method for
	 * {@link main.java.com.softserve.itachiever.entity.Role#Role(String)}
	 */
	@Test
	public void constructorWithKey() {
		Role role = new Role("Client");
		assertEquals("Client", role.getType());
	}

	/**
	 * Test method for
	 * {@link main.java.com.softserve.itachiever.entity.Role#toString()}
	 */
	@Test
	public void testToString() {
		Role student = new Role("Student");
		String expected = "Role{type=Student}";
		String actual = student.toString();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link main.java.com.softserve.itachiever.entity.Role#equals(Object)}
	 */
	@Test
	public void equalsContract() {
		Role student = new Role("Student");
		Role testStudent = new Role("Student");
		Role moderator = new Role("Moderator");
		Role testModerator = new Role("Moderator");
		Role admin = new Role("Admin");
		Role testAdmin = new Role("Admin");
		new EqualsTester().addEqualityGroup(student, testStudent)
				.addEqualityGroup(moderator, testModerator)
				.addEqualityGroup(admin, testAdmin).testEquals();
	}

}
