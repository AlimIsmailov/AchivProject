package com.ita.softserveinc.achiever.exception;

public class UserDataVerificationException extends UserException {

	private static final long serialVersionUID = 1L;

	private String parametr;
	
	public UserDataVerificationException(String parametr) {
		super("User " + parametr + "does not match the pattern");
		this.parametr = parametr;
	}

	public String getParameter() {
		return parametr;
	}
}
