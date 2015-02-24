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
 * 
 * @author Alim Ismailov
 * 
 */
@NamedQueries (value= {
		@NamedQuery(name = "Subtopic.findByName", query = "SELECT s FROM Subtopic s WHERE s.name = :name"),
		@NamedQuery(name = "Subtopic.findByTopic", query = "SELECT s FROM Subtopic s WHERE s.topic = :topic"),
})
@Entity
@Table(name = "SUBTOPICS")
public class Subtopic extends BaseEntity {

	private static final long serialVersionUID = -862201817349244293L;

	@Size(min = 1, max = 50)
	@Column(name = "NAME", nullable = false, updatable = true)
	private String name;

	@OneToMany(mappedBy = "subtopic", orphanRemoval = true)
	private List<Question> questions;

	@ManyToOne(optional = false)
	@JoinColumn(name = "TOPIC_ID")
	private Topic topic;
	
	@OneToMany(mappedBy = "subtopic")
	private List<Article> articles;

	/**
	 * 
	 */
	public Subtopic() {
		super();
	}

	/**
	 * @param subtopicName
	 */
	public Subtopic(String subtopicName) {
		super();
		this.name = subtopicName;
	}

	/**
	 * 
	 * @param subtopicName
	 * @param topic
	 */
	public Subtopic(String subtopicName, Topic topic) {
		super();
		this.name = subtopicName;
		this.topic = topic;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the questions
	 */
	public List<Question> getQuestions() {
		return questions;
	}

	/**
	 * @param questions
	 *            the questions to set
	 */
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	/**
	 * @return the topic
	 */
	public Topic getTopic() {
		return topic;
	}

	/**
	 * @param topic
	 *            the topic to set
	 */
	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	/**
	 * @param question
	 */
	public void addQuestion(Question question) {
		this.questions.add(question);
	}
	
	public void addArticle(Article article) {
		articles.add(article);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.name, this.topic);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Subtopic)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		Subtopic that = (Subtopic) obj;
		return Objects.equal(this.name, that.name)
				&& Objects.equal(this.topic, that.topic);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("Name", this.name).toString();
	}

}
