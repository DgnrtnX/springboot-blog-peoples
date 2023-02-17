package com.blog.peoples.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.peoples.exception.DataNotFoundException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleNoUserException(DataNotFoundException exception) {
		Map<String, String> errMap = new HashMap<String, String>();

		log.error("DataNotFoundException: {}", exception.getMessage());
		errMap.put("DataNotFoundException", exception.getMessage());

		return new ResponseEntity<>(errMap, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgNotValidExp(MethodArgumentNotValidException exception) {
		Map<String, String> map = new HashMap<String, String>();

		exception.getBindingResult().getFieldErrors().forEach(error -> {
			map.put(error.getField(), error.getDefaultMessage());
			log.error("MethodArgumentNotValidException: {} :: {}", error.getField(), error.getDefaultMessage());
		});

		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	}
}
