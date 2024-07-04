package com.SpringCloudApp.util.aop.date;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Aspect
@Component
@Slf4j
public class DateFormatCheckBusiness {

    @Before("execution(* *.*(..)) && @annotation(formatCheck)")
    public void validateDateFormat(JoinPoint joinPoint, DateFormatCheck formatCheck) throws NoSuchFieldException, IllegalAccessException, ParseException {
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            Field[] fields = arg.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(DateFormatCheck.class)) {
                    field.setAccessible(true);
                    Object value = field.get(arg);
                    if (value instanceof String) {
                        String dateString = (String) value;
                        String pattern = field.getAnnotation(DateFormatCheck.class).pattern();
                        if (!isValidDateFormat(dateString, pattern)) {
                            throw new IllegalArgumentException("Invalid date format: " + dateString + ". Expected format: " + pattern);
                        }
                    }
                }
            }
        }
    }

    private boolean isValidDateFormat(String dateString, String pattern) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            dateFormat.setLenient(false);
            dateFormat.parse(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
