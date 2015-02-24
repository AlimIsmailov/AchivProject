package com.ita.softserveinc.achiever.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ita.softserveinc.achiever.dao.IAnswerDao;
import com.ita.softserveinc.achiever.entity.Answer;
import com.ita.softserveinc.achiever.entity.Question;
import com.ita.softserveinc.achiever.exception.ElementExistsException;
import com.ita.softserveinc.achiever.paging.PagingCounter;
/**
 * @author Ruslan Didyk
 */
@Service
public class AnswerServiceImpl implements IAnswerService{
	
	@Autowired
	private IAnswerDao answerDao;
	
	@Transactional (propagation = Propagation.REQUIRED)
	public void create(Answer entity) throws ElementExistsException{
		if (findAll().contains(entity)){
			throw new ElementExistsException();
		}
		answerDao.create(entity);
	}

	@Transactional (propagation = Propagation.REQUIRED)
	public Answer update(Answer entity) throws ElementExistsException {
		if (findAll().contains(entity)){
			throw new ElementExistsException();
		}
		return answerDao.update(entity);
	}

	@Transactional (propagation = Propagation.REQUIRED)
	public void delete(Answer entity) {
		answerDao.delete(entity);
	}

	public Answer findById(Long id) {
		return answerDao.findById(Answer.class, id);
	}

	public List<Answer> findAll() {
		return answerDao.findAll(Answer.class);
	}

	@Override
	public Answer findByAnswer(Answer answer) {
		return answerDao.findByAnswer(answer);
	}

	@Override
	public List<Answer> findByQuestion(Question question) {
		return answerDao.findByQuestion(question);
	}
}
