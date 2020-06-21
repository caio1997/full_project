package com.myhome.full_project.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError2{
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> list = new ArrayList<>();

	public ValidationError (String msg) {
		super(msg);
	}
	

	public List<FieldMessage> getErrors() {
		return list;
	}

	public void addError(String fieldName, String message) {
		list.add(new FieldMessage(fieldName, message));
	}
	
	
	
	
}
