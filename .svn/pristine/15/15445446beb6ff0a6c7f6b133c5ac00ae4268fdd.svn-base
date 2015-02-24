package com.ita.softserveinc.achiever.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.QuizResult;
import com.ita.softserveinc.achiever.entity.Subtopic;
import com.ita.softserveinc.achiever.exception.NotEnoughtElementsExeption;
import com.ita.softserveinc.achiever.service.IGroupService;
import com.ita.softserveinc.achiever.service.IQuizResultService;
import com.ita.softserveinc.achiever.service.ISubtopicService;
import com.ita.softserveinc.achiever.service.IUserService;
import com.ita.softserveinc.achiever.tool.GenerateTestFormBean;

/**
 * @author Ruslan Didyk
 */
@Controller
public class TestingController {
	private static final Logger LOG = LoggerFactory
			.getLogger(TestingController.class);

	@Autowired
	private IQuizResultService quizService;

	@Autowired
	private IGroupService groupService;

	@Autowired
	private ISubtopicService subtopicService;

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/testing/my-tests", method = RequestMethod.GET)
	public String showUserTesting(Model model, Principal principal) {
		String login = principal.getName();
		model.addAttribute("quizResult", new QuizResult());
		model.addAttribute("quizResultList", quizService.findAllByUser(login));
		return "testing/my-tests";
	}

	@RequestMapping(value = "/testing/my-tests", method = RequestMethod.POST)
	public String showTestDetail(Model model,
			@ModelAttribute("quizResult") QuizResult quizResult) {
		QuizResult quiz = quizService.findById(quizResult.getId());
		model.addAttribute("quizResult", quiz);
		return "testing/my-test-detail";
	}

	@RequestMapping(value = "/testing/generate", method = RequestMethod.GET)
	public String showGenereTestForm(Model model) {
		model.addAttribute("testFormBean", new GenerateTestFormBean());
		model.addAttribute("groupList", groupService.findCurrentGroups());
		model.addAttribute("subtopicList", subtopicService.findAll());
		model.addAttribute("counts", quizService.getCount());
		return "/testing/generate-tests";
	}

	@RequestMapping(value = "/testing/addTest", method = RequestMethod.POST)
	public String addTest(
			@Valid @ModelAttribute("testFormBean") GenerateTestFormBean testFormBean,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute("groupList", groupService.findCurrentGroups());
			model.addAttribute("subtopicList", subtopicService.findAll());
			model.addAttribute("counts", quizService.getCount());
			return "/testing/generate-tests";
		}
		Group group = groupService.findByName(testFormBean.getGroupName());
		Subtopic subtopic = subtopicService.findByName(testFormBean
				.getSubtopicName());
		int countOfQuestions = testFormBean.getCountOfQuestions();
		String name = testFormBean.getQuizName();
		try {
			quizService.generateTest(name, group, subtopic, countOfQuestions);
		} catch (NotEnoughtElementsExeption e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/testing/generate";
		}
		redirectAttributes.addFlashAttribute("success", true);
		return "redirect:/testing/generate";
	}

	@RequestMapping(value = "/testing/pass-the-test", method = RequestMethod.POST)
	public String passTheTestForm(Model model,
			@ModelAttribute("quizResult") QuizResult quizResult) {
		QuizResult quiz = quizService.findById(quizResult.getId());
		model.addAttribute("quiz", quiz);
		return "testing/pass-the-test";
	}

	@RequestMapping(value = "/testing/pass-the-test/finish", method = RequestMethod.POST)
	public String finishTest(@ModelAttribute("quiz") QuizResult quizResult) {
		return "redirect:/testing/my-tests";
	}

}
