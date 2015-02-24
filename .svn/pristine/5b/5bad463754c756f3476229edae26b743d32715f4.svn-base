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

import com.google.common.base.Objects;
/**
 * @author Ruslan Didyk
 */
@Entity
@Table(name = "QUIZ_RESULT")
@NamedQueries({ @NamedQuery(name = "QuizResult.getByUser", query = "SELECT q FROM QuizResult q WHERE q.user = :user") })
public class QuizResult extends BaseEntity {

	private static final long serialVersionUID = -5976870993381620278L;

	@Column(name = "QUIZ_NAME", nullable = false)
	private String name;

	@ManyToOne(optional = false)
	@JoinColumn(name = "USER_ID", nullable = false)
	private User user;

	@Column(name = "passed")
	private boolean passed;

	@Column(name = "TOTAL_GRADE")
	private Double totalGrade;

	@OneToMany(mappedBy = "quizResult", orphanRemoval = true)
	private List<UserAnswer> userAnswers;

	public QuizResult() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public Double getTotalGrade() {
		return totalGrade;
	}

	public void setTotalGrade(Double totalGrade) {
		this.totalGrade = totalGrade;
	}

	public List<UserAnswer> getUserAnswers() {
		return userAnswers;
	}

	public void setUserAnswers(List<UserAnswer> userAnswers) {
		this.userAnswers = userAnswers;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("name", name)
				.add("user", user.getId()).add("totalGrade", totalGrade)
				.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(name, user, totalGrade, userAnswers);
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
		QuizResult that = (QuizResult) object;
		return Objects.equal(this.name, that.name)
				&& Objects.equal(this.user, that.user)
				&& Objects.equal(this.totalGrade, that.totalGrade)
				&& Objects.equal(this.userAnswers, that.userAnswers);

	}

}
