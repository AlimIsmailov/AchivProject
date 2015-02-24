package com.ita.softserveinc.achiever.api;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@XmlRootElement
public class EmailVerificationRequest {

	@NotEmpty
	@Email
	private String email;

	public EmailVerificationRequest() {

	}

	public EmailVerificationRequest(final String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
