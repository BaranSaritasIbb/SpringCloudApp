package com.SpringCloudApp.util.aop.date;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormatCheck {
    String message() default "Invalid date format";
    String pattern() default "yyyy-MM-dd";
}
