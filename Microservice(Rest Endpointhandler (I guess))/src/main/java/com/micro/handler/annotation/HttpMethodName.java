package com.micro.handler.annotation;

public enum HttpMethodName {
	POST("post"),

	GET("get"),

	PUT("put"),

	DELETE("delete"),

	PATCH("patch");

	private String name = null;

	private HttpMethodName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
