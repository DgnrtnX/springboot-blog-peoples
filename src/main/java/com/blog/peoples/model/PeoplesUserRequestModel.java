package com.blog.peoples.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "build")
public class PeoplesUserRequestModel {
	
	@NotEmpty(message = "name should not be empty")
	private String name;
	
	@Email
	@NotEmpty(message = "email should not be empty")
	private String email;
	
	@NotEmpty(message = "password should not be empty")
	@Size(min = 8, max = 16,message = "Provide length between 8 and 16")
	private String password;
	
	private String about;
}
