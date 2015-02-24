package com.ita.softserveinc.achiever.api;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@XmlRootElement
public class UpdateUserRequest {

	@NotEmpty(message = "{label.required.firstName}")
	private String firstName;

	@NotEmpty(message = "{label.required.lastName}")
	private String lastName;

	public UpdateUserRequest() {

	}

	public UpdateUserRequest(final String firstName, final String lastName) {

		this.firstName = firstName;
		this.lastName = lastName;

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
