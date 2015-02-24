/**
 * 
 */
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
import com.ita.softserveinc.achiever.entity.Answer;
import com.ita.softserveinc.achiever.entity.Question;

/**
 * @author Taras Hrytsko
 * 
 */
@RunWith(JUnit4.class)
public class QuestionDaoTest extends DBUnitConfig implements IDaoTest {

	@Autowired
	private IQuestionDao questionDAO;
	Question question1 = new Question();
	Question question2 = new Question();
	Question question3 = new Question();

	public QuestionDaoTest() {
		super("QuestionDao");
	}

	public void setUp() throws Exception {
		super.setUp();

		question1.setName("Java");
		question1.addAnswer(new Answer("answer1",true));

		question2.setName("C#");
		question2.addAnswer(new Answer("answer2",true));

		beforeData = getXmlDataSetFromPath("resources/com/softserve/entity/question/question-data.xml");
		tester.setDataSet(beforeData);
		tester.onSetup();
	}

	@Test
	public void findAllTest() throws DataSetException, Exception {
		List<Question> questions = questionDAO.findAll(Question.class);
		IDataSet databaseDataSet = getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("QUESTIONS");

		IDataSet expectedDataSet = getDataSet();
		ITable expectedTable = expectedDataSet.getTable("QUESTIONS");

		String[] ignore = { "ID", "SUBTOPIC_ID", "LAST_UPDATE", "CREATED",
				"version" };
		assertEquals(questions.size(), actualTable.getRowCount());
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, ignore);
	}

	@Test
	public void createTest() throws SQLException, Exception {
		question3.setName("Ruby");
		question3.addAnswer(new Answer("answer3",true));
		questionDAO.create(question3);

		IDataSet databaseDataSet = getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("QUESTIONS");

		IDataSet expectedDataSet = getXmlDataSetFromPath("resources/com/softserve/entity/question/question-data-create.xml");
		ITable expectedTable = expectedDataSet.getTable("QUESTIONS");

		String[] ignore = { "ID", "SUBTOPIC_ID", "LAST_UPDATE", "CREATED",
				"version" };
		System.err.println("EXCPECTED: " + expectedTable.toString());
		System.err.println("ACTUAL: " + actualTable.toString());
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, ignore);
	}

	@Test
	public void deleteTest() throws SQLException, Exception {
		questionDAO.delete(question3);

		IDataSet databaseDataSet = getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("QUESTIONS");

		IDataSet expectedDataSet = getXmlDataSetFromPath("resources/com/softserve/entity/question/question-data-delete.xml");
		ITable expectedTable = expectedDataSet.getTable("QUESTIONS");

		String[] ignore = { "ID", "SUBTOPIC_ID", "LAST_UPDATE", "CREATED",
				"version" };
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, ignore);
	}

	@Test
	public void updateTest() throws SQLException, Exception {
		question1 = questionDAO.findByName("Java");
		question1.setName("Android");
		questionDAO.update(question1);

		IDataSet databaseDataSet = getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("QUESTIONS");

		IDataSet expectedDataSet = getXmlDataSetFromPath("resources/com/softserve/entity/question/question-data-update.xml");
		ITable expectedTable = expectedDataSet.getTable("QUESTIONS");

		String[] ignore = { "ID", "SUBTOPIC_ID", "LAST_UPDATE", "CREATED",
				"version" };
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, ignore);
	}

}
