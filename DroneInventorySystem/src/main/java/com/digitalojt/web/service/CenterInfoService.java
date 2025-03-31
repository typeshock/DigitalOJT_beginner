package com.digitalojt.web.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	 * @param storageCapacityFrom 
	 * @param storageCapacityTo
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
	    entity.setDeleteFlag(form.getDeleteFlagRegister());
	    entity.setNotes(form.getNotes());
	    Timestamp currentTimestamp = Timestamp.valueOf(LocalDateTime.now());
	    entity.setCreateDate(currentTimestamp);
	    entity.setUpdateDate(currentTimestamp);

	    //データの登録を実行
        repository.save(entity); 
	}
	
	/**
	 * 在庫センター情報を登録
	 * 
	 * @param entity
	 * @return
	 */
	@Transactional
	public void saveCenterInfoData(CenterInfo entity) {
        repository.save(entity); 
	}
}
