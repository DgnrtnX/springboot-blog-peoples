package com.blog.peoples.exception;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 5953709788414979947L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
