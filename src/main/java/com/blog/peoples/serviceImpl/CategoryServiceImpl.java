package com.blog.peoples.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.peoples.entity.UserCategory;
import com.blog.peoples.exception.DataNotFoundException;
import com.blog.peoples.model.CategoryModel;
import com.blog.peoples.repository.UserCategoryRepo;
import com.blog.peoples.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private UserCategoryRepo categoryRepo;

	@Override
	public CategoryModel createCategory(CategoryModel categoryModel) {
		log.info("inside createCategory");

		UserCategory category = mapCategoryModelToCategory(categoryModel);

		categoryRepo.save(category);

		categoryModel = mapCategoryToCategoryModel(category);

		return categoryModel;
	}

	@Override
	public CategoryModel updateCategory(CategoryModel categoryModel, Long categoryId) throws DataNotFoundException {
		log.info("inside updateCategory");
		UserCategory category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new DataNotFoundException("Category id: " + categoryId + " not found"));

		log.info("Category id found: {}", categoryId);

		category = mapCategoryModelToCategory(categoryModel, categoryId);

		categoryRepo.save(category);
		log.info("Category id updated: {}", categoryId);

		return mapCategoryToCategoryModel(category);
	}

	@Override
	public CategoryModel getCategory(Long categoryId) throws DataNotFoundException {
		log.info("inside getCategory");
		UserCategory category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new DataNotFoundException("Category id: " + categoryId + " not found"));

		log.info("Category id found: {}", categoryId);
		return mapCategoryToCategoryModel(category);
	}

	@Override
	public List<CategoryModel> getAllCategory() {
		log.info("inside getAllCategory");
		List<UserCategory> categories = categoryRepo.findAll();

		return categories.stream().map(this::mapCategoryToCategoryModel).toList();
	}

	@Override
	public String deleteCategory(Long categoryId) throws DataNotFoundException {
		log.info("inside deleteCategory");
		UserCategory category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new DataNotFoundException("Category id: " + categoryId + " not found"));

		log.info("Category id found: {}", categoryId);
		categoryRepo.delete(category);

		return "Data deleted for category id: " + categoryId;
	}

	/***************************************************************************************************/

	protected CategoryModel mapCategoryToCategoryModel(UserCategory category) {

		return new CategoryModel().setCategoryTitle(category.getCategoryTitle())
				.setCategoryDesc(category.getCategoryDesc());
	}

	protected UserCategory mapCategoryModelToCategory(CategoryModel category) {

		return new UserCategory().setCategoryTitle(category.getCategoryTitle())
				.setCategoryDesc(category.getCategoryDesc());
	}

	protected UserCategory mapCategoryModelToCategory(CategoryModel category, Long categoryId) {
		return new UserCategory().setCategoryId(categoryId).setCategoryTitle(category.getCategoryTitle())
				.setCategoryDesc(category.getCategoryDesc());
	}
}
