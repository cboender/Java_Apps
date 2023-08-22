package com.micro.handler.annotation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.*;

@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface HttpMethod {
	HttpMethodName value();
}
