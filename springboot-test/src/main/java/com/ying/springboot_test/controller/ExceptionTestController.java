package com.ying.springboot_test.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/exc_test/")
public class ExceptionTestController {

	@RequestMapping(value = "/test1/{user_id}", method = RequestMethod.GET)
	public String exceptionTest1(@PathVariable("user_id") Long userId) throws Exception {
		if (userId != 1) {
			throw new Exception();
		}
		return "exception test 1. user id: " + userId;
	}
}
