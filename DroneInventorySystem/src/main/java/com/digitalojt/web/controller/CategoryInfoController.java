package com.digitalojt.web.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.consts.PartsRegion;
import com.digitalojt.web.consts.UrlConsts;
import com.digitalojt.web.entity.CategoryInfo;
import com.digitalojt.web.form.CategoryInfoForm;
import com.digitalojt.web.service.CategoryInfoService;
import com.digitalojt.web.util.MessageManager;

import jakarta.validation.Valid;
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

		try {
			// 分類情報画面に表示するデータを取得
			List<CategoryInfo> categoryInfoList = categoryInfoService.getCategoryInfoData();

			// 画面表示用に部品情報リストをセット
			model.addAttribute("categoryInfoList", categoryInfoList);

			// 部品Enumをリストに変換
			List<PartsRegion> partsRegions = Arrays.asList(PartsRegion.values());

			// 部品プルダウン情報をセット
			model.addAttribute("partsRegions", partsRegions);

		} catch (NullPointerException categoryNullError) {
			//Nullエラー処理
			String errorMsg = ErrorMessage.LIST_EMPTY_ERROR_MESSAGE;
			model.addAttribute("errorMsg", errorMsg);

		} catch (DataAccessException categoryDbError) {
			//データベース接続エラー処理
			String errorMsg = ErrorMessage.DB_DISCONNECTED_ERROR_MESSAGE;
			model.addAttribute("errorMsg", errorMsg);

		} catch (Exception error) {
			//全ての例外処理
			String errorMsg = ErrorMessage.UNEXPECT_ERROR_MESSAGE;
			model.addAttribute("errorMsg", errorMsg);
		}

		return "admin/categoryInfo/index";
	}

	/**
	 * 検索結果表示
	 * 
	 * @param model
	 * @param form
	 * @return
	 */
	@PostMapping(UrlConsts.CATEGORY_INFO_SEARCH)
	public String search(Model model, @Valid CategoryInfoForm form, BindingResult bindingResult) {

		try {
			// Valid項目チェック
			if (bindingResult.hasErrors()) {

				// エラーメッセージをプロパティファイルから取得
				String errorMsg = MessageManager.getMessage(messageSource,bindingResult.getGlobalError().getDefaultMessage());
				model.addAttribute("errorMsg", errorMsg);

				// 部品Enumをリストに変換
				List<PartsRegion> partsRegions = Arrays.asList(PartsRegion.values());

				// 部品プルダウン情報をセット
				model.addAttribute("partsRegions", partsRegions);

				return "admin/categoryInfo/index";
			}

			// 分類情報画面に表示するデータを取得
			List<CategoryInfo> categoryInfoList = categoryInfoService.getCategoryInfoData(form.getCategoryName());

			// 画面表示用に商品情報リストをセット
			model.addAttribute("categoryInfoList", categoryInfoList);

			// 部品Enumをリストに変換
			List<PartsRegion> partsRegions = Arrays.asList(PartsRegion.values());

			// 部品プルダウン情報をセット
			model.addAttribute("partsRegions", partsRegions);

		} catch (NullPointerException categoryNullError) {
			//Nullエラー処理
			String errorMsg = ErrorMessage.LIST_EMPTY_ERROR_MESSAGE;
			model.addAttribute("errorMsg", errorMsg);

		} catch (DataAccessException categoryDbError) {
			//データベース接続エラー処理
			String errorMsg = ErrorMessage.DB_DISCONNECTED_ERROR_MESSAGE;
			model.addAttribute("errorMsg", errorMsg);

		} catch (Exception error) {
			//全ての例外処理
			String errorMsg = ErrorMessage.UNEXPECT_ERROR_MESSAGE;
			model.addAttribute("errorMsg", errorMsg);

		}

		return "admin/categoryInfo/index";
	}
}
