package com.ying.springboot_test.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;

@ControllerAdvice
@ResponseBody
public class ExceptionController {

	@ExceptionHandler(Exception.class)
	public String processException(NativeWebRequest request, Exception e) {
		return "Process exception. user id: " + request.getParameter("user_id");
	}
}
