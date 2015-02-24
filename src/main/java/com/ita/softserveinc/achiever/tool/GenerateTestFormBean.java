package com.ita.softserveinc.achiever.tool;

import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class GenerateTestFormBean {

	@Size(min = 3, message = "Name must be at least 3 characters!")
	private String quizName;

	@Size(min = 1, message = "Select a group!")
	private String groupName;

	private String topicName;

	private String subtopicName;

	private int countOfQuestions;

	private boolean review;

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

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
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

	public boolean isReview() {
		return review;
	}

	public void setReview(boolean review) {
		this.review = review;
	}

}
