package com.digitalojt.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalojt.web.entity.CategoryInfo;

/**
 * センター情報テーブルリポジトリー
 *
 * @author ueno
 * 
 */
@Repository
public interface CategoryInfoRepository extends JpaRepository<CategoryInfo, Integer> {

	/**
	 * 引数に合致する在庫センター情報を取得
	 * 
	 * @param categoryName
	 * @param partsRegion
	 * @return paramで検索した結果
	 */
	/*@Query("SELECT s FROM CategoryInfo s WHERE " +
			"(:partsRegion = '' OR s.address LIKE %:partsRegion%) ")			
	List<CategoryInfo> findByCategoryNameAndPartsRegionAndStorageCapacity(
			String categoryName,
			String partsRegion);*/
}
