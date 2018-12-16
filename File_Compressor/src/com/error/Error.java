package com.error;

public class Error extends Exception {

	public Error(String msg)
	{
		super(msg);
	}
	
	@Override
	public String getMessage() {
		return this.getMessage();
	}
	
}
