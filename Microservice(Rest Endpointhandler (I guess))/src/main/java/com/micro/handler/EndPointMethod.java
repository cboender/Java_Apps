package com.micro.handler;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

import com.micro.handler.annotation.*;

public class EndPointMethod {

	private Method method = null;

	private List<String> parameters = null;

	private List<String> attributes = null;

	private List<InputParameter> inputParams = null;

	private EndPointMethod(Method method, List<String> attributes, List<String> parameters,
			List<InputParameter> inputParams) {
		this.method = method;
		this.parameters = parameters;
		this.attributes = attributes;
		this.inputParams = inputParams;
	}

	public static EndPointMethod create(Method method) {
		boolean create = true;
		List<String> parameters = new ArrayList<>();
		List<String> attributes = new ArrayList<>();
		List<InputParameter> ips = new ArrayList<>(method.getParameters().length);
		for (Parameter param : method.getParameters()) {
			Annotation[] annos = param.getAnnotations();
			if (annos.length == 0) {
				create = false;
				break;
			}
			boolean found = false;
			for (Annotation anno : annos) {
				if (anno.annotationType() == URIAttribute.class) {
					String val = ((URIAttribute) anno).value();
					attributes.add(val);
					ips.add(new InputParameter(val, true));
					found = true;
					break;
				} else if (anno.annotationType() == RequestParameter.class) {
					String val = ((RequestParameter) anno).value();
					ips.add(new InputParameter(val, false));
					parameters.add(val);
					found = true;
					break;
				}
			}
			if (!found) {
				create = false;
				break;
			}
		}
		EndPointMethod epm = null;
		if (create) {
			epm = new EndPointMethod(method, Collections.unmodifiableList(attributes),
					Collections.unmodifiableList(parameters), ips);
		}
		return epm;
	}

	public void call(Map<String, String> parameters, Map<String, String> attributes) throws Exception {
		Object[] params = new Object[inputParams.size()];
		for (int i = 0; i < inputParams.size(); i++) {
			InputParameter ip = inputParams.get(i);
			if (ip.attribute) {
				params[i] = attributes.get(ip.name);
			} else {
				params[i] = parameters.get(ip.name);
			}
		}
		method.invoke(null, params);
	}

	public Method getMethod() {
		return method;
	}

	public List<String> getParameters() {
		return parameters;
	}

	public List<String> getAttributes() {
		return attributes;
	}

}
