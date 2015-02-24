package com.ita.softserveinc.achiever.dao;

import java.sql.SQLException;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;

import com.ita.softserveinc.achiever.config.DBUnitConfig;
import com.ita.softserveinc.achiever.entity.Subtopic;



@RunWith(JUnit4.class)
public class SubtopicDaoTestDBUnit extends DBUnitConfig implements IDaoTest {

	@Autowired
	private ISubtopicDao subtopicDao;
	private Subtopic hibernate = new Subtopic("Hibernate");
	private Subtopic jdbc = new Subtopic("jdsc");

	public SubtopicDaoTestDBUnit() {
		super("SubtopicDao");
	}

	public void setUp() throws Exception {
		super.setUp();
		beforeData = getXmlDataSetFromPath("resources/com/softserve/entity/subtopic/subtopic-data-table.xml");
		tester.setDataSet(beforeData);
		tester.onSetup();
	}

	@Test
	public void createTest() throws SQLException, Exception {

		subtopicDao.create(hibernate);

		IDataSet databaseDataSet = getConnection().createDataSet();
		ITable actualITable = databaseDataSet.getTable("SUBTOPICS");

		IDataSet expectedDataSet = getXmlDataSetFromPath("resources/com/softserve/entity/subtopic/subtopic-data-table-create.xml");
		ITable expectedITable = expectedDataSet.getTable("SUBTOPICS");

		String[] ignore = { "ID", "LAST_UPDATE", "CREATE", "version" };
		System.err.println("EXPECTED: " + expectedITable.toString());
		System.err.println("ACTUAL: " + actualITable.toString());
		Assertion.assertEqualsIgnoreCols(expectedITable, actualITable, ignore);
	}

	public void updateTest() throws SQLException, Exception {
		// TODO Auto-generated method stub

	}

	public void deleteTest() throws SQLException, Exception {
		// TODO Auto-generated method stub

	}

	public void findAllTest() throws SQLException, Exception {
		// TODO Auto-generated method stub

	}

}