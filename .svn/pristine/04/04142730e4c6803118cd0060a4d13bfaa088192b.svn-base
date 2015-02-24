package com.ita.softserveinc.achiever.tool;

import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class GenerateTestFormBean {

	@Size(min = 3, message = "Name must be at least 3 characters!")
	private String quizName;

	private String groupName;

	private String subtopicName;

	private int countOfQuestions;

	public GenerateTestFormBean() {
	}

	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getSubtopicName() {
		return subtopicName;
	}

	public void setSubtopicName(String subtopicName) {
		this.subtopicName = subtopicName;
	}

	public int getCountOfQuestions() {
		return countOfQuestions;
	}

	public void setCountOfQuestions(int countOfQuestions) {
		this.countOfQuestions = countOfQuestions;
	}

}
