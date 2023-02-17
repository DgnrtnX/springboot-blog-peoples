package com.blog.peoples.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class UserModel {

	@NotEmpty()
	@Size(min = 4, message = "Name must be minimum of 4 characters")
	private String name;

	@Email
	@NotEmpty(message = "Email is invalid")
	private String email;

	@NotEmpty
	@Size(min = 8, max = 16, message = "Provide length between 8 and 16")
	private String password;

	private String about;
}
