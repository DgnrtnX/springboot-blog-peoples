package com.blog.peoples.model;

import java.sql.Timestamp;

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
	
	private String title;

	private String content;
	
	private Timestamp createdDate;
	
	private UserModel user;
	
	private CategoryModel category;
}
