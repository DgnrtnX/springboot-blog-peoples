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
import com.blog.peoples.model.UserModel;
import com.blog.peoples.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/create")
	public ResponseEntity<UserModel> createUser(@Valid @RequestBody UserModel userRequestModel) {
		return new ResponseEntity<>(userService.createUser(userRequestModel), HttpStatus.CREATED);
	}

	@PostMapping("/update")
	public ResponseEntity<UserModel> updateUser(@Valid @RequestBody UserModel userRequestModel,
			@RequestParam("id") Long userId) throws DataNotFoundException {
		return ResponseEntity.ok(userService.updateUser(userRequestModel, userId));
	}

	@GetMapping("/get")
	public ResponseEntity<UserModel> getById(@RequestParam("id") Long userId)
			throws DataNotFoundException {
		return ResponseEntity.ok(userService.getByUserId(userId));
	}

	@GetMapping("/all")
	public ResponseEntity<List<UserModel>> getAllUser() {
		return ResponseEntity.ok(userService.getAllUser());
	}

	@GetMapping("/delete")
	public ResponseEntity<String> deleteUser(@RequestParam("id") Long userId) throws DataNotFoundException {
		return ResponseEntity.ok(userService.deleteUser(userId));
	}

}
