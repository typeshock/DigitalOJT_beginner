package com.digitalojt.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.digitalojt.web.entity.StockInfo;
import com.digitalojt.web.repository.StockListRepository;

import lombok.RequiredArgsConstructor;

/**
 * 在庫情報画面のサービスクラス
 *
 * @author ueno
 * 
 */
@Service
@RequiredArgsConstructor
public class StockListService {

	/** 在庫情報テーブル リポジトリー */
	private final StockListRepository repository;

	/**
	 * 在庫情報をすべて取得
	 * 
	 * @return
	 */
	public List<StockInfo> getStockListData() {
		return repository.findAll();
	}

	/**
	 * 引数に合致する分類情報と在庫情報のIDを取得
	 * 
	 * @param categoryId
	 * @return
	 */
	public List<StockInfo> getStockListData(int categoryId,String name) {
		return repository.findByCategoryIdAndName(categoryId, name);
	}

}
