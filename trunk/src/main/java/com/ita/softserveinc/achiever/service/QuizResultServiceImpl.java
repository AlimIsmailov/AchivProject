package com.ita.softserveinc.achiever.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ita.softserveinc.achiever.dao.IQuizResultDao;
import com.ita.softserveinc.achiever.dao.IUserAnswerDao;
import com.ita.softserveinc.achiever.dao.IUserDao;
import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.Question;
import com.ita.softserveinc.achiever.entity.QuizResult;
import com.ita.softserveinc.achiever.entity.Subtopic;
import com.ita.softserveinc.achiever.entity.User;
import com.ita.softserveinc.achiever.entity.UserAnswer;
import com.ita.softserveinc.achiever.exception.NotEnoughtElementsExeption;
import com.ita.softserveinc.achiever.tool.Count;

/**
 * @author Ruslan Didyk
 */
@Service
public class QuizResultServiceImpl implements IQuizResultService {
	private static final Logger LOG = LoggerFactory
			.getLogger(QuizResultServiceImpl.class);

	@Autowired
	private IQuizResultDao quizResultDao;

	@Autowired
	private IUserDao userDao;

	@Autowired
	private IUserService userService;

	@Autowired
	private IUserAnswerDao userAnswerDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public void create(QuizResult entity) {
		quizResultDao.create(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public QuizResult update(QuizResult entity) {
		return quizResultDao.update(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(QuizResult entity) {
		quizResultDao.delete(entity);
	}

	public QuizResult findById(Long id) {
		return quizResultDao.findById(QuizResult.class, id);
	}

	public List<QuizResult> findAll() {
		return quizResultDao.findAll(QuizResult.class);
	}

	@Override
	public List<QuizResult> findAllByUser(String login) {
		User user = userDao.findByLogin(login);
		List<QuizResult> quizResults = quizResultDao.findByUser(user);
		return quizResults;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void generateTest(String name, Group group, Subtopic subtopic,
			int countOfQuestions) throws NotEnoughtElementsExeption {
		List<User> users = userService.findStudentsByGroup(group);
		if (users.isEmpty()) {
			throw new NotEnoughtElementsExeption("empty-group");
		}
		for (User user : users) {
			List<Question> questions = getRandomQuestions(subtopic,
					countOfQuestions);
			QuizResult quizResult = new QuizResult();
			quizResult.setName(name);
			quizResult.setUser(user);
			quizResultDao.create(quizResult);
			addQuestions(questions, quizResult);
		}
	}

	public List<Question> getRandomQuestions(Subtopic subtopic,
			int countOfQuestions) throws NotEnoughtElementsExeption {
		List<Question> questions = subtopic.getQuestions();
		Collections.shuffle(questions);
		if (questions.size() < countOfQuestions) {
			throw new NotEnoughtElementsExeption("not-enought-elements");
		}
		List<Question> resuList = new ArrayList<Question>();
		for (int i = 0; i < countOfQuestions; i++) {
			resuList.add(questions.get(i));
		}
		return resuList;
	}

	public void addQuestions(List<Question> questions, QuizResult quizResult) {
		for (Question question : questions) {
			UserAnswer userAnswer = new UserAnswer();
			userAnswer.setQuestion(question);
			userAnswer.setQuizResult(quizResult);
			userAnswerDao.create(userAnswer);
		}
	}

	public List<Integer> getCount() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Count.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			ClassPathResource cpr = new ClassPathResource("/jaxb-xml/generate-test-configuration.xml");
			InputStream inputs =  cpr.getInputStream();
			Count count = (Count) unmarshaller.unmarshal(inputs);
			return count.getCountOfQuestions();
		} catch (JAXBException e) {
			LOG.info("JAXB Exception");
		} catch (IOException e) {
			LOG.info("File not found");
		}
		return null;
	}
}
