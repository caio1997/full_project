package com.myhome.full_project.services.exceptions;

public class DataIntegrityException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityException(String msg) {
		super(msg);
	}

}
