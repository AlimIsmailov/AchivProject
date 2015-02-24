package com.ita.softserveinc.achiever.dao;

import java.sql.SQLException;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;

import com.ita.softserveinc.achiever.config.DBUnitConfig;
import com.ita.softserveinc.achiever.entity.Group;





@RunWith(JUnit4.class)
public class GroupDaoTest extends DBUnitConfig {
	
	@Autowired
	private IGroupDao groupDao;
	private Group group = new Group("LV-115");
	
	public GroupDaoTest() {
		super("GroupDao");
	}

	public void setUp() throws Exception {
		super.setUp();
		beforeData = getXmlDataSetFromPath("resources/com/softserve/entity/group/group-data.xml");
		tester.setDataSet(beforeData);
		tester.onSetup();
	}

	@Test
	public void testCreate() throws SQLException, Exception {
		groupDao.create(group);
		IDataSet databaseDataSet = getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("GROUPS");

		IDataSet expectedDataSet = getXmlDataSetFromPath("resources/com/softserve/entity/group/group-data-create.xml");
		ITable expectedTable = expectedDataSet.getTable("GROUPS");

		String[] ignore = { "ID", "DIRECTION_ID" };
		System.err.println("EXCPECTED: " + expectedTable.toString());
		System.err.println("ACTUAL: " + actualTable.toString());
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, ignore);
	}

	@Test
	public void testDelete() throws SQLException, Exception {
		groupDao.delete(group);

		IDataSet databaseDataSet = getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("GROUPS");

		IDataSet expectedDataSet = getXmlDataSetFromPath("resources/com/softserve/entity/group/group-data-delete.xml");
		ITable expectedTable = expectedDataSet.getTable("GROUPS");

		String[] ignore = { "ID", "DIRECTION_ID" };
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, ignore);
	}

	@Test
	public void testfindAll() throws DataSetException, Exception {
		List<Group> groups = groupDao.findAll(Group.class);
		IDataSet databaseDataSet = getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("GROUPS");

		IDataSet expectedDataSet = getDataSet();
		ITable expectedTable = expectedDataSet.getTable("GROUPS");

		String[] ignore = { "ID", "DIRECTION_ID" };
		assertEquals(groups.size(), actualTable.getRowCount());
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, ignore);
	}
	/*
	 * private static GroupDAO groupDAO = new GroupDAO(); private static Group
	 * Lv112DB; private static Group Lv113DB;
	 * 
	 * @BeforeClass public static void setUpBeforeClass() throws Exception {
	 * Lv112DB = new Group("LV-112"); Lv113DB = new Group("LV-113");
	 * groupDAO.create(Lv112DB); groupDAO.create(Lv113DB); }
	 * 
	 * @Test public void testFindByName() { Group group = new Group("LV-112");
	 * Group findGroup = groupDAO.findByName(Group.class, "LV-112");
	 * assertEquals(group, findGroup); }
	 * 
	 * @Test public void testCreate() { Group group = new Group("LV-112"); Group
	 * foundGroup = groupDAO.findByName(Group.class, "LV-112");
	 * assertEquals(group, foundGroup); }
	 * 
	 * @Test public void testUpdate() { //TODO
	 * 
	 * }
	 * 
	 * @Test public void testDelete() { //TODO }
	 * 
	 * @Test public void testFindByID() { Group group = new Group("LV-112");
	 * Long elementID = Lv112DB.getId(); Group foundGroup =
	 * groupDAO.findById(Group.class, elementID); assertEquals(group,
	 * foundGroup); }
	 * 
	 * @Test public void testFindAll() { Group firstGroup = new Group("LV-112");
	 * Group secondGroup = new Group("LV-113"); List<Group> actual =
	 * groupDAO.findAll(Group.class); assertTrue(actual.contains(firstGroup) &&
	 * actual.contains(secondGroup)); }
	 * 
	 * @Test public void testExecuteQuery() { //TODO }
	 * 
	 * @Test public void testFindByIdNull() { Group actual =
	 * groupDAO.findById(Group.class, 150L); assertNull(actual); }
	 * 
	 * @AfterClass public static void tearDownAfterClass() throws Exception {
	 * groupDAO.delete(Lv112DB); groupDAO.delete(Lv113DB); }
	 */
}
