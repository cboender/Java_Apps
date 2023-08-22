package com.micro.handler;

import java.util.*;

import com.micro.handler.annotation.*;
import com.micro.handler.request.*;

public class RequestHandler {

	private List<EndPoint> endpoints = null;

	public RequestHandler() {
		endpoints = new ArrayList<>();
	};

	public void bindEndPoint(Class<?> epClass) {
		if (epClass.getAnnotation(RequestPath.class) != null) {
			EndPoint ep = new EndPoint(epClass);
			endpoints.add(ep);
		} else {
			System.out.println("Class " + epClass.getName() + " Not bound bececause no RequestPath was specified");
		}
	}

	public Response handleRequest(RequestWrapper request) throws Exception {
		Response resp = new Response();

		ParsedEndpoint endpoint = getEndPoint(request.getRequestURI());
		if (endpoint != null) {
			EndPointMethod epMethod = getEndPointMethod(endpoint, request);
			if (epMethod != null) {
				epMethod.call(request.getParameters(), endpoint.attributes);
			} else {
				System.out.println("Unsupported Method " + request.getMethod());
			}
		} else {
			System.out.println("endpoint " + request.getRequestURI() + " not found");
		}

		return resp;
	}

	private EndPointMethod getEndPointMethod(ParsedEndpoint pe, RequestWrapper request) {
		List<EndPointMethod> methods = pe.endpoint.getMethods(request.getMethod());
		for (EndPointMethod epm : methods) {
			boolean valid = true;
			for (String attr : epm.getAttributes()) {
				if (pe.attributes.get(attr) == null) {
					valid = false;
					break;
				}
			}
			if (valid) {
				// Only go through the params if the attributes were valid
				for (String param : epm.getParameters()) {
					if (request.getParameters().get(param) == null) {
						valid = false;
						break;
					}
				}
				if (valid) {
					// All inputs were succesfully found
					return epm;
				}
			}
		}
		return null;
	}

	private ParsedEndpoint getEndPoint(String uri) {
		if (uri.startsWith("/")) {
			uri = uri.substring(1);
		}
		uri = uri.toLowerCase();
		for (EndPoint ep : endpoints) {
			ParsedEndpoint pe = parseEndpoint(ep, uri);
			if (pe != null) {
				return pe;
			}
		}

		return null;
	}

	private ParsedEndpoint parseEndpoint(EndPoint ep, String uri) {
		String[] parts = uri.split("/");
		if (ep.getParts().size() > parts.length) {
			// Can never match
			return null;
		}
		Map<String, String> attributes = new HashMap<>();
		// TODO doesn't handle any trailing values
		for (int i = 0; i < ep.getParts().size(); i++) {
			String part = ep.getParts().get(i);
			String uriPart = parts[i];
			if (part.startsWith("{")) {
				attributes.put(part.substring(1, part.length() - 1), uriPart);
				// Expected attribute so continue;
			} else if (uriPart.equalsIgnoreCase(part)) {
				// Continue
			} else {
				return null;
			}
		}
		ParsedEndpoint pe = new ParsedEndpoint();
		pe.endpoint = ep;
		pe.attributes = attributes;
		return pe;
	}

	private class ParsedEndpoint {
		public EndPoint endpoint;
		public Map<String, String> attributes = null;

		private ParsedEndpoint() {
		}

	}

}
