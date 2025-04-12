package com.digitalojt.web.form;

import java.sql.Timestamp;

import com.digitalojt.web.consts.NumberValidConsts;
import com.digitalojt.web.validation.CenterInfoFormValidator;

import jakarta.persistence.Id;
import lombok.Data;

/**
 * 在庫センター情報画面のフォームクラス
 * 
 * @author ueno
 *
 */
@Data
@CenterInfoFormValidator
public class CenterInfoForm {

	/**
	 * センター名
	 */
	private String centerName;

	/**
	 * 都道府県
	 */
	private String region;

	/**
	 * センターID
	 */
	@Id
	private int centerId;

	/**
	 * 郵便番号
	 */
	private String postCode;

	/**
	 * 住所
	 */
	private String address;

	/**
	 * 電話番号
	 */
	private String phoneNumber;

	/**
	 * 管理者名
	 */
	private String managerName;

	/**
	 * 稼働状況ステータス
	 */
	private String operationalStatus;

	/**
	 * 最大容量
	 */
	private String maxStorageCapacity;

	/**
	 * 現在容量
	 */
	private String currentStorageCapacity;

	/**
	 * 備考
	 */
	private String notes;

	/**
	 * 論理削除フラグ
	 */
	private String deleteFlag;

	/**
	 * 作成日時
	 */
	private Timestamp createDate;

	/**
	 * 更新日時
	 */
	private Timestamp updateDate;

	/**
	 * 論理削除フラグを未削除（0）に設定
	 * 
	 * @return
	 */
	public String getDeleteFlagRegister() {

		deleteFlag = NumberValidConsts.DELETE_FLAG_REGISTER_NUMBER;
		return deleteFlag;
	}

	/**
	 * 論理削除フラグを削除（1）に設定
	 * 
	 * @return
	 */
	public String getDeleteFlagDeleter() {

		deleteFlag = NumberValidConsts.DELETE_FLAG_DELETE_NUMBER;
		return deleteFlag;
	}

}
