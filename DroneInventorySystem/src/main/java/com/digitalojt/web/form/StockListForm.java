package com.digitalojt.web.form;

import com.digitalojt.web.validation.StockListFormValidator;

import lombok.Data;

/**
 * 在庫情報画面のフォームクラス
 * 
 * @author ueno
 *
 */
@Data
@StockListFormValidator
public class StockListForm {

	/**
	 * カテゴリID
	 */
	private Integer categoryId;

	/**
	 * 在庫名
	 */
	private String name;

	/**
	 * 数量
	 */
	private Integer amount;

	/**
	 * 以上(0) 以下(1)のフラグ
	 */
	private Integer amountRange;

}
