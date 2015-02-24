package com.ita.softserveinc.achiever.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.QuizResult;
import com.ita.softserveinc.achiever.entity.Subtopic;
import com.ita.softserveinc.achiever.exception.NotEnoughtElementsExeption;
import com.ita.softserveinc.achiever.service.IAnswerService;
import com.ita.softserveinc.achiever.service.IDirectionService;
import com.ita.softserveinc.achiever.service.IGroupService;
import com.ita.softserveinc.achiever.service.IQuestionService;
import com.ita.softserveinc.achiever.service.IQuizResultService;
import com.ita.softserveinc.achiever.service.ISubtopicService;
import com.ita.softserveinc.achiever.service.ITopicService;
import com.ita.softserveinc.achiever.service.IUserAnswerService;
import com.ita.softserveinc.achiever.service.IUserService;
import com.ita.softserveinc.achiever.tool.QuizResultFormBean;

/**
 * @author Ruslan Didyk
 */
@Controller
public class TestingController {
	private static final Logger logger = LoggerFactory
			.getLogger(TestingController.class);

	@Autowired
	private IQuizResultService quizService;

	@Autowired
	private IDirectionService directionService;

	@Autowired
	private IGroupService groupService;

	@Autowired
	private ITopicService topicService;

	@Autowired
	private ISubtopicService subtopicService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IQuestionService questionService;

	@Autowired
	private IAnswerService answerService;

	@Autowired
	private IUserAnswerService userAnswerService;

	@RequestMapping(value = "/testing/takeTheTest")
	public ModelAndView takeTheTest() {
		return new ModelAndView("takeTheTest");
	}

	@RequestMapping(value = "/testing/my-tests")
	public String showUserTesting(Model model, Principal principal) {
		String login = principal.getName();
		model.addAttribute("quizResult", quizService.findAllByUser(login));
		return "testing/my-tests";
	}

	@RequestMapping(value = "/testing/my-tests/{id}")
	public String showTestDetail(Model model, @PathVariable long id) {
		QuizResult quiz = quizService.findById(id);
		model.addAttribute("quizResult", quizService.findOne(quiz));
		return "testing/my-test-detail";
	}

	@RequestMapping(value = "/testing/generate", method = RequestMethod.GET)
	public String showTestForm(Model model) {
		model.addAttribute("quizResultFormBean", new QuizResultFormBean(
				new QuizResult()));
		model.addAttribute("groupList", groupService.findCurrentGroups());
		model.addAttribute("subtopicList", subtopicService.findAll());
		return "/testing/generate-tests";
	}

	@RequestMapping(value = "/testing/addTest", method = RequestMethod.POST)
	public String addTest(
			@ModelAttribute("quizResultFormBean") QuizResultFormBean quizResultFormBean) {
		Group group = groupService.findByName(quizResultFormBean.getGroup().getGroupName());
		Subtopic subtopic = subtopicService.findByName(quizResultFormBean.getSubtopic().getName());
		int countOfQuestions = quizResultFormBean.getCountOfQuestions();
		String name = quizResultFormBean.getQuizResult().getName();
		try {
			quizService.generateTest(name, group, subtopic, countOfQuestions);
		} catch (NotEnoughtElementsExeption e) {
			return "redirect:/testing/generate?" + e.getMessage();
		}
		return "redirect:/testing/generate?success=true";
	}
}
