package com.ita.softserveinc.achiever.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.google.common.base.Objects;

/**
 *
 */
@Entity
@Table(name = "TOPICS")
@NamedQueries({
		@NamedQuery(name = "Topic.findByDirectionName", query = "SELECT t FROM Topic t JOIN t.directions d WHERE d.name = :name"),
		@NamedQuery(name = "Topic.findByDirection", query = "SELECT t FROM Topic t JOIN t.directions d WHERE d = :direction"),
		@NamedQuery(name = "Topic.findByName", query = "SELECT t FROM Topic t WHERE t.topicName = :name"),
})
public class Topic extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 804553713833686970L;

	@Column(name = "NAME", unique = true, nullable = false)
	@Size(min = 1, max = 50)
	private String topicName;

	@OneToMany(mappedBy = "topic", orphanRemoval = true)
	private List<Subtopic> subtopics;

	@ManyToMany
	@JoinTable(name = "DIRECTIONS_to_TOPICS", joinColumns = { @JoinColumn(name = "TOPIC_ID") }, inverseJoinColumns = { @JoinColumn(name = "DIRECTION_ID") })
	private List<Direction> directions;

	/**
	 * 
	 */
	public Topic() {

	}

	/**
	 * @param topicName
	 */
	public Topic(String topicName) {
		super();
		this.topicName = topicName;
	}

	/**
	 * 
	 * @param topicName
	 * @param directions
	 */
	public Topic(String topicName, List<Direction> directions) {
		super();
		this.topicName = topicName;
		this.directions = directions;
	}

	/**
	 * @param subtopics
	 *            the subtopics to set
	 */
	public void addSubtopics(Set<Subtopic> subtopics) {
		this.subtopics.addAll(subtopics);
	}

	/**
	 * @param subtopic
	 *            the subtopic to set
	 */
	public void addSubtopic(Subtopic subtopic) {
		this.subtopics.add(subtopic);
	}

	/**
	 * @param direction
	 */
	public void addDirection(Direction direction) {
		this.directions.add(direction);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.topicName);
	}

	/**
	 * @return the topicName
	 */
	public String getTopicName() {
		return topicName;
	}

	/**
	 * @param topicName
	 *            the topicName to set
	 */
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	/**
	 * @return the subtopics
	 */
	public List<Subtopic> getSubtopics() {
		return subtopics;
	}

	/**
	 * @param subtopics
	 *            the subtopics to set
	 */
	public void setSubtopics(List<Subtopic> subtopics) {
		this.subtopics = subtopics;
	}

	/**
	 * @return the directions
	 */
	public List<Direction> getDirections() {
		return directions;
	}

	/**
	 * @param directions
	 *            the directions to set
	 */
	public void setDirections(List<Direction> directions) {
		this.directions = directions;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Topic other = (Topic) obj;
		return Objects.equal(this.topicName, other.topicName);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("name", this.topicName)
				.add("direction", this.directions).toString();
	}
}
