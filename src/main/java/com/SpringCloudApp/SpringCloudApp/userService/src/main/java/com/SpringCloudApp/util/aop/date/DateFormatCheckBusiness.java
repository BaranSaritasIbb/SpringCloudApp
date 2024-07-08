package com.SpringCloudApp.util.aop.date;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Aspect
@Component
@Slf4j
public class DateFormatCheckBusiness implements ConstraintValidator<DateFormatCheck, LocalDate> {

        private static final String DATE_PATTERN = "MM/dd/yyyy";

        @Override
        public void initialize(DateFormatCheck customDate) {
        }

        @Override
        public boolean isValid(LocalDate customDateField,
                               ConstraintValidatorContext cxt) {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
            try
            {
                sdf.setLenient(false);
                Date d = sdf.parse(String.valueOf(customDateField));
                return true;
            }
            catch (ParseException e)
            {
                return false;
            }
        }

    }