package com.blog.peoples.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.peoples.entity.PeoplesUser;
import com.blog.peoples.exception.DataNotFoundException;
import com.blog.peoples.model.UserModel;
import com.blog.peoples.repository.PeoplesUserRepo;
import com.blog.peoples.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private PeoplesUserRepo userRepo;

	@Override
	public UserModel createUser(UserModel userRequestModel) {
		log.info("inside createUser");

		PeoplesUser user = mapUserModeltoUserEntity(userRequestModel);
		userRepo.save(user);

		log.info("user persist in database");
		return mapUserEntitytoUserModel(user);
	}

	@Override
	public UserModel updateUser(UserModel userRequestModel, Long userId) throws DataNotFoundException {
		log.info("inside updateUser");

		PeoplesUser user = userRepo.findById(userId)
				.orElseThrow(() -> new DataNotFoundException("Updation failed, user id: " + userId + " not present"));

		user = mapUserModeltoUserEntity(userRequestModel, userId);
		userRepo.save(user);

		log.info("user id: " + userId + " updated successfully");
		return mapUserEntitytoUserModel(user);
	}

	@Override
	public UserModel getByUserId(Long userId) throws DataNotFoundException {
		log.info("inside getByUserId");

		PeoplesUser user = userRepo.findById(userId)
				.orElseThrow(() -> new DataNotFoundException("Requested User id: " + userId + " not present"));

		log.info("User found for id: " + userId);
		return mapUserEntitytoUserModel(user);
	}

	@Override
	public List<UserModel> getAllUser() {
		log.info("inside getAllUser");

		List<PeoplesUser> users = userRepo.findAll();

		return users.stream().map(this::mapUserEntitytoUserModel).toList();
	}

	@Override
	public String deleteUser(Long userId) throws DataNotFoundException {
		log.info("inside deleteUser");

		PeoplesUser user = userRepo.findById(userId)
				.orElseThrow(() -> new DataNotFoundException("Requested User id: " + userId + " not present"));

		log.info("User found for id: " + userId);
		userRepo.delete(user);

		return "User with id: " + userId + " is deleted";
	}

	/********************************************************************************************************************/

	protected UserModel mapUserEntitytoUserModel(PeoplesUser user) {
		return new UserModel().setName(user.getName()).setEmail(user.getEmail()).setPassword(user.getPassword())
				.setAbout(user.getAbout());
	}

	protected PeoplesUser mapUserModeltoUserEntity(UserModel userRequestModel) {
		return new PeoplesUser().setName(userRequestModel.getName()).setEmail(userRequestModel.getEmail())
				.setPassword(userRequestModel.getPassword()).setAbout(userRequestModel.getAbout());
	}

	protected PeoplesUser mapUserModeltoUserEntity(UserModel userRequestModel, Long userId) {
		return new PeoplesUser().setUserId(userId).setName(userRequestModel.getName())
				.setEmail(userRequestModel.getEmail()).setPassword(userRequestModel.getPassword())
				.setAbout(userRequestModel.getAbout());
	}

}
