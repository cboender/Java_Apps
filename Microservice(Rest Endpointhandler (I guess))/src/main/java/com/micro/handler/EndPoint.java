package com.micro.handler;

import java.lang.reflect.*;
import java.util.*;

import com.micro.handler.annotation.*;

public class EndPoint {

	private String path = null;

	private List<String> attributes = null;

	private Map<HttpMethodName, List<EndPointMethod>> methods = null;

	private List<String> parts = null;

	public EndPoint(Class<?> clazz) {
		RequestPath path = clazz.getAnnotation(RequestPath.class);
		if (path == null) {
			throw new RuntimeException("Class " + clazz.getName() + " is not an endpoint.");
		}

		this.path = path.value();
		if (this.path.startsWith("/")) {
			this.path = this.path.substring(1);
		}
		parts = new ArrayList<>();
		attributes = new ArrayList<>();
		calcParts();

		methods = new HashMap<>();
		calcMethods(clazz);
	}

	private void calcParts() {
		String[] parts = path.split("/");

		// TODO make more complex?
		for (String part : parts) {
			if (part.startsWith("{")) {
				this.attributes.add(part.substring(1, part.length() - 1));
			}
			this.parts.add(part);
		}
	}

	private void calcMethods(Class<?> c) {
		for (Method method : c.getMethods()) {
			HttpMethod hm = method.getAnnotation(HttpMethod.class);
			if (hm != null) {
				EndPointMethod epm = EndPointMethod.create(method);
				if (epm != null) {
					List<EndPointMethod> epMethods = methods.getOrDefault(hm.value(), new ArrayList<>(1));
					epMethods.add(epm);
					methods.putIfAbsent(hm.value(), epMethods);
				}
			}
		}
	}

	public List<String> getParts() {
		return parts;
	}

	public List<EndPointMethod> getMethods(String methodName) {
		return methods.getOrDefault(HttpMethodName.valueOf(methodName.toUpperCase()), new ArrayList<>(0));
	}
}
