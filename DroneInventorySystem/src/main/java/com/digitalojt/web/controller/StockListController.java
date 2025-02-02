package com.digitalojt.web.controller;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.digitalojt.web.consts.UrlConsts;
import com.digitalojt.web.entity.CategoryInfo;
import com.digitalojt.web.entity.StockInfo;
import com.digitalojt.web.form.StockListForm;
import com.digitalojt.web.service.CategoryInfoService;
import com.digitalojt.web.service.StockListService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * 在庫情報画面コントローラークラス
 * 
 * @author ueno
 *
 */
@Controller
@RequiredArgsConstructor
public class StockListController{

	/** 在庫情報 サービス */
	private final StockListService stockListService;

	/** 分類情報 サービス */
	private final CategoryInfoService categoryInfoService;

	/** メッセージソース */
	private final MessageSource messageSource;

	/**
	 * 初期表示
	 * 
	 * @param model
	 * @param form
	 * @return
	 */
	@GetMapping(UrlConsts.STOCK_LIST)
	public String index(Model model, StockListForm form) {

		// 在庫情報画面に表示するデータを取得
		List<StockInfo> stockList = stockListService.getStockListData();

		for(StockInfo s : stockList) {
		    System.out.println(s.getName());
		}

		// 画面表示用に部品情報リストをセット
		model.addAttribute("stockList", stockList);

		// 分類情報画面に表示するデータを取得
		List<CategoryInfo> categoryInfoList = categoryInfoService.getCategoryInfoData();

		// 画面表示用に商品情報リストをセット
		model.addAttribute("categoryInfoList", categoryInfoList);

		return "admin/stockList/index";
	}

	/**
	 * 検索結果表示
	 * 
	 * @param model
	 * @param form
	 * @return
	 */
	@PostMapping(UrlConsts.STOCK_LIST_SEARCH)
	public String search(Model model, @Valid StockListForm form, BindingResult bindingResult) {

		// 分類情報画面に表示するデータを取得
		List<CategoryInfo> categoryInfoList = categoryInfoService.getCategoryInfoData();
		
		// 画面表示用に商品情報リストをセット
		model.addAttribute("categoryInfoList", categoryInfoList);
		// 在庫情報画面に表示するデータを取得
		List<StockInfo> stockList = stockListService.getStockListData(form.getCategoryId(),form.getName());

		for(StockInfo s : stockList) {
		    System.out.println(s.getName());
		}

		// 画面表示用に部品情報リストをセット
		model.addAttribute("stockList", stockList);

		return "admin/stockList/index";
	}
}
