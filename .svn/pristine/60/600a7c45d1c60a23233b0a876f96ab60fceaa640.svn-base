package com.ita.softserveinc.achiever.entity;

import java.util.Collections;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.google.common.base.Objects;

/**
 * 
 * @author Ostap Palianytsia
 * 
 */
@Entity
@Table(name = "ROLES")
@NamedQuery(name = "Role.findByName", query = "SELECT r FROM Role r WHERE r.type = :type")
public class Role extends BaseEntity {

	private static final long serialVersionUID = 300910595777222806L;

	@Column(name = "NAME", unique = true, nullable = false)
	private String type;

	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	private Set<User> users;

	public Role() {
		super();
	}

	/**
	 * @param name
	 */
	public Role(String name) {
		super();
		this.type = name;
	}

	/**
	 * @return the name
	 */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the users
	 */
	public Set<User> getUsers() {
		return Collections.unmodifiableSet(users);
	}

	/**
	 * @param users
	 *            the users to set
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.type);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Role other = (Role) obj;
		return Objects.equal(this.type, other.type);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("type", this.type).toString();
	}

}
