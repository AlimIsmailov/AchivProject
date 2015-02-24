package com.ita.softserveinc.achiever.exception;

/**
 * 
 * @author Ostap Palianytsia
 * 
 */
public class UploadFileOnServerException extends Exception {
	
	private static final long serialVersionUID = 5663505904991830298L;

	public UploadFileOnServerException(String message) {
		super(message);
	}
	
	public UploadFileOnServerException() {
		super("Uploading file on server failed!!");
	}
}
