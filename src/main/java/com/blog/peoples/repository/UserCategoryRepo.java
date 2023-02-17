package com.blog.peoples.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.peoples.entity.UserCategory;

@Repository
public interface UserCategoryRepo extends JpaRepository<UserCategory, Long> {

	UserCategory findByCategoryId(Long categoryId);

}
