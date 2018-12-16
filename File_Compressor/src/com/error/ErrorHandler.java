package com.error;
import com.error.Error;

public class ErrorHandler {

	public static void throwError(Error error)
	{
		System.out.println(error.getMessage());
	}
	
}
