package com.ying.springboot_test.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import com.ying.springboot_test.service.UserService;

@RestController
@RequestMapping(value = "/user/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/sign_in/{user_id}", method = RequestMethod.GET)
	public String signIn (@PathVariable("user_id") Long userId) {
		return userService.signIn(userId);
	}
	
	@RequestMapping(value = "/sign_in_2/{user_id}", method = RequestMethod.GET)
	public Callable<String> signIn2 (@PathVariable ("user_id") Long userId) {
		System.out.println("callable start;");
		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				TimeUnit.SECONDS.sleep(5);
				return "user: " + userId +" sign in success.";
			}
		};
		
		System.out.println("callable end;");
		
		return callable; 
	}
	
	@RequestMapping(value = "/sign_in_3/{user_id}", method = RequestMethod.GET)
	public DeferredResult<String> signIn3 (@PathVariable("user_id") Long userId) {
		System.out.println("DeferredResult start;");
		DeferredResult<String> deferredResult = new DeferredResult<>(2000L); //time out time
		
		CompletableFuture.<String>supplyAsync(()->{
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "user: " + userId +" sign in success.";
		}).whenCompleteAsync((result, throwable)->{
			deferredResult.setResult(result);
		});
		
		deferredResult.onTimeout(()->{
			deferredResult.setResult("DeferredResult time out.");
		});
		
		System.out.println("DeferredResult end;");
		
		return deferredResult;
	}
	
	@RequestMapping(value = "/sign_in_4/{user_id}", method = RequestMethod.GET)
	public WebAsyncTask signIn4 (@PathVariable("user_id") Long userId) {
		System.out.println("WebAsyncTask start;");
		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				TimeUnit.SECONDS.sleep(5);
				return "user: " + userId +" sign in success.";
			}
		};
		
		WebAsyncTask asyncTask = new WebAsyncTask(2000, callable);
	    asyncTask.onTimeout(
	            new Callable<String>() {
	                public String call() throws Exception {
	                    return "time out!";
	                }
	            }
	    );
		
		System.out.println("WebAsyncTask end;");
		
//		return new WebAsyncTask<String>(callable);
		return asyncTask;
	}

}
