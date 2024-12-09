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

	@Id
	private int category_id;

	private String categoryName;
	/*
	private category_name VARCHAR(256);
	
	varchar delete_flag;
	
	datetime create_data;
	
	date time update_data;
	*/
}
