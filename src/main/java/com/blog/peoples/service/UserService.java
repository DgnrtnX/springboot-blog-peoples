package com.blog.peoples.service;

import java.util.List;

import com.blog.peoples.exception.DataNotFoundException;
import com.blog.peoples.model.UserModel;

public interface UserService {
	
	public UserModel createUser(UserModel userRequestmodel);
	public UserModel updateUser(UserModel userRequestModel, Long userId) throws DataNotFoundException;
	public UserModel getByUserId(Long userId) throws DataNotFoundException;
	public List<UserModel> getAllUser();
	public String deleteUser(Long userId) throws DataNotFoundException;

}
