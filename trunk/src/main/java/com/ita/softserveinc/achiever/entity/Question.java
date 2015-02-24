package com.ita.softserveinc.achiever.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.google.common.base.Objects;

/**
 * @author Taras Hrytsko
 */
@NamedQueries({
		@NamedQuery(name = "Question.findBySubtopic", query = "SELECT q FROM Question q WHERE q.subtopic = :subtopic"),
		@NamedQuery(name = "Question.findByQuestion", query = "SELECT q FROM Question q WHERE q = :question"),
		@NamedQuery(name = "Question.findByName", query = "SELECT q FROM Question q WHERE q.name = :name") })
@Entity
@Table(name = "QUESTIONS")
public class Question extends BaseEntity {

	private static final long serialVersionUID = -5801723710274520852L;

	/**
	 * question body
	 */
	@Size(min = 1, max = 250)
	@Column(name = "NAME", nullable = false, updatable = true)
	private String name;

	@OneToMany(mappedBy = "question", orphanRemoval = true)
	private List<Answer> answers;
	
	@OneToMany(mappedBy = "question", orphanRemoval = true)
	private List<UserAnswer> userAnswers;

	@ManyToOne
	@JoinColumn(name = "SUBTOPIC_ID")
	private Subtopic subtopic;

	public Question() {
	}

	/**
	 * 
	 * @param question
	 * @param correctAnswer
	 * @param subtopic
	 */
	public Question(String name, String correctAnswer, Subtopic subtopic) {
		super();
		this.name = name;
		this.subtopic = subtopic;
	}

	public List<UserAnswer> getUserAnswers() {
		return userAnswers;
	}

	public void setUserAnswers(List<UserAnswer> userAnswers) {
		this.userAnswers = userAnswers;
	}

	/**
	 * @return the question
	 */
	public String getName() {
		return name;
	}
 
	/**
	 * @param question
	 *            the question to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the subtopic
	 */
	public Subtopic getSubtopic() {
		return subtopic;
	}

	/**
	 * @return the correctAnswer
	 */

	public void setSubtopic(Subtopic subtopic) {
		this.subtopic = subtopic;
	}


	/**
	 * @return the answers
	 */
	public List<Answer> getAnswers() {
		return answers;
	}

	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	/**
	 * 
	 * @param answer
	 */
	public void addAnswer(Answer answer) {
		this.answers.add(answer);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.subtopic, this.name);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Question)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		Question that = (Question) obj;
		return Objects.equal(this.subtopic, that.subtopic)
				&& Objects.equal(this.name, that.name);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("id", this.getId())
				.add("name", this.name).toString();
	}
}
