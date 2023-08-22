package com.micro.handler.annotation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.*;

@Retention(RUNTIME)
@Target(PARAMETER)
public @interface URIAttribute {
	String value();
}
