package com.ita.softserveinc.achiever.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ita.softserveinc.achiever.entity.QuizResult;
import com.ita.softserveinc.achiever.entity.User;

@Repository
public class QuizResultDaoImpl extends GenericDaoImpl<QuizResult> implements
		IQuizResultDao {
	private static final Logger LOG = Logger.getLogger(GroupDaoImpl.class);

	public QuizResultDaoImpl() {
		super();
	}

	@Override
	public List<QuizResult> findByUser(User user) {
		List<QuizResult> quizResults = null;
		try {
			quizResults = entityManager
					.createNamedQuery("QuizResult.getByUser", QuizResult.class)
					.setParameter("user", user).getResultList();
		} catch (NoResultException e) {
			LOG.info("No one quilzResult in this user were found");
		}
		return quizResults;
	}
}
