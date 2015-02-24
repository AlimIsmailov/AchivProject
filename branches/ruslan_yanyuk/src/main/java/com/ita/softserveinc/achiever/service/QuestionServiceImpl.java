package com.ita.softserveinc.achiever.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.softserveinc.achiever.dao.IAnswerDao;
import com.ita.softserveinc.achiever.dao.IQuestionDao;
import com.ita.softserveinc.achiever.entity.Answer;
import com.ita.softserveinc.achiever.entity.Question;
import com.ita.softserveinc.achiever.entity.Subtopic;
import com.ita.softserveinc.achiever.exception.ElementExistsException;

/**
 * @author Taras Hrytsko
 *
 */
@Service
@Transactional
public class QuestionServiceImpl implements IQuestionService {

	private static final Logger LOG = LoggerFactory
			.getLogger(QuestionServiceImpl.class);

	@Autowired
	private IQuestionDao questionDao;

	@Autowired
	private IAnswerDao answerDao;

	public void create(Question entity) throws ElementExistsException {
		LOG.trace("QuestionService create");
		if (findAll().contains(entity)) {
			throw new ElementExistsException();
		}
		questionDao.create(entity);
	}

	public Question update(Question entity) {
		LOG.trace("QuestionService update");
		return questionDao.update(entity);
	}

	public void delete(Question question) {
		LOG.trace("QuestionService delete");
		List<Answer> list = null;
		list = answerDao.findByQuestion(question);
		if (list != null) {
			for (Answer answer : list) {
				answerDao.delete(answer);
			}
		}
		questionDao.delete(question);
	}

	public Question findById(Long id) {
		LOG.trace("QuestionService findById "+id);
		return questionDao.findById(Question.class, id);
	}

	public List<Question> findAll() {
		LOG.trace("QuestionService findAll");
		return questionDao.findAll(Question.class);
	}

	@Override
	public Question findByName(String name) {
		LOG.trace("QuestionService findByName");
		return questionDao.findByName(name);
	}

	@Override
	public Question findByQuestion(Question question) {
		LOG.trace("QuestionService findByQuestion");
		return questionDao.findByQuestion(question);
	}

	@Override
	public List<Question> findBySubtopic(Subtopic subtopic) {
		LOG.trace("QuestionService findBySubtopic");
		return questionDao.findBySubtopic(subtopic);
	}

}
