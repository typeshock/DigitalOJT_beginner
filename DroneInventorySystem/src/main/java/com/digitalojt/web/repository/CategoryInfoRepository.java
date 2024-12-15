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
	 * @param category_name
	 * @param partsRegion
	 * @return paramで検索した結果
	 */
	@Query("SELECT s FROM CategoryInfo s WHERE " +
			"(:category_name = '' OR s.category_name LIKE %:category_name%) AND " +
			"(:partsRegion = '' OR s.partsAddress LIKE %:partsRegion%)")
	List<CategoryInfo> findByCategoryNameAndPartsRegionAndStorageCapacity(
			String category_name,
			String partsRegion);
}
