package com.digitalojt.web.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

		// HelloWorldに表示するデータを取得
		String helloWorld = categoryInfoService.getHelloWorld();

		// HelloWorldに商品情報リストをセット
		model.addAttribute("helloWorld", helloWorld);


		return "admin/categoryInfo/index";
	}

}

