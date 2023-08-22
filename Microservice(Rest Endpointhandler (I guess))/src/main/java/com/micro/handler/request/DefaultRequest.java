package com.micro.handler.request;

import java.util.*;

public class DefaultRequest implements RequestWrapper {

	private String method = null;
	private String requestURI = null;
	private Map<String, String> params = null;
	private Map<String, String> headers = null;

	public DefaultRequest(String method, String requestURI, Map<String, String> params, Map<String, String> headers) {
		this.method = method;
		this.requestURI = requestURI;
		this.params = params;
		this.headers = headers;
	}

	@Override
	public String getMethod() {
		return method;
	}

	@Override
	public String getRequestURI() {
		return requestURI;
	}

	@Override
	public Map<String, String> getParameters() {
		return params;
	}

	@Override
	public Map<String, String> getHeaders() {
		return headers;
	}

}
