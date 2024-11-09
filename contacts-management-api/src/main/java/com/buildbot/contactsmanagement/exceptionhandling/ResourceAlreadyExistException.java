package com.buildbot.contactsmanagement.exceptionhandling;

public class ResourceAlreadyExistException extends RuntimeException {

	public ResourceAlreadyExistException(String message) {
		super(message);
	}

}
