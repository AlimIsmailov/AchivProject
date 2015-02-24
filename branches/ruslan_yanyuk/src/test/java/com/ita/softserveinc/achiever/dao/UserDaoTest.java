package com.ita.softserveinc.achiever.dao;

import java.sql.SQLException;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;

import com.ita.softserveinc.achiever.config.DBUnitConfig;
import com.ita.softserveinc.achiever.entity.User;


@RunWith(JUnit4.class)
public class UserDaoTest extends DBUnitConfig {

	@Autowired
	private IUserDao userDao;
	@Autowired
	private IRoleDao roleDao;
	
//	private static Role role = new Role("ADMIN");
	

	public UserDaoTest() {
		super("UserDaoTest");
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		beforeData = getXlsDataSetFromPath("src/test/java/resources/com/softserve/entity/user/users_table.xls");
		tester.setDataSet(getDataSet());
		tester.onSetup();
	}

	@Test
	public void createNewUser() throws Exception {
		
		
		User user = new User("Larry", "123456789", "Larry@own.com");
		user.setFirstName("Larry");
		user.setLastName("Larry");
		userDao.create(user);
		
		IDataSet databaseDataSet = getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("USERS");

		
		IDataSet expectedDataSet = getXlsDataSetFromPath("src/test/java/resources/com/softserve/entity/user/users_table_create.xls");
		ITable expectedTable = expectedDataSet.getTable("USERS");

		String[] ignore = { "ID", "LAST_UPDATE", "CREATED", "version" };
		DatabaseOperation.DELETE_ALL.execute(getConnection(), expectedDataSet);
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, ignore);

	}

//	@Test(expected = Exception.class)
//	public void updateUser_NotNullFieldsToNull() {
//		String login = "Harry";
//		String expected = null;
//
//		User foundetUser = userDao.getByLogin(login);
//		foundetUser.setEmail(expected);
//
//		assertNull(userDao.update(foundetUser));
//
//	}
//
//	@Test
//	public void updateUser_NotMapedFields() {
//		String login = "Harry";
//		String expectedLastName = "King";
//		String expectedFirstName = "Joe";
//
//		User foundetUser = userDao.getByLogin(login);
//
//		foundetUser.setLastName(expectedLastName);
//		foundetUser.setFirstName(expectedFirstName);
//
//		User actual = userDao.update(foundetUser);
//
//		assertEquals(expectedFirstName, actual.getFirstName());
//		assertEquals(expectedLastName, actual.getLastName());
//	}
//
//	@Test
//	public void userDeleting() {
//		String login = "James";
//		User users = userDao.getByLogin(login);
//		userDao.delete(users);
//		User actual = userDao.getByLogin(login);
//
//		assertNull(actual);
//	}
//
//	@Test(expected = Exception.class)
//	public void deleteNullUser() {
//		User users = null;
//		userDao.delete(users);
//	}
//
//	@Test
//	public void getById() {
//		Long expected = 2L;
//		Long actual = userDao.findById(User.class, expected).getId();
//
//		assertEquals(expected, actual);
//	}
//
//	@Test(expected = Exception.class)
//	public void getById_NegativeValue() {
//		Long expected = -1L;
//		Long actual = userDao.findById(User.class, expected).getId();
//
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	public void getByLogin() {
//		String expected = "Thomas";
//		User foundUser = userDao.getByLogin(expected);
//		String actual = foundUser.getFirstName();
//
//		assertEquals(expected, actual);
//	}
//
//	@Test(expected = Exception.class)
//	public void getByLogin_NullUserLogin() {
//		String expected = null;
//		User foundUser = userDao.getByLogin(expected);
//		String actual = foundUser.getFirstName();
//
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	public void getByLogin_NonExistentUser() {
//		String expected = "Guava";
//		User actual = userDao.getByLogin(expected);
//
//		assertNull(actual);
//	}

	@Test
	public void testDelete() throws SQLException, Exception {
		User user = userDao.findByLogin("Daniel");
		userDao.delete(user);

		IDataSet databaseDataSet = getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("USERS");

		IDataSet expectedDataSet = getXlsDataSetFromPath("src/test/java/resources/com/softserve/entity/user/users_table_delete.xls");
		ITable expectedTable = expectedDataSet.getTable("USERS");

		String[] ignore = { "ID", "LAST_UPDATE", "CREATED", "version" };
		DatabaseOperation.DELETE_ALL.execute(getConnection(), expectedDataSet);
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, ignore);
		
	}

	/*
	 * /
	 * 
	 * @AfterClass public static void tearDownAfterClass() throws Exception {
	 * 
	 * tested.closeAll(); entityManager.close(); ENTITY_MANAGER_FACTORY.close();
	 * } /
	 */

}
