package com.digitalojt.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitalojt.web.entity.StockInfo;

/**
 * 在庫情報テーブルリポジトリー
 *
 * @author ueno
 * 
 */
@Repository
public interface StockListRepository extends JpaRepository<StockInfo, Integer> {
	
	// TODO: インデントが揃っていないかもです。cntl+shift+Fで自動保管しましたか？

//	/**
//	 * 引数に合致する在庫情報を取得
//	 * 
//	 * @param categoryId
//	 * ↑現状のSQL文では指定無しの場合に該当するリスト無しとなってしまう。
//	 * @param centerName
//	 * @param amount
//	 * @return paramで個数をamount以上で検索した結果
//	 */
//	@Query("SELECT s FROM StockInfo s WHERE "+
//	"(:categoryId IS NULL OR categoryId = :categoryId) AND "+ 
//	"(:name = '' OR s.name LIKE %:name%) AND "+ 
//	"(amount <= :amount OR :amount IS NULL")
//	List<StockInfo> findByCategoryIdAndNameAndAmount(int categoryId,String name,int amount);
	
	/**
	 * 引数に合致する在庫情報を取得
	 * 
	 * @param categoryId
	 * @param stockName
	 * @param amount
	 * @param isAboveOrBelowFlag
	 * @return paramで検索した結果
	 */
	@Query("SELECT s FROM StockInfo s " +
			"WHERE (:categoryId IS NULL OR s.categoryInfo.categoryId = :categoryId) " +
			"AND (:name IS NULL OR s.name LIKE %:name%) " +
			"AND (:amount IS NULL OR " +
			"     (:amountRange = 0 AND s.amount >= :amount) OR " +
			"     (:amountRange = 1 AND s.amount <= :amount)) " +
			"AND s.deleteFlag = '0' " +
			"ORDER BY s.categoryInfo.categoryId ASC, s.amount DESC")
	List<StockInfo> findByCategoryIdAndNameAndAmount(Integer categoryId, String name, Integer amount,
			Integer amountRange);
}
