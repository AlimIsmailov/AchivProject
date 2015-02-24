package com.ita.softserveinc.achiever.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.google.common.base.Objects;

/**
 * The persistent class for the locations database table.
 * 
 */
@Entity
@Table(name = "LOCATIONS")
@NamedQueries({
		@NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l"),
		@NamedQuery(name = "Location.findByName", query = "SELECT l FROM Location l WHERE l.name = :name") })
public class Location extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3756269681413016808L;

	@Column(name = "NAME")
	private String name;

	// bi-directional one-to-one association to Event
	@OneToOne(mappedBy = "location")
	@PrimaryKeyJoinColumn
	private Event event;
	public Location() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

}