package com.digitalojt.web.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.digitalojt.web.consts.PartsRegion;
import com.digitalojt.web.consts.UrlConsts;
import com.digitalojt.web.service.CategoryInfoService;

import lombok.RequiredArgsConstructor;

/**
 * 分類情報管理画面のコントローラークラス
 * 
 * @author ueno
 *
 */
 

@Controller
@RequiredArgsConstructor
public class CategoryInfoController {
	
	/** 分類情報 サービス */
	private final CategoryInfoService categoryInfoService;

	/** メッセージソース */
	private final MessageSource messageSource;
		
	/**
	 * 初期表示
	 * 
	 * @param model
	 * @return
	 */
	
	@GetMapping(UrlConsts.CATEGORY_INFO)
	public String index(Model model) {

		// 分類情報画面に表示するデータを取得
		/*List<CategoryInfo> categoryInfoList = categoryInfoService.getCategoryInfoData();

		// 画面表示用に部品情報リストをセット
		model.addAttribute("categoryInfoList", categoryInfoList);*/

		// 部品Enumをリストに変換
		List<PartsRegion> partsRegions = Arrays.asList(PartsRegion.values());

		// 部品プルダウン情報をセット
		model.addAttribute("partsRegions", partsRegions);

		return "admin/categoryInfo/index";
	}


}

