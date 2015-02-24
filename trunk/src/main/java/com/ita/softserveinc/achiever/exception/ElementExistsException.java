package com.ita.softserveinc.achiever.exception;

public class ElementExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1741704027648881942L;
	
	public ElementExistsException(){
		super("This element already exists!");
	}
	
	public ElementExistsException(String message){
		super(message+" already exists!");
	}

}
