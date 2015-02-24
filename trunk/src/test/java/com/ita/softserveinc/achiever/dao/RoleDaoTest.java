package com.ita.softserveinc.achiever.dao;

import java.sql.SQLException;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;

import com.ita.softserveinc.achiever.config.DBUnitConfig;
import com.ita.softserveinc.achiever.entity.Role;

@RunWith(JUnit4.class)
public class RoleDaoTest extends DBUnitConfig implements IDaoTest {

	@Autowired
	private IRoleDao roleDao;
	private Role student = new Role("Student");
	private Role moderator = new Role("Moderator");

	public RoleDaoTest() {
		super("RoleDaoTest");
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		beforeData = getXmlDataSetFromPath("resources/com/softserve/entity/role/role-data-set.xml");
		tester.setDataSet(beforeData);
		tester.onSetup();
	}

	@Test
	public void createTest() throws SQLException, Exception {
		roleDao.create(moderator);

		IDataSet databaseDataSet = getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("ROLES");

		IDataSet expectedDataSet = getXmlDataSetFromPath("resources/com/softserve/entity/role/role-data-set-create.xml");
		ITable expectedTable = expectedDataSet.getTable("ROLES");

		String[] ignore = { "ID", "LAST_UPDATE", "CREATED", "version" };
		System.err.println("EXCPECTED: " + expectedTable.toString());
		System.err.println("ACTUAL: " + actualTable.toString());
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, ignore);
	}

	@Test
	public void updateTest() throws SQLException, Exception {
		student = roleDao.findByType("Student");
		student.setType("Guest");
		roleDao.update(student);
		
		IDataSet databaseDataSet = getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("ROLES");
		
		IDataSet expectedDataSet = getXmlDataSetFromPath("resources/com/softserve/entity/role/role-data-set-update.xml");
		ITable expectedTable = expectedDataSet.getTable("ROLES");
		
		String[] ignore = { "ID", "LAST_UPDATE", "CREATED", "version"};
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, ignore);
	}

	@Test
	public void deleteTest() throws SQLException, Exception {
		roleDao.delete(moderator);
		
		IDataSet databaseDataSet = getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("ROLES");

		IDataSet expectedDataSet = getXmlDataSetFromPath("resources/com/softserve/entity/role/role-data-set-delete.xml");
		ITable expectedTable = expectedDataSet.getTable("ROLES");

		String[] ignore = { "ID", "LAST_UPDATE", "CREATED", "version"};
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, ignore);
	}

	@Test
	public void findAllTest() throws SQLException, Exception {
		List<Role> roles = roleDao.findAll(Role.class);
		IDataSet databaseDataSet = getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("ROLES");

		IDataSet expectedDataSet = getDataSet();
		ITable expectedTable = expectedDataSet.getTable("ROLES");

		System.out.println("ROLES: " + roles.get(0));
		String[] ignore = { "ID", "LAST_UPDATE", "CREATED", "version" };
		assertEquals(roles.size(), actualTable.getRowCount());
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, ignore);
	}
}
