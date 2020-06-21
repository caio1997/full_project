package com.myhome.full_project.controllers.exceptions;

import java.io.Serializable;

public class StandardError2 implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String msg;

	
	public StandardError2() {
	}

	public StandardError2( String msg) {
		super();
	
	
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}