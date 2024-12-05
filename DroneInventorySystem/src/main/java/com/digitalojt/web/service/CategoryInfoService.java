package com.digitalojt.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.digitalojt.web.entity.CategoryInfo;
import com.digitalojt.web.repository.CategoryInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * 分類情報画面のサービスクラス
 *
 * @author ueno
 * 
 */
@Service
@RequiredArgsConstructor
public class CategoryInfoService {
		
	/** 分類情報テーブル リポジトリー */
	private final CategoryInfoRepository repository;

	/**
	 * 部品情報をすべて取得
	 * 
	 * @return
	 */
	public List<CategoryInfo> getCategoryInfoData() {
		return repository.findAll();
	}
}
