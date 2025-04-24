package com.digitalojt.web.validation;

import org.thymeleaf.util.StringUtils;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.consts.NumberValidConsts;
import com.digitalojt.web.form.CenterInfoForm;
import com.digitalojt.web.util.ParmCheckUtil;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 在庫センター情報画面のバリデーションチェック 実装クラス
 * 
 * @author ueno
 */
public class CenterInfoFormValidatorImpl implements ConstraintValidator<CenterInfoFormValidator, CenterInfoForm> {

	/**
	 * バリデーションチェック
	 */
	@Override
	public boolean isValid(CenterInfoForm form, ConstraintValidatorContext context) {

		/**
		 * すべてのフィールドが空かどうかをチェック
		 */
		boolean allFieldsEmpty = StringUtils.isEmpty(form.getCenterName()) && StringUtils.isEmpty(form.getRegion());

		// すべてのフィールドが空かをチェック
		if (allFieldsEmpty) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(ErrorMessage.ALL_FIELDS_EMPTY_ERROR_MESSAGE).addConstraintViolation();
			return false;
		}

		// センター名のチェック
		if (form.getCenterName() != null) {

			// 不正文字列チェック
			if (ParmCheckUtil.isParameterInvalid(form.getCenterName())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE).addConstraintViolation();
				return false;
			}

			// 文字数チェック
			if (form.getCenterName().length() > NumberValidConsts.MAX_LENGTH) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.CENTER_NAME_LENGTH_ERROR_MESSAGE).addConstraintViolation();
				return false;
			}
		}

		// 郵便番号のチェック
		if (form.getPostCode() != null) {
			// 不正文字列チェック
			if (ParmCheckUtil.isParameterInvalid(form.getPostCode())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE).addConstraintViolation();
				return false;
			}

			// 形式チェック
			if (ParmCheckUtil.isPostCode(form.getPostCode())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.FORMAT_ERROR_MESSAGE).addConstraintViolation();
				return false;
			}
		}

		// 住所のチェック
		if (form.getAddress() != null) {
			// 不正文字列チェック
			if (ParmCheckUtil.isParameterInvalid(form.getAddress())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE).addConstraintViolation();
				return false;
			}

			// 文字数チェック
			if (form.getAddress().length() > NumberValidConsts.MAX_ADDRESS_LENGTH) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.ADDLESS_LENGTH_ERROR_MESSAGE).addConstraintViolation();
				return false;
			}
		}

		// 電話番号のチェック
		if (form.getPhoneNumber() != null) {
			// 不正文字列チェック
			if (ParmCheckUtil.isParameterInvalid(form.getPhoneNumber())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE).addConstraintViolation();
				return false;
			}

			// 形式チェック
			if (ParmCheckUtil.isPhoneNumber(form.getPhoneNumber())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.FORMAT_ERROR_MESSAGE).addConstraintViolation();
				return false;
			}
		}

		// 管理者名のチェック
		if (form.getManagerName() != null) {
			// 不正文字列チェック
			if (ParmCheckUtil.isParameterInvalid(form.getManagerName())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE).addConstraintViolation();
				return false;
			}

			// 文字数チェック
			if (form.getManagerName().length() > NumberValidConsts.MAX_LENGTH) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.NAME_LENGTH_ERROR_MESSAGE).addConstraintViolation();
				return false;
			}
		}

		// 稼働状況のチェック
		if (form.getOperationalStatus() != null) {
			// 不正文字列チェック
			if (ParmCheckUtil.isParameterInvalid(form.getOperationalStatus())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE).addConstraintViolation();
				return false;
			}

			// 稼働状況正常数値チェック
			if (ParmCheckUtil.isOperationStatusFlag(form.getOperationalStatus())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.UNEXPECT_ERROR_MESSAGE).addConstraintViolation();
				return false;
			}

		}

		// 最大容量のチェック
		if (form.getMaxStorageCapacity() != null) {
			// 不正文字列チェック
			if (ParmCheckUtil.isParameterInvalid(form.getMaxStorageCapacity())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.MAX_STORAGE_ERROR_MESSAGE).addConstraintViolation();
				return false;
			}

			// 数値の範囲をチェック
			if (ParmCheckUtil.isWithinRange(form.getMaxStorageCapacity())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.NUMBER_LENGTH_ERROR_MESSAGE).addConstraintViolation();
				return false;
			}
		}

		// 現在容量のチェック
		if (form.getCurrentStorageCapacity() != null) {
			// 不正文字列チェック
			if (ParmCheckUtil.isParameterInvalid(form.getCurrentStorageCapacity())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.CURRENT_STORAGE_ERROR_MESSAGE).addConstraintViolation();
				return false;
			}

			// 数値の範囲をチェック
			if (ParmCheckUtil.isWithinRange(form.getCurrentStorageCapacity())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.NUMBER_LENGTH_ERROR_MESSAGE).addConstraintViolation();
				return false;
			}

			// 最大容量を超えていないかチェック
			if (ParmCheckUtil.isStorageCapacityOver(form.getMaxStorageCapacity(), form.getCurrentStorageCapacity())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.OVER_STORAGE_ERROR_MESSAGE).addConstraintViolation();
				return false;
			}

		}

		// 備考のチェック
		if (form.getNotes() != null) {
			// 不正文字列チェック
			if (ParmCheckUtil.isParameterInvalid(form.getNotes())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE).addConstraintViolation();
				return false;
			}

			// 文字数チェック
			if (form.getNotes().length() > NumberValidConsts.MAX_LENGTH) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.NAME_LENGTH_ERROR_MESSAGE).addConstraintViolation();
				return false;
			}
		}

		// 都道府県のチェック
		if (form.getRegion() != null) {

			// 不正文字列チェック
			if (ParmCheckUtil.isParameterInvalid(form.getRegion())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE).addConstraintViolation();
				return false;
			}
		}

		// その他のバリデーションに問題なければtrueを返す
		return true;
	}
}
