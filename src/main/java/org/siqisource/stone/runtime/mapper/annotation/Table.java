package org.siqisource.stone.runtime.mapper.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Retention(RUNTIME)
public @interface Table {

	String schema() default "";

	String value();

	KeyGenerator keyGenerator() default KeyGenerator.auto_increment;

	String keySequence() default "";

}
