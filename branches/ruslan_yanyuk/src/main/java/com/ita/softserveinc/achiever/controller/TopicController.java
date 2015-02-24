package com.ita.softserveinc.achiever.controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ita.softserveinc.achiever.entity.Direction;
import com.ita.softserveinc.achiever.entity.Topic;
import com.ita.softserveinc.achiever.service.IDirectionService;
import com.ita.softserveinc.achiever.service.ISubtopicService;
import com.ita.softserveinc.achiever.service.ITopicService;

/**
 * @author Taras Hrytsko
 *
 */
@Controller
public class TopicController {

	private static final Logger LOG = LoggerFactory
			.getLogger(TopicController.class);

	@Autowired
	private ITopicService topicService;

	@Autowired
	private ISubtopicService subtopicService;

	@Autowired
	private IDirectionService directionService;

	/**
	 * @param map
	 * @return topics
	 */
	@RequestMapping("/topics")
	public String listQuestions(Map<String, Object> map) {
		map.put("topic", new Topic());
		map.put("topicList", topicService.findAll());
		return "topic/topic";
	}

	/**
	 * @param map
	 * @return newtopic
	 */
	@RequestMapping("/newtopic")
	public String newTopic(Map<String, Object> map) {
		map.put("topic", new Topic());
		map.put("directionList", directionService.findAll());
		// map.put("topicList", topicService.findAll());
		return "topic/newtopic";
	}

	/**
	 * @param topic
	 * @param result
	 * @return subtopics
	 */
	@RequestMapping(value = "/addTopic", method = RequestMethod.POST)
	public String addTopic(@ModelAttribute("topic") Topic topic,
			BindingResult result) {
		LOG.info("Topic: " + topic.getTopicName());
		if (topic.getDirections() != null) {
			Set<Direction> directions = new HashSet<Direction>();
			for (Direction direction : topic.getDirections()) {
				directions
						.add(directionService.findByName(direction.getName()));
			}
			LOG.info("found directions set size: " + directions.size());
			topic.setDirections(directions);
		} else {
			LOG.info("there's no directions");
		}
		try {
			topicService.create(topic);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/topics";
	}

	/**
	 * 
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/topic/edit/{topicId}")
	public String editTopic(@PathVariable("topicId") Long id,
			Map<String, Object> map) {
		LOG.info("Edit topic: " + topicService.findById(id));
		map.put("topic", topicService.findById(id));
		map.put("directionList", directionService.findAll());
		return "topic/edittopic";
	}

	/**
	 * @param topic
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/topic/edit/editTopic", method = RequestMethod.POST)
	public String editTopic2(@Valid @ModelAttribute("topic") Topic topic,
			BindingResult result) {
		LOG.trace("editTopic id = " + topic.getId());
		Topic editableTopic = topicService.findById(topic.getId());
		LOG.info("found in db: id = " + editableTopic.getId());
		if (result.hasErrors()) {
			LOG.error("There are some errors");
			return "topic/edittopic";
		}
		editableTopic.setTopicName(topic.getTopicName());
		try {
			Set<Direction> directions = new HashSet<Direction>();
			for (Direction direction : topic.getDirections()) {
				directions
						.add(directionService.findByName(direction.getName()));
			}
			editableTopic.setDirections(directions);
			topicService.update(editableTopic);
		} catch (Exception e) {
			result.rejectValue("topicName", "error.topic",
					"this topic already exists");
			return "edittopic";
		}
		return "redirect:/topics";
	}

	/**
	 * @param id
	 * @return topics
	 */
	@RequestMapping("/topic/delete/{topicId}")
	public String deletetopic(@PathVariable("topicId") Long id) {
		Topic topic = topicService.findById(id);
		topicService.delete(topic);
		return "redirect:/topics";
	}
}
