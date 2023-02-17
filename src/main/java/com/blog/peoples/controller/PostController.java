package com.blog.peoples.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.peoples.exception.DataNotFoundException;
import com.blog.peoples.model.PostModel;
import com.blog.peoples.service.PostService;

@RestController
@RequestMapping("api")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostModel> createPost(@RequestBody PostModel postModel, @PathVariable Long userId,
			@PathVariable Long categoryId) throws DataNotFoundException {
		return new ResponseEntity<>(postService.createPost(postModel, userId, categoryId), HttpStatus.CREATED);
	}

	@GetMapping("/post/{userId}")
	public ResponseEntity<PostModel> getPostbyId(@PathVariable Long userId) throws DataNotFoundException {
		return ResponseEntity.ok(postService.getPostById(userId));
	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostModel>> getPostbyUser(@PathVariable Long userId) throws DataNotFoundException {
		return ResponseEntity.ok(postService.getpostByUser(userId));
	}

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostModel>> getPostByCategory(@PathVariable Long categoryId)
			throws DataNotFoundException {
		return ResponseEntity.ok(postService.getPostByCategory(categoryId));
	}

	@GetMapping("/post/all")
	public ResponseEntity<List<PostModel>> getAllUserPost() {
		return ResponseEntity.ok(postService.getAllPost());
	}

}
