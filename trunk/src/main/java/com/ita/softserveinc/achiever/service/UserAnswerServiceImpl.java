package com.ita.softserveinc.achiever.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ita.softserveinc.achiever.dao.IUserAnswerDao;
import com.ita.softserveinc.achiever.entity.UserAnswer;

@Service
public class UserAnswerServiceImpl implements IUserAnswerService {

	@Autowired
	private IUserAnswerDao userAnswerDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public void create(UserAnswer entity) {
		userAnswerDao.create(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public UserAnswer update(UserAnswer entity) {
		return userAnswerDao.update(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(UserAnswer entity) {
		userAnswerDao.delete(entity);
	}

	public UserAnswer findById(Long id) {
		return userAnswerDao.findById(UserAnswer.class, id);
	}

	public List<UserAnswer> findAll() {
		return userAnswerDao.findAll(UserAnswer.class);
	}

}
