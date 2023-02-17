package com.blog.peoples.model;

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
public class CategoryModel {

	@Size(min = 4, max = 50,message="Enter title between size between 4 and 50")
	private String categoryTitle;

	@Size(min = 4,max=250,message="Enter description between size 4 and 250")
	private String categoryDesc;
	
//	private List<PostModel> post;
}
