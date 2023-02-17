package com.blog.peoples.service;

import java.util.List;

import com.blog.peoples.exception.DataNotFoundException;
import com.blog.peoples.model.CategoryModel;

public interface CategoryService {

	CategoryModel createCategory(CategoryModel categoryModel);
	
	CategoryModel updateCategory(CategoryModel categoryModel,Long categoryId) throws DataNotFoundException;
	
	CategoryModel getCategory(Long categoryId) throws DataNotFoundException;
	
	List<CategoryModel> getAllCategory();
	
	String deleteCategory(Long categoryId) throws DataNotFoundException;
}
