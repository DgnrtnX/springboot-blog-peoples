package com.blog.peoples.model;

import java.sql.Timestamp;

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
public class PostModel {
	
	@Size(min = 4,message = "Title must have a minimun size of 4 characters")
	private String title;

	@Size(min = 4,message = "Content must have a minimun size of 4 characters")
	private String content;
	
	private Timestamp createdDate;
	
	private UserModel user;
	
	private CategoryModel category;
}
