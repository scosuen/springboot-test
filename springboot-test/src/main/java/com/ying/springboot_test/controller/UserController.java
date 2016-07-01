package com.ying.springboot_test.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user/")
public class UserController {
	
	@RequestMapping(value = "/sign_in", method = RequestMethod.POST)
	public String signIn (@RequestBody String json) {
		
		
		return "123321";
		
	}

}
