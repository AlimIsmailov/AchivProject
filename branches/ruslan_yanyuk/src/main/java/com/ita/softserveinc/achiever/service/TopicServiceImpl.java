package com.ita.softserveinc.achiever.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ita.softserveinc.achiever.dao.ISubtopicDao;
import com.ita.softserveinc.achiever.dao.ITopicDao;
import com.ita.softserveinc.achiever.entity.Subtopic;
import com.ita.softserveinc.achiever.entity.Topic;
import com.ita.softserveinc.achiever.exception.ElementExistsException;
import com.ita.softserveinc.achiever.exception.SubtopicException;

@Service
public class TopicServiceImpl implements ITopicService {

	private static final Logger LOG = LoggerFactory
			.getLogger(TopicServiceImpl.class);

	@Autowired
	private ITopicDao topicDao;

	@Autowired
	private ISubtopicDao subtopicDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public void create(Topic entity) {
		try {
			topicDao.create(entity);
		} catch (ElementExistsException e) {
			LOG.error("topic exists" + e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Topic update(Topic entity) {
		return topicDao.update(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Topic topic) {
		List<Subtopic> list = null;
		try {
			list = subtopicDao.findByTopic(topic);
		} catch (SubtopicException e) {
			LOG.error("SubtopicException" + e);
		}
		if (list != null) {
			for (Subtopic subtopic : list) {
				subtopicDao.delete(subtopic);
			}
		}
		topicDao.delete(topic);
	}

	public Topic findById(Long id) {
		return topicDao.findById(Topic.class, id);
	}

	public List<Topic> findAll() {
		return topicDao.findAll(Topic.class);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Topic findByName(String string) {
		return topicDao.findByName(string);
	}
}
