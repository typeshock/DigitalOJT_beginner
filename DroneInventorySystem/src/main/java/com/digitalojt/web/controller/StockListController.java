package com.digitalojt.web.controller;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.consts.UrlConsts;
import com.digitalojt.web.entity.CategoryInfo;
import com.digitalojt.web.entity.StockInfo;
import com.digitalojt.web.form.StockListForm;
import com.digitalojt.web.service.CategoryInfoService;
import com.digitalojt.web.service.StockListService;
import com.digitalojt.web.util.MessageManager;

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
public class StockListController extends AbstractController {

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
	 * @return
	 */
	@GetMapping(UrlConsts.STOCK_LIST)
	public String index(Model model) {
		try {
			// 分類情報画面に表示するデータを取得
			List<CategoryInfo> categoryInfoList = categoryInfoService.getCategoryInfoData();

			// 画面表示用に商品情報リストをセット
			model.addAttribute("categoryInfoList", categoryInfoList);

			// 在庫情報画面に表示するデータを取得
			List<StockInfo> stockList = stockListService.getStockListData();

			// 画面表示用に部品情報リストをセット
			model.addAttribute("stockList", stockList);

		} catch (NullPointerException categoryNullError) {
			//Nullエラー処理
			String errorMsg = MessageManager.getMessage(messageSource, ErrorMessage.LIST_EMPTY_ERROR_MESSAGE);
			model.addAttribute("errorMsg", errorMsg);

		} catch (DataAccessException categoryDbError) {
			//データベース接続エラー処理
			String errorMsg = MessageManager.getMessage(messageSource, ErrorMessage.DB_DISCONNECTED_ERROR_MESSAGE);
			model.addAttribute("errorMsg", errorMsg);

		} catch (Exception error) {
			//全ての例外処理
			String errorMsg = MessageManager.getMessage(messageSource, ErrorMessage.UNEXPECT_ERROR_MESSAGE);
			model.addAttribute("errorMsg", errorMsg);
		}

		return UrlConsts.STOCK_LIST_INDEX;
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
		try {
			// Valid項目チェック
			if (bindingResult.hasErrors()) {

				// エラーメッセージをプロパティファイルから取得
				String errorMsg = MessageManager.getMessage(messageSource, bindingResult.getGlobalError().getDefaultMessage());
				model.addAttribute("errorMsg", errorMsg);

				// 分類情報画面に表示するデータを取得
				List<CategoryInfo> categoryInfoList = categoryInfoService.getCategoryInfoData();

				// 画面表示用に商品情報リストをセット
				model.addAttribute("categoryInfoList", categoryInfoList);

				// 在庫情報画面に表示するデータを取得
				List<StockInfo> stockList = stockListService.getStockListData();

				// 画面表示用に部品情報リストをセット
				model.addAttribute("stockList", stockList);

				return UrlConsts.STOCK_LIST_INDEX;
			}

			// 分類情報画面に表示するデータを取得
			List<CategoryInfo> categoryInfoList = categoryInfoService.getCategoryInfoData();

			// 画面表示用に商品情報リストをセット
			model.addAttribute("categoryInfoList", categoryInfoList);

			// 在庫情報画面に表示するデータを取得
			List<StockInfo> stockList = stockListService.getStockListData(form.getCategoryId(), form.getName(), form.getAmount(), form.getAmountRange());

			// 画面表示用に部品情報リストをセット
			model.addAttribute("stockList", stockList);

		} catch (NullPointerException categoryNullError) {
			//Nullエラー処理
			String errorMsg = MessageManager.getMessage(messageSource, ErrorMessage.LIST_EMPTY_ERROR_MESSAGE);
			model.addAttribute("errorMsg", errorMsg);

		} catch (DataAccessException categoryDbError) {
			//データベース接続エラー処理
			String errorMsg = MessageManager.getMessage(messageSource, ErrorMessage.DB_DISCONNECTED_ERROR_MESSAGE);
			model.addAttribute("errorMsg", errorMsg);

		} catch (Exception error) {
			//全ての例外処理
			String errorMsg = MessageManager.getMessage(messageSource, ErrorMessage.UNEXPECT_ERROR_MESSAGE);
			model.addAttribute("errorMsg", errorMsg);
		}

		return UrlConsts.STOCK_LIST_INDEX;
	}
}
