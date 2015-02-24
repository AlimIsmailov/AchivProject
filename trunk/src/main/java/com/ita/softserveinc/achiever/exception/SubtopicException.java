package com.ita.softserveinc.achiever.exception;

public class SubtopicException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public SubtopicException() {
		super("Subtopic exception");
	}

	/**
	 * @param arg0
	 */
	public SubtopicException(String arg0) {
		super("Subtopic exception: "+arg0);
	}

}
