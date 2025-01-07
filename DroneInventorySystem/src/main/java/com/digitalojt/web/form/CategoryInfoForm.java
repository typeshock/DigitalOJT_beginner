package com.digitalojt.web.form;

import com.digitalojt.web.validation.CategoryInfoFormValidator;

import jakarta.persistence.Id;
import lombok.Data;

/**
 * 分類情報画面のフォームクラス
 * 
 * @author ueno
 *
 */
@Data
@CategoryInfoFormValidator
public class CategoryInfoForm {

	/**
	 * 分類ID
	 */
	@Id
	private int category_id;

	/**
	 * 分類情報名
	 */
	private String categoryName;

}
