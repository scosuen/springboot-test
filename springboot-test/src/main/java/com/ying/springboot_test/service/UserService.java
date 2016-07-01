package com.ying.springboot_test.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	public String signIn (Long userId) {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return "user:" + userId + " sign in success.";
	}

}
