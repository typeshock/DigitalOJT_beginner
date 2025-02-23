package com.digitalojt.web.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 在庫一覧情報Entity
 * 
 * @author ueno
 *
 */
@Data
@Getter
@Setter
@Entity
public class StockInfo {

	/**
	 * 在庫ID
	 */
	@Id
	private int stockId;

	/**
	 * 分類ID
	 * 関連付け
	 */
	@ManyToOne
	@JoinColumn(name = "categoryId", insertable = false, updatable = false)
	private CategoryInfo categoryInfo;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 在庫センターID
	 * 関連付け
	 */
	@ManyToOne
	@JoinColumn(name = "centerId", insertable = false, updatable = false)
	private CenterInfo centerInfo;

	/**
	 * 数量
	 */
	private int amount;

	/**
	 * 数量範囲
	 */
	private String amountRange;

	/**
	 * 論理削除フラグ
	 */
	private String deleteFlag;

	/**
	 * 説明
	 */
	private String description;

	/**
	 * 更新日
	 */
	private Timestamp updateDate;

	/**
	 * 登録日
	 */
	private Timestamp createDate;
}
