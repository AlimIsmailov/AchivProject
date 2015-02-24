package com.ita.softserveinc.achiever.controller;

import java.util.Map;

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

import com.ita.softserveinc.achiever.entity.Subtopic;
import com.ita.softserveinc.achiever.service.IArticleService;
import com.ita.softserveinc.achiever.service.IQuestionService;
import com.ita.softserveinc.achiever.service.ISubtopicService;
import com.ita.softserveinc.achiever.service.ITopicService;

/**
 * @author Taras Hrytsko
 *
 */
@Controller
@RequestMapping("/subtopics")
public class SubtopicController {

	private static final Logger LOG = LoggerFactory
			.getLogger(QuestionController.class);

	@Autowired
	private ISubtopicService subtopicService;

	@Autowired
	private IQuestionService questionService;

	@Autowired
	private ITopicService topicService;

	@Autowired
	private IArticleService articleService;

	@Autowired
	private TopicController topicController;

	/**
	 * @param map
	 * @return subtopics
	 */
	@RequestMapping("/all")
	public String listSubtopics(Map<String, Object> map) {
		// map.put("subtopic", new Subtopic());
		map.put("subtopicList", subtopicService.findAll());
		return "subtopic/subtopic";
	}

	/**
	 * @param map
	 * @return redirect to newsubtopic
	 */
	@RequestMapping("/newsubtopic")
	public String newSubtopic(Map<String, Object> map) {
		map.put("subtopic", new Subtopic());
		map.put("topicList", topicService.findAll());
		return "subtopic/newsubtopic";
	}

	/**
	 * @param subtopic
	 * @param topic
	 * @param result
	 * @return subtopics
	 */
	@RequestMapping(value = "/addSubtopic", method = RequestMethod.POST)
	public String addSubtopic(@ModelAttribute("subtopic") Subtopic subtopic,
			BindingResult result) {
		LOG.info("/addsubtopic");
		subtopicService.create(subtopic);
		return "redirect:/subtopics/all";
	}

	/**
	 * @param id
	 * @return homepage
	 */
	@RequestMapping("/subtopic/edit/{subtopicId}")
	public String editSubtopic(@PathVariable("subtopicId") Long id,
			Map<String, Object> map) {
		LOG.info("/editSubtopic");
		map.put("subtopic", subtopicService.findById(id));
		map.put("topicList", topicService.findAll());
		return "subtopic/editsubtopic";
	}

	/**
	 * 
	 * @param subtopic
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/subtopic/edit/editSubtopic", method = RequestMethod.POST)
	public String editSubtopic(
			@Valid @ModelAttribute("subtopic") Subtopic subtopic,
			BindingResult result) {
		if (result.hasErrors()) {
			return "redirect:/editsubtopic";
		}
		if(subtopicService.update(subtopic)==null) {
			result.rejectValue("subtopic", "error editing");
			LOG.error("error editing");
		}
		return "redirect:/subtopics/all";
	}

	/**
	 * @param id
	 * @return homepage
	 */
	@RequestMapping("/subtopic/delete/{subtopicId}")
	public String deletesubtopic(@PathVariable("subtopicId") Long id) {
		Subtopic subtopic = subtopicService.findById(id);
		subtopicService.delete(subtopic);
		return "redirect:/subtopics/all";
	}

}
