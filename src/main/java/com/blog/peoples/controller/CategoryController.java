package com.blog.peoples.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.peoples.exception.DataNotFoundException;
import com.blog.peoples.model.CategoryModel;
import com.blog.peoples.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	
	@PostMapping("/create")
	public ResponseEntity<CategoryModel> createUser(@Valid @RequestBody CategoryModel categoryModel) {
		return new ResponseEntity<>(categoryService.createCategory(categoryModel), HttpStatus.CREATED);
	}

	@PostMapping("/update")
	public ResponseEntity<CategoryModel> updateUser(@Valid @RequestBody CategoryModel categoryModel,
			@RequestParam("id") Long categoryId) throws DataNotFoundException {
		return ResponseEntity.ok(categoryService.updateCategory(categoryModel, categoryId));
	}

	@GetMapping("/get")
	public ResponseEntity<CategoryModel> getById(@RequestParam("id") Long categoryId)
			throws DataNotFoundException {
		return ResponseEntity.ok(categoryService.getCategory(categoryId));
	}

	@GetMapping("/all")
	public ResponseEntity<List<CategoryModel>> getAllUser() {
		return ResponseEntity.ok(categoryService.getAllCategory());
	}

	@GetMapping("/delete")
	public ResponseEntity<String> deleteUser(@RequestParam("id") Long categoryId) throws DataNotFoundException {
		return ResponseEntity.ok(categoryService.deleteCategory(categoryId));
	}

}
