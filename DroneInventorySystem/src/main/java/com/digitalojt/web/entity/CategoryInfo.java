package com.digitalojt.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/**
 * 分類情報Entity
 * 
 * @author ueno
 *
 */
@Data
@Getter
@Setter
@Entity
public class CategoryInfo {
	
	/**
	 * 分類ID
	 */
	@Id
	private int categoryId;

	/**
	 * 分類情報名
	 */
	private String categoryName;

}
