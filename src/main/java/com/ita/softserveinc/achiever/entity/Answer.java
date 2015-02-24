package com.ita.softserveinc.achiever.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.google.common.base.Objects;

/**
 * @author Ruslan Didyk
 */
@Entity
@Table(name = "ANSWERS")
@NamedQueries(value = {
		@NamedQuery(name = "Answer.findByQuestion", query = "SELECT a FROM Answer a WHERE a.question = :question"),
		@NamedQuery(name = "Answer.findByAnswer", query = "SELECT a FROM Answer a WHERE a.name = :name AND a.isCorrect = :correct AND a.question = :question") })
public class Answer extends BaseEntity {

	private static final long serialVersionUID = 171077558751171399L;

	@Size(min = 0, max = 250)
	@Column(name = "NAME")
	private String name;

	@Column(name = "IS_CORRECT")
	private boolean isCorrect;

	@ManyToOne
	@JoinColumn(name = "QUESTION_ID")
	private Question question;

	@ManyToMany(mappedBy = "answers")
	private List<UserAnswer> userAnswers;

	public Answer() {
	}

	public Answer(String string, boolean correct) {
		this.name = string;
		this.isCorrect = correct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<UserAnswer> getUserAnswers() {
		return userAnswers;
	}

	public void setUserAnswers(List<UserAnswer> userAnswers) {
		this.userAnswers = userAnswers;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.name, this.question);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Answer that = (Answer) obj;
		return Objects.equal(this.name, that.name)
				&& Objects.equal(this.question, that.question);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("name", this.name)
				.add("correct", this.isCorrect).toString();
	}
}
