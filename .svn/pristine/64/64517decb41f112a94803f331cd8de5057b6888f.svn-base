package com.ita.softserveinc.achiever.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.ita.softserveinc.achiever.entity.Answer;
import com.ita.softserveinc.achiever.entity.Question;

/**
 * @author Ruslan Didyk
 */
@Repository
public class AnswerDaoImpl extends GenericDaoImpl<Answer> implements IAnswerDao {

	@Override
	public Answer findByAnswer(Answer answer) {
		Answer foundresult = null;
		try {
			foundresult = entityManager
					.createNamedQuery("Answer.findByAnswer", Answer.class)
					.setParameter("name", answer.getName())
					.setParameter("correct", answer.getIsCorrect())
					.setParameter("question", answer.getQuestion())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		return foundresult;
	}

	@Override
	public List<Answer> findByQuestion(Question question) {
		List<Answer> foundresult = null;
		try {
			foundresult = entityManager
					.createNamedQuery("Answer.findByQuestion", Answer.class)
					.setParameter("question", question).getResultList();
		} catch (NoResultException e) {
			return null;
		}
		return foundresult;
	}

}
