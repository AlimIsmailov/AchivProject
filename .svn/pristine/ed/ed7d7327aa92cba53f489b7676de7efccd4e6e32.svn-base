package com.ita.softserveinc.achiever.tool;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.User;

public class UserFormModel implements Serializable {

	private static final long serialVersionUID = 4715901551608963193L;

	private final String login;
	private final String firstName;
	private final String lastName;
	private final String email;
	private final String role;
	private final Date created;
	private List<Group> activeGroups;

	public UserFormModel(User user, List<Group> activeGroups) {

		this.login = user.getLogin();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.role = user.getMainRole();
		this.created = user.getCreated();
		this.activeGroups = activeGroups;
	}

	public String getLogin() {
		return login;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getRole() {
		return role;
	}

	public Date getCreated() {
		return created;
	}

	public List<Group> getActiveGroups() {
		return activeGroups;
	}

	public void setActiveGroups(List<Group> activeGroups) {
		this.activeGroups = activeGroups;
	}
}
