package com.buildbot.contactsmanagement.helper;

public interface UserConstants {


		String USERNAME_MESSAGE = "username cannot contain spaces in the middle";
		String PASSWORD_MESSAGE = "Your password must be at least 5 characters long"
				+ " contain at least one uppercase letter" + "one lowercase letter"
				+ "one digit, and one special character ";

		String USERNAME_REGEX = "^\\s*\\S+\\s*$";
		String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)[a-zA-Z\\d\\W]{5,}$";


}
