package com.ita.softserveinc.achiever.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

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
import com.ita.softserveinc.achiever.entity.QuizResult;
import com.ita.softserveinc.achiever.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
@DatabaseSetup("file:src/test/resources/com/ita/softserveinc/achiever/entity/quizresult/before-data.xml")
public class QuizResultTest {

	@Autowired
	private IQuizResultDao quizResultDao;
	@Autowired
	private IUserDao userDao;

	private QuizResult quizResult;
	private User user;

	@Test
	@ExpectedDatabase(value = "file:src/test/resources/com/ita/softserveinc/achiever/entity/quizresult/data-create.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void testCreate() throws Exception {

		user = userDao.findById(User.class, 1L);

		quizResult = new QuizResult();
		quizResult.setName("name3");
		quizResult.setTotalGrade(50.0);
		quizResult.setUser(user);
		quizResultDao.create(quizResult);

	}

	@Test
	@ExpectedDatabase(value = "file:src/test/resources/com/ita/softserveinc/achiever/entity/quizresult/data-update.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void testUpdate() {
		quizResult = quizResultDao.findById(QuizResult.class, 2L);
		quizResult.setName("update");
		quizResultDao.update(quizResult);
	}

	@Test
	@ExpectedDatabase(value = "file:src/test/resources/com/ita/softserveinc/achiever/entity/quizresult/data-delete.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void testDelete() {
		QuizResult qResult = quizResultDao.findById(QuizResult.class, 2L);
		quizResultDao.delete(qResult);
	}

	@Test
	public void testFindById() {
		QuizResult quizResult = quizResultDao.findById(QuizResult.class, 1L);
		assertEquals("name1", quizResult.getName());
	}

	@Test
	public void testFindAll() {
		List<QuizResult> quizResult = quizResultDao.findAll(QuizResult.class);
		assertEquals(2, quizResult.size());
	}

}
