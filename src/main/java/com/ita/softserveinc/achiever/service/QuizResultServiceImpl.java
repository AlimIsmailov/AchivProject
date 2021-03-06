package com.ita.softserveinc.achiever.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

import com.ita.softserveinc.achiever.dao.IGroupDao;
import com.ita.softserveinc.achiever.dao.IQuizResultDao;
import com.ita.softserveinc.achiever.dao.ISubtopicDao;
import com.ita.softserveinc.achiever.dao.IUserAnswerDao;
import com.ita.softserveinc.achiever.dao.IUserDao;
import com.ita.softserveinc.achiever.entity.Answer;
import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.Question;
import com.ita.softserveinc.achiever.entity.QuizResult;
import com.ita.softserveinc.achiever.entity.Subtopic;
import com.ita.softserveinc.achiever.entity.User;
import com.ita.softserveinc.achiever.entity.UserAnswer;
import com.ita.softserveinc.achiever.exception.NotEnoughElementsExeption;
import com.ita.softserveinc.achiever.tool.Count;
import com.ita.softserveinc.achiever.tool.GenerateTestFormBean;

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

	@Autowired
	private IGroupDao groupDao;

	@Autowired
	private ISubtopicDao subtopicDao;

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
	public void generateTest(GenerateTestFormBean testFormBean)
			throws NotEnoughElementsExeption {
		Group group = groupDao.findByName(testFormBean.getGroupName());
		Subtopic subtopic = subtopicDao.findByName(testFormBean
				.getSubtopicName());
		int countOfQuestions = testFormBean.getCountOfQuestions();
		String name = testFormBean.getQuizName();
		boolean review = testFormBean.isReview();
		generateTest(name, group, subtopic, countOfQuestions, review);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void generateTest(String name, Group group, Subtopic subtopic,
			int countOfQuestions, boolean review) throws NotEnoughElementsExeption {
		List<User> users = userService.findStudentsByGroup(group);
		if (users.isEmpty()) {
			throw new NotEnoughElementsExeption("empty-group");
		}
		for (User user : users) {
			List<Question> questions = getRandomQuestions(subtopic,
					countOfQuestions);
			QuizResult quizResult = new QuizResult();
			quizResult.setName(name);
			quizResult.setReview(review);
			quizResult.setUser(user);
			quizResultDao.create(quizResult);
			addQuestions(questions, quizResult);
		}
	}

	public List<Question> getRandomQuestions(Subtopic subtopic,
			int countOfQuestions) throws NotEnoughElementsExeption {
		List<Question> questions = subtopic.getQuestions();
		Collections.shuffle(questions);
		if (questions.size() < countOfQuestions) {
			throw new NotEnoughElementsExeption("not-enought-elements");
		}
		List<Question> resultList = new ArrayList<Question>(countOfQuestions);
		for (int i = 0; i < countOfQuestions; i++) {
			resultList.add(questions.get(i));
		}
		return resultList;
	}

	@Transactional(propagation = Propagation.REQUIRED)
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
			ClassPathResource cpr = new ClassPathResource(
					"/jaxb-xml/generate-test-configuration.xml");
			InputStream inputs = cpr.getInputStream();
			Count count = (Count) unmarshaller.unmarshal(inputs);
			return count.getCountOfQuestions();
		} catch (JAXBException e) {
			LOG.info("Can't unmarshall the file. File not found.");
		} catch (IOException e) {
			LOG.info("File not found");
		}
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void saveAndCheckTest(QuizResult quizResult,
			Map<String, List<String>> testsData) {
		List<UserAnswer> userAnswers = quizResult.getUserAnswers();
		saveAndChackAnswers(userAnswers, testsData);
		double totalGrade = Math.rint(100 * (getTotalGrade(userAnswers)
				/ userAnswers.size() * 10.0)) / 100;
		quizResult.setTotalGrade(totalGrade);
		quizResult.setPassed(true);
		quizResultDao.update(quizResult);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void saveAndChackAnswers(List<UserAnswer> userAnswers,
			Map<String, List<String>> testsData) {
		for (UserAnswer userAnswer : userAnswers) {
			Question question = userAnswer.getQuestion();
			int right = getCountOfRightAnswers(question.getAnswers());
			List<Answer> answers = getAnswers(testsData, question);
			if (answers.isEmpty()) {
				userAnswer.setAnswers(null);
				userAnswer.setAnswerGrade(0.0);
				userAnswerDao.update(userAnswer);
			} else {
				userAnswer.setAnswers(answers);
				double grade = getGradeForQuestion(answers, right);
				userAnswer.setAnswerGrade(grade);
				userAnswerDao.update(userAnswer);
			}
		}
	}

	public List<Answer> getAnswers(Map<String, List<String>> testsData,
			Question question) {
		List<String> selectedAnswers = getValuesByKey(testsData,
				question.getName());
		List<Answer> resultAnswers = new ArrayList<Answer>();
		if (selectedAnswers.isEmpty()) {
			return resultAnswers;
		}
		for (Answer answer : question.getAnswers()) {
			for (String selectedAnswer : selectedAnswers) {
				if (selectedAnswer.equals(answer.getName())) {
					resultAnswers.add(answer);
				}
			}
		}
		return resultAnswers;
	}

	public List<String> getValuesByKey(Map<String, List<String>> map,
			String questionName) {
		List<String> values = new ArrayList<String>();
		for (Entry<String, List<String>> entry : map.entrySet()) {
			if (questionName.equals(entry.getKey())) {
				if (entry.getValue() == null) {
					return values;
				}
				values.addAll(entry.getValue());
			}
		}
		return values;
	}

	public double getGradeForQuestion(List<Answer> answers,
			int countOfRightAnswers) {
		double correct = 0.0;
		for (Answer answer : answers) {
			if (!answer.getIsCorrect()) {
				return 0.0;
			} else {
				correct++;
			}
		}
		return correct / countOfRightAnswers;
	}

	public int getCountOfRightAnswers(List<Answer> answers) {
		int count = 0;
		for (Answer answer : answers) {
			if (answer.getIsCorrect()) {
				count++;
			}
		}
		return count;
	}

	public double getTotalGrade(List<UserAnswer> userAnswers) {
		double testGrade = 0.0;
		for (UserAnswer userAnswer : userAnswers) {
			testGrade = testGrade + userAnswer.getAnswerGrade();
		}
		return testGrade;
	}

	public void shuffleAnswers(QuizResult quizResult) {
		for (UserAnswer userAnswer : quizResult.getUserAnswers()) {
			Collections.shuffle(userAnswer.getQuestion().getAnswers());
		}
	}

}
