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
	 * @return paramで検索した結果
	 */
	@Query("SELECT s FROM StockInfo s WHERE "+
	"(:categoryId IS NULL OR categoryId = :categoryId) AND "+ 
	"(:name = '' OR s.name LIKE %:name%)")
	List<StockInfo> findByCategoryIdAndName(int categoryId,String name);

}
