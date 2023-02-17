package com.blog.peoples.service;

import java.util.List;

import com.blog.peoples.exception.DataNotFoundException;
import com.blog.peoples.model.PostModel;

public interface PostService {

	PostModel createPost(PostModel postModel, Long userId, Long categoryId) throws DataNotFoundException;
	
	PostModel updatePost(PostModel postModel, Long postId) throws DataNotFoundException;

	String deletePost(Long postId) throws DataNotFoundException;

	PostModel getPostById(Long postId) throws DataNotFoundException;

	List<PostModel> getAllPost();

	List<PostModel> getPostByCategory(Long categoryId) throws DataNotFoundException;

	List<PostModel> getpostByUser(Long userId) throws DataNotFoundException;
}
