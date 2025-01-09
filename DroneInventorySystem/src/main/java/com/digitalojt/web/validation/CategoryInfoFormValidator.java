package com.digitalojt.web.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.digitalojt.web.consts.ErrorMessage;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * 分類情報画面のバリデーションチェック インターフェース
 * 
 * @author ueno
 */
@Constraint(validatedBy = CategoryInfoFormValidatorImpl.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface CategoryInfoFormValidator {

	String message() default ErrorMessage.ALL_FIELDS_EMPTY_ERROR_MESSAGE;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
