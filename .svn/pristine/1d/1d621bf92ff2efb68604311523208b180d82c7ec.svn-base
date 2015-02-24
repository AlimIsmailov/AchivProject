package com.ita.softserveinc.achiever.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.google.common.testing.EqualsTester;


public class UserTest {
//	private Role role = new Role("ADMIN");
	private User userLarry = new User("Larry", "123456789", "Larry@com");
	private User userLarryTest = new User("Larry", "123456789", "Larry@com");

	private User userSally = new User("Sally", "123456789", "Sally@com");
	private User userSallyTest = new User("Sally", "123456789", "Sally@com");

	private User userTor = new User("Tor", "123456789", "Tor@com");
	private User userTorTest = new User("Tor", "123456789", "Tor@com");

	@Before
	public void setUp() {

	}

	@Test
	public void equalsContract() {

		new EqualsTester().addEqualityGroup(userLarry, userLarryTest)
				.addEqualityGroup(userSally, userSallyTest)
				.addEqualityGroup(userTor, userTorTest).testEquals();
	}

	@Test
	public void toString_NotNull() {
		User user = new User("Larry", "123456789", "123@own.com");
		user.setFirstName("Larry");
		user.setLastName("Page");

		String expected = "User{login=" + user.getLogin() + ", first name="
				+ user.getFirstName() + ", last name=" + user.getLastName()
				+ ", email=" + user.getEmail() + ", role="
				+ user.getRoles() + "}";
		String actual = user.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void toString_IncludeNullFields() {
		User user = new User("Larry", "123456789", "123@own.com");

		String expected = "User{login=" + user.getLogin() + ", first name="
				+ user.getFirstName() + ", last name=" + user.getLastName()
				+ ", email=" + user.getEmail() + ", role="
				+ user.getRoles() + "}";
		String actual = user.toString();
		assertEquals(expected, actual);
	}
}
