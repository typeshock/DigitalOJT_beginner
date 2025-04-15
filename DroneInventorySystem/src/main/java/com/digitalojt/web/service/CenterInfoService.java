package com.digitalojt.web.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digitalojt.web.consts.NumberValidConsts;
import com.digitalojt.web.entity.CenterInfo;
import com.digitalojt.web.form.CenterInfoForm;
import com.digitalojt.web.repository.CenterInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * 在庫センター情報画面のサービスクラス
 *
 * @author ueno
 * 
 */
@Service
@RequiredArgsConstructor
public class CenterInfoService {

	/** センター情報テーブル リポジトリー */
	private final CenterInfoRepository repository;

	/**
	 * 在庫センター情報を全建検索で取得
	 * 
	 * @return
	 */
	public List<CenterInfo> getCenterInfoData() {
		return repository.findAll();
	}

	/**
	 * 引数に合致する在庫センター情報を取得
	 * 
	 * @param centerName
	 * @param region
	 * @return
	 */
	public List<CenterInfo> getCenterInfoData(String centerName, String region) {
		return repository.findByCenterNameAndRegion(centerName, region);
	}

	/**
	 * 在庫センター情報を登録
	 * 
	 * @param centerName
	 * @param postCode
	 * @param address
	 * @param phoneNumber
	 * @param managerName
	 * @param operationalStatus
	 * @param maxStorageCapacity
	 * @param currentStorageCapacity
	 * @param deleteFlag
	 * @param notes
	 * @param createDate
	 * @param updateDate
	 * @return
	 */
	@Transactional
	public void saveCenterInfoData(CenterInfoForm form) {

		// インスタンス化
		CenterInfo entity = new CenterInfo();

		//各データを格納する
		entity.setCenterName(form.getCenterName());
		entity.setPostCode(form.getPostCode());
		entity.setAddress(form.getAddress());
		entity.setPhoneNumber(form.getPhoneNumber());
		entity.setManagerName(form.getManagerName());
		entity.setOperationalStatus(form.getOperationalStatus());
		entity.setMaxStorageCapacity(form.getMaxStorageCapacity());
		entity.setCurrentStorageCapacity(form.getCurrentStorageCapacity());
		entity.setDeleteFlag(NumberValidConsts.DELETE_FLAG_REGISTER_NUMBER);
		entity.setNotes(form.getNotes());
		Timestamp currentTimestamp = Timestamp.valueOf(LocalDateTime.now());
		entity.setCreateDate(currentTimestamp);
		entity.setUpdateDate(currentTimestamp);

		//データの登録を実行
		repository.save(entity); 
	}

	/**
	 * 引数に合致する在庫センター情報を取得
	 * 
	 * @param centerId
	 * @return
	 */
	public CenterInfo getCenterInfoData(Integer centerId) {
		return repository.findByCenterId(centerId);
	}

	/**
	 * 在庫センター情報を更新
	 * 
	 * @param centerName
	 * @param postCode
	 * @param address
	 * @param phoneNumber
	 * @param managerName
	 * @param operationalStatus
	 * @param maxStorageCapacity
	 * @param currentStorageCapacity
	 * @param deleteFlag
	 * @param notes
	 * @param createDate
	 * @param updateDate
	 * @return
	 */
	@Transactional
	public void updateCenterInfoData(CenterInfoForm form) {

		// インスタンス化
		CenterInfo entity = new CenterInfo();
		
		//データベースの在庫センター情報を取得
		entity = repository.findByCenterId(form.getCenterId());

		//各データを格納する
		entity.setCenterName(form.getCenterName());
		entity.setPostCode(form.getPostCode());
		entity.setAddress(form.getAddress());
		entity.setPhoneNumber(form.getPhoneNumber());
		entity.setManagerName(form.getManagerName());
		entity.setOperationalStatus(form.getOperationalStatus());
		entity.setMaxStorageCapacity(form.getMaxStorageCapacity());
		entity.setCurrentStorageCapacity(form.getCurrentStorageCapacity());
		entity.setDeleteFlag(NumberValidConsts.DELETE_FLAG_REGISTER_NUMBER);
		entity.setNotes(form.getNotes());
		Timestamp currentTimestamp = Timestamp.valueOf(LocalDateTime.now());
		entity.setUpdateDate(currentTimestamp);

		//データの更新を実行
		repository.save(entity);
	}

	/**
	 * 在庫センター情報を削除
	 * 
	 * @param deleteFlag
	 * @param updateDate
	 * @return
	 */
	@Transactional
	public void deleteCenterInfoData(Integer centerId) {

		// インスタンス化
		CenterInfo entity = new CenterInfo();

		//データベースの在庫センター情報を取得
		entity = repository.findByCenterId(centerId);
		
		//在庫一覧とリンクしているセンター名があるか確認
		if(repository.count(centerId) > 0) {
			//在庫一覧とリンクしているセンター名がある場合、エラーを発生させて処理を中断する
			throw new Error();
		}

		//各データを格納する
		entity.setDeleteFlag(NumberValidConsts.DELETE_FLAG_DELETE_NUMBER);
		Timestamp currentTimestamp = Timestamp.valueOf(LocalDateTime.now());
		entity.setUpdateDate(currentTimestamp);

		//データの更新(論理削除)を実行
		repository.save(entity);
	}
}
