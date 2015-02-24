package com.ita.softserveinc.achiever.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ita.softserveinc.achiever.dao.IQuizResultDao;
import com.ita.softserveinc.achiever.dao.IUserDao;
import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.Question;
import com.ita.softserveinc.achiever.entity.QuizResult;
import com.ita.softserveinc.achiever.entity.Subtopic;
import com.ita.softserveinc.achiever.entity.User;
import com.ita.softserveinc.achiever.entity.UserAnswer;
import com.ita.softserveinc.achiever.exception.ElementExistsException;
import com.ita.softserveinc.achiever.exception.NotEnoughtElementsExeption;

/**
 * @author Ruslan Didyk
 */
@Service
public class QuizResultServiceImpl implements IQuizResultService {
	private static final Logger logger = LoggerFactory
			.getLogger(QuizResultServiceImpl.class);

	@Autowired
	private IQuizResultDao quizResultDao;

	@Autowired
	private IUserDao userDao;

	@Autowired
	private IUserService userService;

	@Autowired
	private IUserAnswerService userAnswerService;

	@Transactional(propagation = Propagation.REQUIRED)
	public void create(QuizResult entity) {
		try {
			quizResultDao.create(entity);
		} catch (ElementExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	@PreAuthorize(value = "#quizResult.user.login == authentication.name")
	public QuizResult findOne(@P("quizResult") QuizResult quizResult) {
		return quizResultDao.findById(QuizResult.class, quizResult.getId());
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void generateTest(String name, Group group, Subtopic subtopic,
			int countOfQuestions) throws NotEnoughtElementsExeption {
		List<User> users = userService.findStudentsByGroup(group);
		if(users.isEmpty()){
			throw new NotEnoughtElementsExeption("error=empty-group");
		}
		for (User user : users) {
			List<Question> questions = getRandomQuestions(subtopic,
					countOfQuestions);
			QuizResult quizResult = new QuizResult();
			quizResult.setName(name);
			quizResult.setUser(user);
			try {
				quizResultDao.create(quizResult);
			} catch (ElementExistsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<UserAnswer> userAnswers = addQuestions(questions, quizResult);
			quizResult.setUserAnswers(userAnswers);
			update(quizResult);
		}
	}

	public List<Question> getRandomQuestions(Subtopic subtopic,
			int countOfQuestions) throws NotEnoughtElementsExeption{
		List<Question> questions = subtopic.getQuestions();
		Collections.shuffle(questions);
		if (questions.size() < countOfQuestions) {
			throw new NotEnoughtElementsExeption("error=not-enought-elements");
		}
		List<Question> resuList = new ArrayList<Question>();
		for (int i = 0; i < countOfQuestions; i++) {
			resuList.add(questions.get(i));
		}
		return resuList;
	}

	public List<UserAnswer> addQuestions(List<Question> questions,
			QuizResult quizResult) {
		List<UserAnswer> userAnswers = new ArrayList<UserAnswer>();
		for (Question question : questions) {
			UserAnswer userAnswer = new UserAnswer();
			userAnswer.setQuestion(question);
			userAnswer.setQuizResult(quizResult);
			try {
				userAnswerService.create(userAnswer);
			} catch (ElementExistsException e) {
				return null;
			}
		}
		return userAnswers;
	}
}
