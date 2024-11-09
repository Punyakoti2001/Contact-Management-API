package com.buildbot.contactsmanagement.exceptionhandling;

public class ResourceNotFoundException extends RuntimeException 
{

	public ResourceNotFoundException(String Resource,String fieldName,Object value) {
		super("This "+Resource+" does not Exist with "+fieldName +" : "+ value);
	}

	 
	

}