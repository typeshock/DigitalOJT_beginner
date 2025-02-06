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

	/**
	 * 引数に合致する在庫情報を取得
	 * 
	 * @param categoryId
	 * ↑現状のSQL文では指定無しの場合に該当するリスト無しとなってしまう。
	 * @param centerName
	 * @param amount
	 * @return paramで個数をamount以上で検索した結果
	 */
	@Query("SELECT s FROM StockInfo s WHERE "+
	"(:categoryId IS NULL OR categoryId = :categoryId) AND "+ 
	"(:name = '' OR s.name LIKE %:name%) AND "+ 
	"(amount <= :amount OR :amount IS NULL) AND "+
	"(:amountRange IS NOT NULL)")
	List<StockInfo> findByCategoryIdAndNameAndAmountOver(int categoryId,String name,int amount,String amountRange);

	/**
	 * 引数に合致する在庫情報を取得
	 * 
	 * @param categoryId
	 * ↑現状のSQL文では指定無しの場合に該当するリスト無しとなってしまう。
	 * @param centerName
	 * @param amount
	 * @return paramで個数をamount以下で検索した結果
	 */
	@Query("SELECT s FROM StockInfo s WHERE "+
	"(:categoryId IS NULL OR categoryId = :categoryId) AND "+ 
	"(:name = '' OR s.name LIKE %:name%) AND "+ 
	"(:amount IS NULL OR amount >= :amount) AND "+
	"(:amountRange IS NOT NULL)")
	List<StockInfo> findByCategoryIdAndNameAndAmountUnder(int categoryId,String name,int amount,String amountRange);

}
