package com.micro.rest;

import com.micro.handler.annotation.*;

@RequestPath("/auth/{user}")
public class Authenticate {

	@HttpMethod(HttpMethodName.POST)
	public static boolean authenticate(@RequestParameter("user") String user, @RequestParameter("password") String password) {
		System.out.println("User: " + user);
		System.out.println("Password: " + password);
		return true;
	}

}
