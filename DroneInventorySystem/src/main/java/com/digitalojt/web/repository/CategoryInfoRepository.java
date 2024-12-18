package com.digitalojt.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitalojt.web.entity.CategoryInfo;

/**
 * 分類情報テーブルリポジトリー
 *
 * @author ueno
 * 
 */
@Repository
public interface CategoryInfoRepository extends JpaRepository<CategoryInfo, Integer> {

	/**
	 * 引数に合致する分類情報を取得
	 * 
	 * @param categoryName
	 * @return paramで検索した結果
	 */
	@Query("SELECT s FROM CategoryInfo s WHERE " +
			"(:categoryName = '' OR s.categoryName LIKE %:categoryName%)")
	List<CategoryInfo> findByCategoryNameAndPartsRegionAndStorageCapacity(
			String categoryName);
}
