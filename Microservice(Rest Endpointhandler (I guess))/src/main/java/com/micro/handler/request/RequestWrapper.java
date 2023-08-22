package com.micro.handler.request;

import java.util.*;

public interface RequestWrapper {

	public String getMethod();

	public String getRequestURI();

	public Map<String, String> getParameters();

	public Map<String, String> getHeaders();
}
