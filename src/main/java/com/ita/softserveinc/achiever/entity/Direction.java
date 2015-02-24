package com.ita.softserveinc.achiever.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.google.common.base.Objects;

/**
 * 
 * @author Andriana
 *
 */
@Entity
@Table(name = "DIRECTIONS")
@NamedQuery(name = "Direction.findByName", query = "SELECT d FROM Direction d WHERE d.name = :name")
public class Direction extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -862201817349244293L;

	/**
	 * 
	 */
	@Column(name = "NAME", unique = true, nullable = false)
	@Size(min = 1)
	private String name;

	/**
	 * 
	 */
	@OneToMany(mappedBy = "direction", orphanRemoval = true)
	private List<Group> groups;;

	/**
	 * 
	 */
	@ManyToMany(mappedBy = "directions", fetch = FetchType.LAZY)
	private List<Topic> topics;;

	/**
	 * 
	 */
	public Direction() {
		super();
	}

	/**
	 * @param directionName
	 */
	public Direction(String directionName) {
		super();
		this.name = directionName;
	}

	/**
	 * @return the directionName
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets direction name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the groups
	 */
	public List<Group> getGroups() {
		return groups;
	}

	/**
	 * @return the topics
	 */
	public List<Topic> getTopics() {
		return topics;
	}

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(this.name);
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Direction)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		Direction that = (Direction) obj;
		return Objects.equal(this.name, that.name);
	}

	@Override
	public String toString() {
		return name;
	}

	/* 
	 * 
	 */
	

}
