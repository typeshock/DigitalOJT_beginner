package com.digitalojt.web.validation;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.consts.NumberValidConsts;
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

		// すべてのフィールドが空かをチェック
		if (areAllFieldsEmpty(form)) {
			setErrorMessage(context, ErrorMessage.ALL_FIELDS_EMPTY_ERROR_MESSAGE);
			return false;
		}

		// 在庫名のチェック
		if (form.getName() != null) {

			//不正文字列チェック
			if (ParmCheckUtil.isParameterInvalid(form.getName())) {
				setErrorMessage(context, ErrorMessage.INVALID_INPUT_ERROR_MESSAGE);
				return false;
			}

			// 文字数チェック
			if (form.getName().length() > NumberValidConsts.MAX_LENGTH) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.NAME_LENGTH_ERROR_MESSAGE).addConstraintViolation();
				return false;
			}
		}

		// 数量のチェック
		if (form.getAmount() != null) {

			// 数値の範囲をチェック
			if (ParmCheckUtil.isWithinRange(form.getAmount())) {
				setErrorMessage(context, ErrorMessage.NUMBER_LENGTH_ERROR_MESSAGE);
				return false;
			}
		}

		// 以上・以下のフラグチェック
		if (form.getAmountRange() != null) {

			// 数値の範囲をチェック
			if (ParmCheckUtil.isAmountRange(form.getAmountRange())) {
				setErrorMessage(context, ErrorMessage.UNEXPECTED_NUMBER_INPUT_ERROR_MESSAGE);
				return false;
			}
		}

		return true;
	}

	/**
	 * すべてのフィールドが空かどうかをチェック
	 */
	private boolean areAllFieldsEmpty(StockListForm form) {
		return (form.getCategoryId() == null) && (form.getName() == null || form.getName().isEmpty()) && (form.getAmount() == null);
	}

	/**
	 * エラーメッセージを設定する
	 */
	private void setErrorMessage(ConstraintValidatorContext context, String errorMessage) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
	}
}
