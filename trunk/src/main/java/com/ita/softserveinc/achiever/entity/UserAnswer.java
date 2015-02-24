package com.ita.softserveinc.achiever.entity;

import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.common.base.Objects;

@Entity
@Table(name = "USER_ANSWERS")
public class UserAnswer extends BaseEntity {

	private static final long serialVersionUID = -7365493536870095334L;

	@ManyToOne(optional = false)
	@JoinColumn(name = "QUESTION_ID")
	private Question question;

	@Column(name = "ANSWER_GRADE")
	private Double answerGrade;

	@ManyToMany
	@JoinTable(name = "USER_ANSWERS_to_ASNWERS", joinColumns = { @JoinColumn(name = "USER_ANSWER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ANSWER_ID") })
	private List<Answer> answers;

	@ManyToOne(optional = false)
	@JoinColumn(name = "QUIZ_RESULT_ID")
	private QuizResult quizResult;

	public UserAnswer() {
		super();
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<Answer> getAnswers() {
		return Collections.unmodifiableList(this.answers);
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public void addAnswer(Answer answer) {
		this.answers.add(answer);
	}

	public Double getAnswerGrade() {
		return answerGrade;
	}

	public void setAnswerGrade(Double answerGrade) {
		this.answerGrade = answerGrade;
	}

	public QuizResult getQuizResult() {
		return quizResult;
	}

	public void setQuizResult(QuizResult quizResult) {
		this.quizResult = quizResult;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(question,/* answers, */answerGrade, quizResult);
	}

	@Override
	public boolean equals(Object object) {

		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (getClass() != object.getClass()) {
			return false;
		}

		UserAnswer that = (UserAnswer) object;
		return Objects.equal(this.question, that.question)
		/* && Objects.equal(this.answers, that.answers) */
		&& Objects.equal(this.answerGrade, that.answerGrade)
				&& Objects.equal(this.quizResult, that.quizResult);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("question", question.getName())
				./* add("answers", answers). */add("answerGrade", answerGrade)
				.add("quizResult", quizResult.getName()).toString();
	}
}
