package com.digitalojt.web.validation;

import org.thymeleaf.util.StringUtils;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.form.CategoryInfoForm;
import com.digitalojt.web.util.ParmCheckUtil;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 分類情報画面のバリデーションチェック 実装クラス
 * 
 * @author ueno
 */
public class CategoryInfoFormValidatorImpl implements ConstraintValidator<CategoryInfoFormValidator, CategoryInfoForm> {

	/**
	 * バリデーションチェック
	 */

	// 最大文字数クラス定数
	public final int MAX_LENGTH = 20;

	@Override
	public boolean isValid(CategoryInfoForm form, ConstraintValidatorContext context) {

		boolean allFieldsEmpty = StringUtils.isEmpty(form.getCategoryName()) &&
				StringUtils.isEmpty(form.getPartsAddress());

		// すべてのフィールドが空かをチェック
		if (allFieldsEmpty) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(ErrorMessage.ALL_FIELDS_EMPTY_ERROR_MESSAGE)
					.addConstraintViolation();
			return false;
		}

		// 分類情報画面名のチェック
		if (form.getCategoryName() != null) {

			// 不正文字列チェック
			if (ParmCheckUtil.isParameterInvalid(form.getCategoryName())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}

			// 文字数チェック
			/**
			 *  TODO:Formクラスをシンプルにしたく、@Sizeを使わずこちらで桁数チェックを行いました。
			 *  	 車輪の再発明なので、しないほうがいいでしょうか？
			 */
			if (form.getCategoryName().length() > MAX_LENGTH) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.CENTER_NAME_LENGTH_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}
		}


		// その他のバリデーションに問題なければtrueを返す
		return true;
	}
}
