package com.ita.softserveinc.achiever.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.ita.softserveinc.achiever.entity.Subtopic;
import com.ita.softserveinc.achiever.entity.Topic;
import com.ita.softserveinc.achiever.exception.SubtopicException;

@Repository
public class SubtopicDaoImpl extends GenericDaoImpl<Subtopic> implements
		ISubtopicDao {

	public SubtopicDaoImpl() {

	}

	@Override
	public Subtopic findByName(String name) {
		Subtopic resultSubtopic = null;
		try {
			resultSubtopic = entityManager
					.createNamedQuery("Subtopic.findByName", Subtopic.class)
					.setParameter("name", name).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}		
		return resultSubtopic;
	}

	@Override
	public List<Subtopic> findByTopic(Topic topic) throws SubtopicException {
		List<Subtopic> foundresult = null;
		try {
			foundresult = entityManager
					.createNamedQuery("Subtopic.findByTopic", Subtopic.class)
					.setParameter("topic", topic).getResultList();
		} catch (NoResultException e) {
			throw new SubtopicException("there's no subtopics");
		}
		return foundresult;

	}


}
