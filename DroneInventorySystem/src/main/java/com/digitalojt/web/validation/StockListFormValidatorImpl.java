package com.digitalojt.web.validation;

import org.thymeleaf.util.StringUtils;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.form.StockListForm;
import com.digitalojt.web.util.ParmCheckUtil;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 在庫情報画面のバリデーションチェック 実装クラス
 * 
 * @author ueno
 */
public class StockListFormValidatorImpl implements ConstraintValidator<StockListFormValidator, StockListForm> {

	/**
	 * バリデーションチェック
	 */
	@Override
	public boolean isValid(StockListForm form, ConstraintValidatorContext context) {

		boolean allFieldsEmpty = StringUtils.isEmpty(form.getName());

		// すべてのフィールドが空かをチェック
		if (allFieldsEmpty) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(ErrorMessage.ALL_FIELDS_EMPTY_ERROR_MESSAGE)
					.addConstraintViolation();
			return false;
		}

		// 在庫情報画面名のチェック
		if (form.getName() != null) {

			// 不正文字列チェック
			if (ParmCheckUtil.isParameterInvalid(form.getName())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}

			// 文字数チェック
			if (form.getName().length() > ErrorMessage.MAX_LENGTH) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.CENTER_NAME_LENGTH_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}	
				
		}
		
		if (null !=  String.valueOf(form.getCategoryId())) {
			
			// 文字数チェック
			if (99 < String.valueOf(form.getCategoryInfo()).length()) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.UNEXPECT_ERROR_MESSAGE)
						.addConstraintViolation();
				System.out.println(String.valueOf(form.getCategoryInfo()).length());
				return false;
			}	
		}
			

		// その他のバリデーションに問題なければtrueを返す
		return true;
	}
}
