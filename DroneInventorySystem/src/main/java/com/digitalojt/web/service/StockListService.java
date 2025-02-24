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
	 * IDでソートした在庫情報を全件取得
	 * 
	 * @param stockId
	 * @return
	 */
	public List<StockInfo> getStockListData() {
		return repository.findAllByOrderByStockId();
	}

	/**
	 * 引数に合致する在庫一覧情報を取得
	 * 
	 * @param categoryId
	 * @param stockName
	 * @param amount
	 * @param amountRange
	 * @return
	 */
	public List<StockInfo> getStockListData(Integer categoryId, String stockName, Integer amount, Integer amountRange) {
		return repository.findByCategoryIdAndNameAndAmount(categoryId, stockName, amount, amountRange);
	}
}
