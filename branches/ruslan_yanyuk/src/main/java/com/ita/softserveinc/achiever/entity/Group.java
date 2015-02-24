package com.ita.softserveinc.achiever.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.google.common.base.Objects;

/**
 * @author Ruslan Didyk
 */
@NamedQueries({
		@NamedQuery(name = "Group.findByName", query = "SELECT g FROM Group g WHERE g.groupName = :name"),
		@NamedQuery(name = "Group.findUnique", query = "SELECT g FROM Group g JOIN g.direction d WHERE g.groupName=:groupName AND g.direction=:direction"),
		@NamedQuery(name = "Group.findByDirection", query = "SELECT g FROM Group g JOIN g.direction d WHERE g.direction = :direction"),
		@NamedQuery(name = "Group.findByUser", query = "SELECT g FROM Group g JOIN g.users u WHERE u.login = :login"),
		@NamedQuery(name = "Group.findStartAfterDate", query = "SELECT g FROM Group g WHERE g.start > :start"),
		@NamedQuery(name = "Group.findEndBeforeDate", query = "SELECT g FROM Group g WHERE g.end < :end"),
		@NamedQuery(name = "Group.findStartAfterDateByUser", query = "SELECT g FROM Group g JOIN g.users u WHERE g.start > :start AND u.login = :login"),
		@NamedQuery(name = "Group.findEndBeforeDateByUser", query = "SELECT g FROM Group g JOIN g.users u WHERE g.end < :end AND u.login = :login"),
		@NamedQuery(name = "Group.findCurrent", query = "SELECT g FROM Group g WHERE g.start <= :start AND g.end >= :end"),
		@NamedQuery(name = "Group.findCurrentByUser", query = "SELECT g FROM Group g JOIN g.users u WHERE g.start <= :start AND g.end >= :end AND u.login = :login"),
		@NamedQuery(name = "Group.findByStartDate", query = "SELECT g FROM Group g WHERE g.start = :start"),
		@NamedQuery(name = "Group.findByEndDate", query = "SELECT g FROM Group g WHERE g.end = :end"), })
@Entity
@Table(name = "GROUPS")
public class Group extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "NAME", nullable = false, unique = true)
	@Size(min = 1, max = 10, message = "Must be from 1 to 10 chars")
	private String groupName;

	@Column(name = "STARTS")
	private Date start;

	@Column(name = "ENDS")
	private Date end;

	@ManyToMany
	@JoinTable(name = "USERS_to_GROUPS", joinColumns = { @JoinColumn(name = "GROUP_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	private Set<User> users = new HashSet<User>();

	@ManyToOne
	@JoinColumn(name = "DIRECTION_ID")
	private Direction direction;

	@OneToMany(mappedBy = "group", orphanRemoval = true)
	private Set<Application> applications;

	// bi-directional many-to-one association to Event
	@OneToMany(mappedBy = "group")
	private List<Event> events;

	public Group() {
	}

	public Group(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	/**
	 * @param user
	 *            (type = User) set new user in Group
	 */
	public void addUser(User user) {
		this.users.add(user);
	}

	/**
	 * @param manyUsers
	 *            (type = Set<User>) set many Users in Group
	 */
	public void addUsers(Collection<User> manyUsers) {
		this.users.addAll(manyUsers);
	}

	/**
	 * @param user
	 *            (type = User) delete user from Group
	 */
	public void deleteUser(User user) {
		this.users.remove(user);
	}

	/**
	 * @param manyUsers
	 *            (type = Set<User>) delete some users from Group
	 */
	public void deleteUsers(Set<User> manyUsers) {
		this.users.removeAll(manyUsers);
	}

	/**
	 * delete All users from Group
	 */
	public void deleteAllUsers() {
		this.users.clear();
	}

	/**
	 * @return direction (type = Direction) Group of direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * @param direction
	 *            (type = Direction) set direction in Group
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Set<User> getUsers() {
		return  Collections.unmodifiableSet (users);
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	/**
	 * @return result (type - int) hashCode of Group.class
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(this.groupName, this.direction);
	}

	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Event addEvent(Event event) {
		getEvents().add(event);
		event.setGroup(this);

		return event;
	}

	public Event removeEvent(Event event) {
		getEvents().remove(event);
		event.setGroup(null);

		return event;
	}

	/**
	 * @param obj
	 *            (type = Object) object which we want check for equality
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Group that = (Group) obj;
		return Objects.equal(this.groupName, that.groupName)
				&& Objects.equal(this.direction, that.direction);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("groupName", this.groupName)
				.add("direction", this.direction.getName())
				.add("users", this.users).toString();
	}

}
