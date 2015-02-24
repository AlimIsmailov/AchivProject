package com.ita.softserveinc.achiever.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.ita.softserveinc.achiever.entity.Direction;
import com.ita.softserveinc.achiever.entity.Topic;

@Repository
public class TopicDaoImpl extends GenericDaoImpl<Topic> implements ITopicDao {

	public TopicDaoImpl() {

	}

	@Override
	public Topic findByName(String name) {
		Topic resultSubtopic = null;
		try {
			resultSubtopic = entityManager
					.createNamedQuery("Topic.findByName", Topic.class)
					.setParameter("name", name).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		return resultSubtopic;
	}

	@Override
	public List<Topic> findByDirectionName(String name) {
		List<Topic> foundresult = null;
		try {
			foundresult = entityManager
					.createNamedQuery("Topic.findByDirectionName", Topic.class)
					.setParameter("direction", name).getResultList();
		} catch (NoResultException e) {
			return new ArrayList<Topic>();
		}
		return foundresult;
	}

	@Override
	public List<Topic> findByDirection(Direction direction) {
		List<Topic> foundresult = null;
		try {
			foundresult = entityManager
					.createNamedQuery("Topic.findByDirection", Topic.class)
					.setParameter("direction", direction).getResultList();
		} catch (NoResultException e) {
			return new ArrayList<Topic>();
		}
		return foundresult;
	}
}