package com.blog.peoples.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.peoples.entity.PeoplesUser;
import com.blog.peoples.entity.UserCategory;
import com.blog.peoples.entity.UserPost;

@Repository
public interface UserPostRepo extends JpaRepository<UserPost, Long> {

	List<UserPost> findByUser(PeoplesUser user);
	
	List<UserPost> findByCategory(UserCategory category);

	UserPost findByPostId(Long postId);
}
