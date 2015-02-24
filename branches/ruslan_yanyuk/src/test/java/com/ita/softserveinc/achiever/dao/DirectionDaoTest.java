package com.ita.softserveinc.achiever.dao;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.dbunit.dataset.DataSetException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.ita.softserveinc.achiever.entity.Direction;
import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.Topic;

/**
 * 
 * @author Andriana
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
@DatabaseSetup("file:src/test/resources/com/ita/softserveinc/achiever/entity/direction/direction-data.xml")
public class DirectionDaoTest implements IDaoTest {

	@Autowired
	private IDirectionDao directionDAO;
	@Autowired
	private IGroupDao groupDao;
	@Autowired
	private ITopicDao topicDao;

	private Direction ruby = new Direction("Ruby");
	private Direction java = new Direction("Java");
	private Direction cSharp = new Direction("C#");
	private Group group113 = new Group();
	private Group group114 = new Group();
	private Topic oop = new Topic();
	private Topic ood = new Topic();

	@Test
	public void findAllTest() throws DataSetException, Exception {
		List<Direction> directions = directionDAO.findAll(Direction.class);
		assertEquals(directions.size(), 2);
	}

	@Test
	@ExpectedDatabase(value = "file:src/test/resources/com/ita/softserveinc/achiever/entity/direction/direction-data-create.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createTest() throws SQLException, Exception {
		directionDAO.create(ruby);
	}

	@Test
	@ExpectedDatabase(value = "file:src/test/resources/com/ita/softserveinc/achiever/entity/direction/direction-data-delete.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void deleteTest() throws SQLException, Exception {
		directionDAO.delete(ruby);
	}

	@Test
	@ExpectedDatabase(value = "file:src/test/resources/com/ita/softserveinc/achiever/entity/direction/direction-data-update.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void updateTest() throws SQLException, Exception {
		java = directionDAO.findByName("Java");
		java.setName("Android");
		directionDAO.update(java);
	}

	@Test
	@ExpectedDatabase(value = "file:src/test/resources/com/ita/softserveinc/achiever/entity/direction/group-direction-data.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void addGroupTest() throws SQLException, Exception {
		group113.setGroupName("LV-113");
		java = directionDAO.findByName("Java");
		group113.setDirection(java);
		groupDao.create(group113);

		group114.setGroupName("LV-114");
		java = directionDAO.findByName("Java");
		group114.setDirection(java);
		groupDao.create(group114);
	}

	@Test
	@ExpectedDatabase(value = "file:src/test/resources/com/ita/softserveinc/achiever/entity/direction/topic-direction-data.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void addTopicTest() throws SQLException, Exception {
		oop.setTopicName("OOP");
		java = directionDAO.findByName("Java");
		cSharp = directionDAO.findByName("C#");
		oop.addDirection(java);
		oop.addDirection(cSharp);
		topicDao.create(oop);

		ood.setTopicName("OOD");
		java = directionDAO.findByName("Java");
		cSharp = directionDAO.findByName("C#");
		ood.addDirection(java);
		ood.addDirection(cSharp);
		topicDao.create(ood);
	}

}
