package com.ita.softserveinc.achiever.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ita.softserveinc.achiever.dao.IQuestionDao;
import com.ita.softserveinc.achiever.dao.ISubtopicDao;
import com.ita.softserveinc.achiever.dao.ITopicDao;
import com.ita.softserveinc.achiever.entity.Question;
import com.ita.softserveinc.achiever.entity.Subtopic;
import com.ita.softserveinc.achiever.entity.Topic;
import com.ita.softserveinc.achiever.exception.ElementExistsException;
import com.ita.softserveinc.achiever.exception.SubtopicException;

@Service
public class SubtopicServiceImpl implements ISubtopicService {

	private static final Logger LOG = LoggerFactory
			.getLogger(SubtopicServiceImpl.class);

	@Autowired
	private ISubtopicDao subtopicDao;

	@Autowired
	private ITopicDao topicDao;

	@Autowired
	private IQuestionDao questionDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(Subtopic subtopic) {
		LOG.info("Service: addSubtopic");
		Topic topic = topicDao.findByName(subtopic.getTopic().getTopicName());
		LOG.info("Topic: "+topic);
		subtopic.setTopic(topic);
		try {
			subtopicDao.create(subtopic);
		} catch (ElementExistsException e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Subtopic update(Subtopic entity) {
		return subtopicDao.update(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Subtopic subtopic) {
		List<Question> list = questionDao.findBySubtopic(subtopic);
		if (list.size()>0) {
			for (Question question : list) {
				questionDao.delete(question);
			}
		}
		subtopicDao.delete(subtopic);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Subtopic findById(Long id) {
		try {
			return subtopicDao.findById(Subtopic.class, id);
		} catch (NoResultException e) {
			LOG.error("No subtopics: "+e);
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Subtopic> findAll() {
		return subtopicDao.findAll(Subtopic.class);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Subtopic findByName(String string) {
		return subtopicDao.findByName(string);
	}

	@Override
	public List<Subtopic> findByTopic(Topic topic) {
		List<Subtopic> result = null;
		try {
			result = subtopicDao.findByTopic(topic);
		} catch (SubtopicException e) {
			LOG.error("Subtopic exception:" + e);
		}
		return result;
	}

	
	@Override
	public Subtopic editSubtopic(Subtopic subtopic) {
		LOG.info("edit subtopic: "+subtopic);
		Subtopic editableSubtopic = subtopicDao.findById(Subtopic.class, subtopic.getId());
		LOG.info("edit subtopic from db: "+editableSubtopic);
		LOG.info("id= "+subtopic.getId());
		if (subtopic.getName().equals(editableSubtopic.getName())
				&& (subtopic.getTopic().equals(editableSubtopic.getTopic()))) {
			return subtopic;
		}
		editableSubtopic.setName(subtopic.getName());
		editableSubtopic.setTopic(topicDao.findByName(subtopic.getTopic()
				.getTopicName()));
		subtopicDao.update(editableSubtopic);
		LOG.info("edited subtopic: "+editableSubtopic);
		return editableSubtopic;

	}

}
