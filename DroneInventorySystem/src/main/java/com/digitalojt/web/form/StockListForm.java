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

	// TODO: Formに必要ない情報を定義しています。必要な分だけ定義して下さい。
	//		 stockIdやdeleteFlagなど、入力値にない情報が含まれているように見えます。
	
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
	
//	/**
//	 * 在庫ID
//	 */
//	@Id
//	private int stockId;
//
//	/**
//	 * 分類ID
//	 */
//	private int categoryId;
//
//	/**
//	 * 分類ID
//	 * 関連付け
//	 */
//	@ManyToOne
//	@JoinColumn(name = "categoryId", insertable = false, updatable = false)
//	private CategoryInfo categoryInfo;
//
//	/**
//	 * 名称
//	 */
//	private String name;
//
//	/**
//	 * 在庫センターID
//	 * 関連付け
//	 */
//	@ManyToOne
//	@JoinColumn(name = "centerId", insertable = false, updatable = false)
//	private CenterInfo centerInfo;
//
//	/**
//	 * 数量
//	 */
//	private int amount;
//
//	/**
//	 * 数量範囲
//	 */
//	private String amountRange;
//
//	/**
//	 * 論理削除フラグ
//	 */
//	private String deleteFlag;
//
//	/**
//	 * 説明
//	 */
//	private String description;
//
//	/**
//	 * 更新日
//	 */
//	private Timestamp updateDate;
//
//	/**
//	 * 登録日
//	 */
//	private Timestamp createDate;

}
