package com.ita.softserveinc.achiever.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
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

import com.ita.softserveinc.achiever.entity.Answer;
import com.ita.softserveinc.achiever.entity.Question;
import com.ita.softserveinc.achiever.exception.ElementExistsException;
import com.ita.softserveinc.achiever.service.IAnswerService;
import com.ita.softserveinc.achiever.service.IQuestionService;
import com.ita.softserveinc.achiever.service.ISubtopicService;

/**
 * @author Taras Hrytsko
 *
 */
@Controller
public class QuestionController {

	private static final Logger LOG = LoggerFactory
			.getLogger(QuestionController.class);

	@Autowired
	private IQuestionService questionService;

	@Autowired
	private IAnswerService answerService;

	@Autowired
	private ISubtopicService subtopicService;

	/**
	 * @param map
	 * @return question
	 */
	@RequestMapping("/questions")
	public String listQuestions(Map<String, Object> map) {
		map.put("question", new Question());
		map.put("questionList", questionService.findAll());
		LOG.info(questionService.findAll().toString());
		LOG.info("/questions");
		return "question/question";
	}

	/**
	 * @param map
	 * @return newquestion
	 */
	@RequestMapping("/newquestion")
	public String newQuestionForm(Map<String, Object> map) {
		LOG.info("/newquestion");
		map.put("question", new Question());
		map.put("subtopicList", subtopicService.findAll());
		return "question/newquestion";
	}

	/**
	 * @param question
	 * @param subtopic
	 * @param result
	 * @return questions
	 */
	@RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
	public String addQuestion(
			@Valid @ModelAttribute("question") Question question,
			BindingResult result) {
		LOG.info("/addQuestion");
		if (result.hasErrors()) {
			LOG.error("there are some errors here");
			result.rejectValue("name", "error.question",
					"this question has errors!");
			return "question/newquestion";
		}
		LOG.info("creating answers");
		List<Answer> answers = new ArrayList<Answer>();
		for (Answer answer : question.getAnswers()) {
			if ((answer != null) && (answer.getName().length() > 0)) {
				try {
					answerService.create(answer);
					answer = answerService.findByAnswer(answer);
					LOG.info("answer: " + answer.toString() + " ID="
							+ answer.getId());
					answers.add(answer);
					LOG.info("answer added");
				} catch (ElementExistsException e) {
					LOG.error("answer exists: " + answer);
				}
			}
		}
		question.setAnswers(answers);
		LOG.info("setting question from DB");
		try {
			question.setSubtopic(subtopicService.findByName(question
					.getSubtopic().getName()));
			LOG.info("question's subtopic: "
					+ question.getSubtopic().getName() + " "
					+ question.getSubtopic().getId());
			questionService.create(question);
		} catch (ElementExistsException e) {
			result.reject("question", "such question exists");
			for (Answer answer : question.getAnswers()) {
				answerService.delete(answer);
			}
			LOG.error("such question exists");
		}
		LOG.info("find question in DB");
		try {
			question = questionService.findByQuestion(question);
		} catch (NoResultException e) {
			result.reject("question", "such question not found in db");
			LOG.error("such question not found in db");
		}
		LOG.info("question: " + question);
		for (Answer answer : answers) {
			Answer tmpAnswer = answerService.findByAnswer(answer);
			tmpAnswer.setQuestion(question);
			try {
				answerService.update(tmpAnswer);
			} catch (ElementExistsException ex) {
				LOG.error("answer exists: " + tmpAnswer);
				result.rejectValue("name", "error.question",
						"this question already exists!");
			}

		}
		LOG.info("/addQuestion successful");
		return "redirect:/questions";
	}

	/**
	 * @param id
	 * @return questions
	 */
	@RequestMapping("/question/delete/{questionId}")
	public String deleteQuestion(@PathVariable("questionId") Long id) {
		Question question = questionService.findById(id);
		LOG.info("delete element " + question);
		questionService.delete(question);
		LOG.info("delete succesful");
		return "redirect:/questions";
	}

	/**
	 * @param map
	 * @return editquestion
	 */
	@RequestMapping(value = "/question/edit/{questionId}")
	public String editQuestionForm(@PathVariable("questionId") Long id,
			Map<String, Object> map) {
		LOG.info("/editquestion");
		Question editableQuestion = questionService.findById(id);
		map.put("question", questionService.findById(id));
		int i = 0;
		for (Answer answer : editableQuestion.getAnswers()) {
			map.put("correctAnswer" + i, answer.getIsCorrect());
			i++;
		}
		map.put("subtopicList", subtopicService.findAll());
		return "question/editquestion";
	}

	/**
	 * @param id
	 * @return questions
	 */
	@RequestMapping(value = "/question/edit/editQuestion", method = RequestMethod.POST)
	public String editQuestion(
	/* @Valid */@ModelAttribute("question") Question question,
			BindingResult result) {
		if (result.hasErrors()) {
			result.rejectValue("name", "error.question",
					"this question has errors!");
			return "redirect:/editquestion";
		}

		LOG.info("question id:" + question.getId());
		LOG.info("name= "+question.getName());
		LOG.info("sub="+question.getSubtopic().getName());
		try {
			Question editableQuestion = questionService.findById(question
					.getId());
			editableQuestion.setName(question.getName());
			editableQuestion.setSubtopic(subtopicService.findByName(question
					.getSubtopic().getName()));
			
			List<Answer> oldanswers=question.getAnswers();
			List<Answer> answers = new ArrayList<Answer>();
			for (Answer answer : editableQuestion.getAnswers()) {
				if (!oldanswers.contains(answer)) {
					answerService.delete(answerService.findByAnswer(answer));
				}else {
					answers.add(answerService.findByAnswer(answer));
				}
			}
			LOG.info("answers:"+answers);
			for (Answer answer : question.getAnswers()) {
				LOG.info("answer: "+answer);
				if ((answerService.findByAnswer(answer) == null)
						&& (answer.getName() != null)) {
					LOG.info("new answer = "+answer);
					answerService.create(answer);
				}
				answers.add(answerService.findByAnswer(answer));
			}
			LOG.info("answers:"+answers);
			editableQuestion.setAnswers(answers);
			questionService.update(editableQuestion);
		} catch (ElementExistsException e) {
			result.rejectValue("name", "error.question",
					"this question already exists!");
			return "redirect:/question/edit/editQuestion";
		}
		return "redirect:/questions";
	}

	/**
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping("/question/answers/{questionId}")
	public String answers(Map<String, Object> map,
			@PathVariable(value = "questionId") Long id) {
		LOG.info("/answers");
		Question question = questionService.findById(id);
		map.put("question", question);
		map.put("answerList", answerService.findByQuestion(question));
		return "question/questionInfo";
	}
}
