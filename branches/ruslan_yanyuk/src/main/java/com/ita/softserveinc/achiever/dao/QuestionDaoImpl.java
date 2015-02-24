package com.ita.softserveinc.achiever.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.ita.softserveinc.achiever.entity.Question;
import com.ita.softserveinc.achiever.entity.Subtopic;

/**
 * 
 * @author Taras Hrytsko
 *
 */
@Repository
public class QuestionDaoImpl extends GenericDaoImpl<Question> implements
		IQuestionDao {

	public QuestionDaoImpl() {

	}

	@Override
	public Question findByName(String name) {
		Question foundresult = null;
		try {
			foundresult = entityManager
					.createNamedQuery("Question.findByName", Question.class)
					.setParameter("name", name).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		return foundresult;
	}

	@Override
	public Question findByQuestion(Question question) {
		Question foundresult = null;
		try {
			foundresult = entityManager
					.createNamedQuery("Question.findByQuestion", Question.class)
					.setParameter("question", question).getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
		return foundresult;
	}

	@Override
	public List<Question> findBySubtopic(Subtopic subtopic) {
		List<Question> foundresult = null;
		try {
			foundresult = entityManager
					.createNamedQuery("Question.findBySubtopic", Question.class)
					.setParameter("subtopic", subtopic).getResultList();
		} catch (NoResultException e) {
			return new ArrayList<Question>();
		}
		return foundresult;

	}

}
