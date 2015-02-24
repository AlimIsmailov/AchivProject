package com.ita.softserveinc.achiever.exception;

public class UserExistException extends UserException {

	private static final long serialVersionUID = 1L;

	private String parameter;

	public UserExistException(String parameter) {
		super("User with such login: " + parameter + " already exists");
		this.parameter = parameter;
	}

	public String getParameter() {
		return parameter;
	}

}
