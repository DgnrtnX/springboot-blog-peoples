package com.blog.peoples.serviceImpl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.peoples.entity.PeoplesUser;
import com.blog.peoples.entity.UserCategory;
import com.blog.peoples.entity.UserPost;
import com.blog.peoples.exception.DataNotFoundException;
import com.blog.peoples.model.CategoryModel;
import com.blog.peoples.model.PostModel;
import com.blog.peoples.model.UserModel;
import com.blog.peoples.repository.PeoplesUserRepo;
import com.blog.peoples.repository.UserCategoryRepo;
import com.blog.peoples.repository.UserPostRepo;
import com.blog.peoples.service.PostService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

	@Autowired
	private UserPostRepo postRepo;

	@Autowired
	private PeoplesUserRepo userRepo;

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private UserCategoryRepo categoryRepo;

	@Autowired
	private CategoryServiceImpl categoryService;

	@Override
	public PostModel createPost(PostModel postModel, Long userId, Long categoryId) throws DataNotFoundException {
		log.info("Inside createPost");
		PeoplesUser user = userRepo.findByUserId(userId);
		UserCategory category = categoryRepo.findByCategoryId(categoryId);

		if (user == null)
			throw new DataNotFoundException("Post cannot be saved. User Id not found: " + userId);
		else if (category == null)
			throw new DataNotFoundException("Post cannot be saved. Category Id not found: " + categoryId);
		else {
			UserPost post = mapPostModelToPost(postModel);
			post.setImageName("default.png");
			post.setUser(user);
			post.setCategory(category);

			postRepo.save(post);
			log.info("Saving post: {}", post.toString());

			return mapPostToPostModel(post);
		}
	}

	@Override
	public PostModel updatePost(PostModel postModel, Long postId) throws DataNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deletePost(Long postId) throws DataNotFoundException {
		UserPost post = postRepo.findById(postId)
				.orElseThrow(() -> new DataNotFoundException("Post not deleted. No post found for id: " + postId));
		postRepo.delete(post);
		log.info("Post deleted with postId: {}", postId);
		return "post deleted successfully";
	}

	@Override
	public PostModel getPostById(Long postId) throws DataNotFoundException {
		UserPost post = postRepo.findById(postId)
				.orElseThrow(() -> new DataNotFoundException("Post Id not found: " + postId));
				
			return mapPostToPostModel(post);

	}

	@Override
	public List<PostModel> getAllPost() {
		List<UserPost> post = postRepo.findAll();

		return post.stream().map(this::mapPostToPostModel).toList();
	}

	@Override
	public List<PostModel> getPostByCategory(Long categoryId) throws DataNotFoundException {
		UserCategory category = categoryRepo.findByCategoryId(categoryId);
		if (category != null) {
			List<UserPost> post = postRepo.findByCategory(category);

			return post.stream().map(this::mapPostToPostModel).toList();
		} else
			throw new DataNotFoundException("Post fetch fail.No Category found: " + categoryId);
	}

	@Override
	public List<PostModel> getpostByUser(Long userId) throws DataNotFoundException {
		PeoplesUser user = userRepo.findByUserId(userId);
		if (user != null) {
			List<UserPost> post = postRepo.findByUser(user);

			return post.stream().map(this::mapPostToPostModel).toList();
		} else
			throw new DataNotFoundException("Post fetch fail.No Category found: " + userId);
	}

	/********************************************************************************************************/

	private UserPost mapPostModelToPost(PostModel model) {
		return new UserPost().setTitle(model.getTitle()).setContent(model.getContent())
				.setCreateDate(new Timestamp(System.currentTimeMillis()));
	}

	private PostModel mapPostToPostModel(UserPost model) {
		UserModel userModel = userService.mapUserEntitytoUserModel(model.getUser());

		CategoryModel categoryModel = categoryService.mapCategoryToCategoryModel(model.getCategory());

		return new PostModel().setTitle(model.getTitle()).setContent(model.getContent())
				.setCreatedDate(model.getCreateDate()).setUser(userModel).setCategory(categoryModel);
	}

}
