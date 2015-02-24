package com.ita.softserveinc.achiever.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "EVENTS")
@NamedQueries({
		@NamedQuery(name = "Event.findAll", query = "SELECT e FROM Event e ORDER BY e.startDatetime"),
		@NamedQuery(name = "Event.findByUserBetweenDates", query = "SELECT e FROM Event e JOIN e.group g JOIN g.users u WHERE u.login=:login AND e.startDatetime BETWEEN :startDate AND :endDate GROUP BY e.startDatetime"),
		@NamedQuery(name = "Event.findByGroupBetweenDates", query = "SELECT e FROM Event e WHERE e.group.id=:group AND e.startDatetime BETWEEN :startDate AND :endDate GROUP BY e.startDatetime"),
		@NamedQuery(name = "Event.findByLocationBetweenDates", query = "SELECT e FROM Event e WHERE e.location.id=:location AND e.startDatetime BETWEEN :startDate AND :endDate GROUP BY e.startDatetime"),
		@NamedQuery(name = "Event.findByUser", query = "SELECT e FROM Event e JOIN e.group g JOIN g.users u WHERE u.login=:login"),
		@NamedQuery(name = "Event.findByGroup", query = "SELECT e FROM Event e WHERE e.group.id=:group"),
		@NamedQuery(name = "Event.findByLocation", query = "SELECT e FROM Event e WHERE e.location.id=:location"),
		@NamedQuery(name = "Event.existValidation", query = "SELECT e FROM Event e WHERE e.startDatetime = :startDatetime AND e.endDatetime = :endDatetime AND e.location = :location") 
})
public class Event extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7749618412570568824L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_DATETIME")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime endDatetime;

	@Temporal(TemporalType.TIMESTAMP)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(name = "START_DATETIME")
	private DateTime startDatetime;

	// bi-directional many-to-one association to Group
	@ManyToOne
	@JoinColumn(name = "GROUPS_ID")
	private Group group;

	@OneToOne
	@JoinColumn(name = "LOCATION_ID")
	private Location location;

	public Event() {
	}

	public DateTime getEndDatetime() {
		return endDatetime;
	}

	public void setEndDatetime(DateTime endDatetime) {
		this.endDatetime = endDatetime;
	}

	public DateTime getStartDatetime() {
		return startDatetime;
	}

	public void setStartDatetime(DateTime startDatetime) {
		this.startDatetime = startDatetime;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((endDatetime == null) ? 0 : endDatetime.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result
				+ ((startDatetime == null) ? 0 : startDatetime.hashCode());
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
		Event other = (Event) obj;
		if (endDatetime == null) {
			if (other.endDatetime != null)
				return false;
		} else if (!endDatetime.equals(other.endDatetime))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (startDatetime == null) {
			if (other.startDatetime != null)
				return false;
		} else if (!startDatetime.equals(other.startDatetime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Event [endDatetime=" + endDatetime + ", startDatetime="
				+ startDatetime + ", group=" + group + ", location="
						+ location + "]";
	}

}