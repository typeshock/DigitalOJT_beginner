package com.digitalojt.web.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.consts.Region;
import com.digitalojt.web.consts.SystemMessage;
import com.digitalojt.web.consts.UrlConsts;
import com.digitalojt.web.entity.CenterInfo;
import com.digitalojt.web.form.CenterInfoForm;
import com.digitalojt.web.service.CenterInfoService;
import com.digitalojt.web.util.MessageManager;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * 在庫センター情報画面のコントローラークラス
 * 
 * @author ueno
 *
 */
@Controller
@RequiredArgsConstructor
public class CenterInfoController extends AbstractController {

	/** センター情報 サービス */
	private final CenterInfoService centerInfoService;

	/** メッセージソース */
	private final MessageSource messageSource;

	/**
	 * 初期表示
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(UrlConsts.CENTER_INFO)
	public String index(Model model, @ModelAttribute("systemMsg") String systemMsg) {

		try {
			// 在庫センター情報画面に表示するデータを取得
			List<CenterInfo> centerInfoList = centerInfoService.getCenterInfoData();

			// 画面表示用に商品情報リストをセット
			model.addAttribute("centerInfoList", centerInfoList);

			// 都道府県Enumをリストに変換
			List<Region> regions = Arrays.asList(Region.values());

			// 都道府県プルダウン情報をセット
			model.addAttribute("regions", regions);

			// 別画面のメッセージをセット
			model.addAttribute("systemMsg", systemMsg);

			return UrlConsts.CENTER_INFO + "/index";

		} catch (NullPointerException categoryNullError) {
			//Nullエラー処理
			String errorMsg = MessageManager.getMessage(messageSource, ErrorMessage.LIST_EMPTY_ERROR_MESSAGE);
			model.addAttribute("errorMsg", errorMsg);
		} catch (Exception error) {
			//全ての例外処理
			String errorMsg = MessageManager.getMessage(messageSource, ErrorMessage.UNEXPECT_ERROR_MESSAGE);
			model.addAttribute("errorMsg", errorMsg);
		}
		return UrlConsts.CENTER_INFO + "/index";

	}

	/**
	 * 検索結果表示
	 * 
	 * @param model
	 * @param form
	 * @return
	 */
	@PostMapping(UrlConsts.CENTER_INFO_SEARCH)
	public String search(Model model, @Valid CenterInfoForm form, BindingResult bindingResult) {

		try {

			// Valid項目チェック
			if (bindingResult.hasErrors()) {

				// エラーメッセージをプロパティファイルから取得
				String errorMsg = MessageManager.getMessage(messageSource,
						bindingResult.getGlobalError().getDefaultMessage());
				model.addAttribute("errorMsg", errorMsg);

				// 都道府県Enumをリストに変換
				List<Region> regions = Arrays.asList(Region.values());

				// 都道府県プルダウン情報をセット
				model.addAttribute("regions", regions);

				return UrlConsts.CENTER_INFO + "/index";
			}

			// 在庫センター情報画面に表示するデータを取得
			List<CenterInfo> centerInfoList = centerInfoService.getCenterInfoData(form.getCenterName(), form.getRegion());

			// 画面表示用に商品情報リストをセット
			model.addAttribute("centerInfoList", centerInfoList);

			// 都道府県Enumをリストに変換
			List<Region> regions = Arrays.asList(Region.values());

			// 都道府県プルダウン情報をセット
			model.addAttribute("regions", regions);

			return "admin/centerInfo/index";

		} catch (NullPointerException categoryNullError) {
			//Nullエラー処理
			String errorMsg = MessageManager.getMessage(messageSource, ErrorMessage.LIST_EMPTY_ERROR_MESSAGE);
			model.addAttribute("errorMsg", errorMsg);

		} catch (Exception error) {
			//全ての例外処理
			String errorMsg = MessageManager.getMessage(messageSource, ErrorMessage.UNEXPECT_ERROR_MESSAGE);
			model.addAttribute("errorMsg", errorMsg);

		}
		return UrlConsts.CENTER_INFO + "/index";
	}

	/**
	 * 登録画面表示
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(UrlConsts.CENTER_INFO_REGISTER)
	public String getRegister(Model model) {

		try {

			// 在庫センター情報画面に表示するデータを取得
			List<CenterInfo> centerInfoList = null;

			// 画面表示用に商品情報リストをセット
			model.addAttribute("centerInfoList", centerInfoList);

			return UrlConsts.CENTER_INFO_REGISTER;

		} catch (NullPointerException categoryNullError) {
			//Nullエラー処理
			String errorMsg = MessageManager.getMessage(messageSource, ErrorMessage.LIST_EMPTY_ERROR_MESSAGE);
			model.addAttribute("errorMsg", errorMsg);

		} catch (Exception error) {
			//全ての例外処理
			String errorMsg = MessageManager.getMessage(messageSource, ErrorMessage.UNEXPECT_ERROR_MESSAGE);
			model.addAttribute("errorMsg", errorMsg);

		}
		return UrlConsts.CENTER_INFO_REGISTER;
	}

	/**
	 * 登録処理
	 * 
	 * @param model
	 * @param form
	 * @return
	 */
	@PostMapping(UrlConsts.CENTER_INFO_REGISTER)
	public String postRegister(Model model, @Valid CenterInfoForm form, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		try {
			// Valid項目チェック
			if (bindingResult.hasErrors()) {

				// エラーメッセージをプロパティファイルから取得
				String errorMsg = MessageManager.getMessage(messageSource, bindingResult.getGlobalError().getDefaultMessage());
				model.addAttribute("errorMsg", errorMsg);

				return UrlConsts.CENTER_INFO_REGISTER;

			}

			CenterInfo entity = new CenterInfo();

			centerInfoService.getCenterInfoData(form,entity);

			// データの登録処理を実行する
			centerInfoService.saveCenterInfoData(entity);

			// 登録成功時のメッセージを設定する
			String systemMsg = MessageManager.getMessage(messageSource, SystemMessage.CENTERINFO_REGISTER_SUCCESS);
			redirectAttributes.addFlashAttribute("systemMsg", systemMsg);

			//①登録完了時に在庫センター情報画面にリダイレクト
			return "redirect:" + UrlConsts.CENTER_INFO;

		} catch (DataAccessException DataAccessError) {
			//データ登録時のエラーメッセージ処理
			String systemMsg = MessageManager.getMessage(messageSource, ErrorMessage.TRANSACTION_ERROR_MESSAGE);
			redirectAttributes.addFlashAttribute("systemMsg", systemMsg);

			//データ登録中に異常が発生した場合、在庫センター情報画面へ遷移する
			return "redirect:" + UrlConsts.CENTER_INFO;

		} catch (NullPointerException categoryNullError) {
			//Nullエラー処理
			String errorMsg = MessageManager.getMessage(messageSource, ErrorMessage.LIST_EMPTY_ERROR_MESSAGE);
			model.addAttribute("errorMsg", errorMsg);
			return UrlConsts.CENTER_INFO_REGISTER;

		} catch (Exception error) {
			//全ての例外処理
			String errorMsg = MessageManager.getMessage(messageSource, ErrorMessage.UNEXPECT_ERROR_MESSAGE);
			model.addAttribute("errorMsg", errorMsg);
			return UrlConsts.CENTER_INFO_REGISTER;
		}

	}

}
