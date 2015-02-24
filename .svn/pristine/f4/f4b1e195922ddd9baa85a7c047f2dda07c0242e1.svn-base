package com.ita.softserveinc.achiever.exception;

public class UserDataPatternException extends Exception {

	private static final long serialVersionUID = 1L;

	private String parametr;
	
	public UserDataPatternException(String parametr) {
		super("User " + parametr + "does not match the pattern");
		this.parametr = parametr;
	}

	public String getParametr() {
		return parametr;
	}
}
