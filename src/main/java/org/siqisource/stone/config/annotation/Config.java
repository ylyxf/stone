package org.siqisource.stone.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Config {

	String label();

	String comment() default "";

	String sortNo() default "0";
	
	String encode() default "false";
}
