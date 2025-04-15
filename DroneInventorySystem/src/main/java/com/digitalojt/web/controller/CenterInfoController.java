package com.digitalojt.web.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.consts.LogMessage;
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

	Logger logger = LoggerFactory.getLogger(CenterInfoController.class);

	

	/**
	 * 初期表示
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(UrlConsts.CENTER_INFO)
	public String index(Model model, @ModelAttribute("systemMsg") String systemMsg) {

		try {
			//ログ：開始
			logger.info(LogMessage.CENTER_INFO_VIEW + LogMessage.INITIAL_DISPLAY + LogMessage.PROCESS_START);
			
			// 在庫センター情報画面に表示するデータを取得
			List<CenterInfo> centerInfoList = centerInfoService.getCenterInfoData();

			//デバッグ：データベースからデータを取得
			logger.debug(LogMessage.CENTER_INFO_VIEW + LogMessage.DEBUG_GET_DATE);

			// 画面表示用に商品情報リストをセット
			model.addAttribute("centerInfoList", centerInfoList);

			//デバッグ：画面表示用のリストをセット
			logger.debug(LogMessage.CENTER_INFO_VIEW + LogMessage.DEBUG_ADDATTRIBUTE);

			// 都道府県Enumをリストに変換
			List<Region> regions = Arrays.asList(Region.values());

			// 都道府県プルダウン情報をセット
			model.addAttribute("regions", regions);

			//デバッグ：画面表示用のリストをセット
			logger.debug(LogMessage.CENTER_INFO_VIEW + LogMessage.DEBUG_ADDATTRIBUTE);

			// 別画面のメッセージをセット
			model.addAttribute("systemMsg", systemMsg);

			//デバッグ：画面表示用のリストをセット
			logger.debug(LogMessage.CENTER_INFO_VIEW + LogMessage.DEBUG_ADDATTRIBUTE);

			//ログ：終了
			logger.info(LogMessage.CENTER_INFO_VIEW + LogMessage.INITIAL_DISPLAY + LogMessage.PROCESS_END);

			return UrlConsts.CENTER_INFO + "/index";

		} catch (NullPointerException categoryNullError) {
			//Nullエラー処理
			String errorMsg = MessageManager.getMessage(messageSource, ErrorMessage.LIST_EMPTY_ERROR_MESSAGE);
			model.addAttribute("errorMsg", errorMsg);

			//ログ：エラー
			logger.error(LogMessage.CENTER_INFO_VIEW + LogMessage.INITIAL_DISPLAY + ErrorMessage.LIST_EMPTY_ERROR_MESSAGE);
		} catch (Exception error) {
			//全ての例外処理
			String errorMsg = MessageManager.getMessage(messageSource, ErrorMessage.UNEXPECT_ERROR_MESSAGE);
			model.addAttribute("errorMsg", errorMsg);

			//ログ：エラー
			logger.error(LogMessage.CENTER_INFO_VIEW + LogMessage.INITIAL_DISPLAY + ErrorMessage.UNEXPECT_ERROR_MESSAGE);
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

			//ログ：開始
			logger.info(LogMessage.CENTER_INFO_SERCH_VIEW + LogMessage.SERCH + LogMessage.PROCESS_START);

			
			// Valid項目チェック
			if (bindingResult.hasErrors()) {

				// エラーメッセージをプロパティファイルから取得
				String errorMsg = MessageManager.getMessage(messageSource, bindingResult.getGlobalError().getDefaultMessage());
				model.addAttribute("errorMsg", errorMsg);

				//警告：エラーメッセージ発生
				logger.warn(LogMessage.CENTER_INFO_SERCH_VIEW + LogMessage.WARN_ERROR_MESSAGE);

				// 都道府県Enumをリストに変換
				List<Region> regions = Arrays.asList(Region.values());

				// 都道府県プルダウン情報をセット
				model.addAttribute("regions", regions);

				//デバッグ：画面表示用のリストをセット
				logger.debug(LogMessage.CENTER_INFO_SERCH_VIEW + LogMessage.DEBUG_ADDATTRIBUTE);


				return UrlConsts.CENTER_INFO + "/index";
			}

			// 在庫センター情報画面に表示するデータを取得
			List<CenterInfo> centerInfoList = centerInfoService.getCenterInfoData(form.getCenterName(), form.getRegion());

			//デバッグ：データベースからデータを取得
			logger.debug(LogMessage.CENTER_INFO_SERCH_VIEW + LogMessage.DEBUG_GET_DATE);

			// 画面表示用に商品情報リストをセット
			model.addAttribute("centerInfoList", centerInfoList);

			//デバッグ：画面表示用のリストをセット
			logger.debug(LogMessage.CENTER_INFO_SERCH_VIEW + LogMessage.DEBUG_ADDATTRIBUTE);

			// 都道府県Enumをリストに変換
			List<Region> regions = Arrays.asList(Region.values());

			// 都道府県プルダウン情報をセット
			model.addAttribute("regions", regions);

			//デバッグ：画面表示用のリストをセット
			logger.debug(LogMessage.CENTER_INFO_SERCH_VIEW + LogMessage.DEBUG_ADDATTRIBUTE);

			//ログ：終了
			logger.info(LogMessage.CENTER_INFO_SERCH_VIEW + LogMessage.SERCH + LogMessage.PROCESS_END);

			return "admin/centerInfo/index";

		} catch (NullPointerException categoryNullError) {
			//Nullエラー処理
			String errorMsg = MessageManager.getMessage(messageSource, ErrorMessage.LIST_EMPTY_ERROR_MESSAGE);
			model.addAttribute("errorMsg", errorMsg);

			//ログ：エラー
			logger.error(LogMessage.CENTER_INFO_SERCH_VIEW + LogMessage.SERCH + ErrorMessage.LIST_EMPTY_ERROR_MESSAGE);

		} catch (Exception error) {
			//全ての例外処理
			String errorMsg = MessageManager.getMessage(messageSource, ErrorMessage.UNEXPECT_ERROR_MESSAGE);
			model.addAttribute("errorMsg", errorMsg);

			//ログ：エラー
			logger.error(LogMessage.CENTER_INFO_SERCH_VIEW + LogMessage.SERCH + ErrorMessage.UNEXPECT_ERROR_MESSAGE);

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
	public String getRegister() {

		//ログ：開始
		logger.info(LogMessage.CENTER_INFO_REGISTER_VIEW + LogMessage.INITIAL_DISPLAY + LogMessage.PROCESS_START);

		//ログ：終了
		logger.info(LogMessage.CENTER_INFO_REGISTER_VIEW + LogMessage.INITIAL_DISPLAY + LogMessage.PROCESS_END);

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
	public String postRegister(Model model, @Valid CenterInfoForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		try {
			//ログ：開始
			logger.info(LogMessage.CENTER_INFO_REGISTER_VIEW + LogMessage.REGISTER + LogMessage.PROCESS_START);

			// Valid項目チェック
			if (bindingResult.hasErrors()) {

				// エラーメッセージをプロパティファイルから取得
				redirectAttributes.addFlashAttribute("errorMsg", MessageManager.getMessage(messageSource, bindingResult.getGlobalError().getDefaultMessage()));

				//警告：エラーメッセージ発生
				logger.warn(LogMessage.CENTER_INFO_REGISTER_VIEW + LogMessage.WARN_ERROR_MESSAGE);

				return "redirect:" + UrlConsts.CENTER_INFO_REGISTER;

			}

			//デバッグ：登録処理実行
			logger.debug(LogMessage.CENTER_INFO_REGISTER_VIEW + LogMessage.DEBUG_SAVE_REGISTER_START);

			// データの登録処理を実行する
			centerInfoService.saveCenterInfoData(form);

			//デバッグ：登録処理完了
			logger.debug(LogMessage.CENTER_INFO_REGISTER_VIEW + LogMessage.DEBUG_SAVE_REGISTER_END);

			// 登録成功時のメッセージを設定する
			String systemMsg = MessageManager.getMessage(messageSource, SystemMessage.CENTERINFO_REGISTER_SUCCESS);
			redirectAttributes.addFlashAttribute("systemMsg", systemMsg);

			//ログ：終了
			logger.info(LogMessage.CENTER_INFO_REGISTER_VIEW + LogMessage.REGISTER + LogMessage.PROCESS_END);

			// 登録完了時に在庫センター情報画面にリダイレクト
			return "redirect:" + UrlConsts.CENTER_INFO;

		} catch (NullPointerException categoryNullError) {
			//Nullエラー処理
			String errorMsg = MessageManager.getMessage(messageSource, ErrorMessage.LIST_EMPTY_ERROR_MESSAGE);
			model.addAttribute("errorMsg", errorMsg);

			//ログ：エラー
			logger.error(LogMessage.CENTER_INFO_REGISTER_VIEW + LogMessage.REGISTER + ErrorMessage.LIST_EMPTY_ERROR_MESSAGE);

		} catch (Exception error) {
			//全ての例外処理
			String errorMsg = MessageManager.getMessage(messageSource, ErrorMessage.UNEXPECT_ERROR_MESSAGE);
			model.addAttribute("errorMsg", errorMsg);

			//ログ：エラー
			logger.error(LogMessage.CENTER_INFO_REGISTER_VIEW + LogMessage.REGISTER + ErrorMessage.UNEXPECT_ERROR_MESSAGE);
		}
		return UrlConsts.CENTER_INFO_REGISTER;

	}

	/**
	 *更新画面表示
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(UrlConsts.CENTER_INFO_UPDATE+"/{centerId}")
	public String getUpdate(Model model, @PathVariable("centerId") Integer centerId) {

		try {
			//ログ：開始
			logger.info(LogMessage.CENTER_INFO_UPDATE_VIEW + LogMessage.INITIAL_DISPLAY + LogMessage.PROCESS_START);

			// 在庫センター情報画面に表示するデータを取得
			CenterInfo centerInfoList = centerInfoService.getCenterInfoData(centerId);

			//デバッグ：データベースからデータを取得
			logger.debug(LogMessage.CENTER_INFO_UPDATE_VIEW + LogMessage.DEBUG_GET_DATE);

			// 画面表示用に商品情報リストをセット
			model.addAttribute("centerInfoList", centerInfoList);

			//デバッグ：画面表示用のリストをセット
			logger.debug(LogMessage.CENTER_INFO_UPDATE_VIEW + LogMessage.DEBUG_ADDATTRIBUTE);

			//ログ：終了
			logger.info(LogMessage.CENTER_INFO_UPDATE_VIEW + LogMessage.INITIAL_DISPLAY + LogMessage.PROCESS_END);

			return UrlConsts.CENTER_INFO_UPDATE;

		} catch (Exception error) {
			//全ての例外処理
			String errorMsg = MessageManager.getMessage(messageSource, ErrorMessage.UNEXPECT_ERROR_MESSAGE);
			model.addAttribute("errorMsg", errorMsg);

			//ログ：エラー
			logger.error(LogMessage.CENTER_INFO_UPDATE_VIEW + LogMessage.INITIAL_DISPLAY + ErrorMessage.UNEXPECT_ERROR_MESSAGE);

			return UrlConsts.CENTER_INFO_UPDATE;
		}
	}

	/**
	 * 更新処理
	 * 
	 * @param model
	 * @param form
	 * @return
	 */
	@PatchMapping(UrlConsts.CENTER_INFO_UPDATE)
	public String postUpdate(Model model, @Valid CenterInfoForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		try {

			//ログ：開始
			logger.info(LogMessage.CENTER_INFO_UPDATE_VIEW + LogMessage.UPDATE + LogMessage.PROCESS_START);

			// Valid項目チェック
			if (bindingResult.hasErrors()) {

				// エラーメッセージをプロパティファイルから取得
				redirectAttributes.addFlashAttribute("errorMsg", MessageManager.getMessage(messageSource, bindingResult.getGlobalError().getDefaultMessage()));

				//警告：エラーメッセージ発生
				logger.warn(LogMessage.CENTER_INFO_UPDATE_VIEW + LogMessage.WARN_ERROR_MESSAGE);

				return "redirect:" + UrlConsts.CENTER_INFO_UPDATE+"/"+form.getCenterId();

			}

			//デバッグ：更新処理実行
			logger.debug(LogMessage.CENTER_INFO_UPDATE_VIEW + LogMessage.DEBUG_SAVE_UPDATE_START);

			// データの更新処理を実行する
			centerInfoService.updateCenterInfoData(form);

			//デバッグ：更新処理完了
			logger.debug(LogMessage.CENTER_INFO_UPDATE_VIEW + LogMessage.DEBUG_SAVE_UPDATE_END);

			// 更新成功時のメッセージを設定する
			redirectAttributes.addFlashAttribute("systemMsg", MessageManager.getMessage(messageSource, SystemMessage.CENTERINFO_UPDATE_SUCCESS));

			//ログ：終了
			logger.info(LogMessage.CENTER_INFO_UPDATE_VIEW + LogMessage.UPDATE + LogMessage.PROCESS_END);

			// 更新完了時に在庫センター情報画面にリダイレクト
			return "redirect:" + UrlConsts.CENTER_INFO;

		} catch (Error linkingError) {
			//エラー処理
			redirectAttributes.addFlashAttribute("errorMsg", MessageManager.getMessage(messageSource, ErrorMessage.CENTERINFO_DELETE_LINKING_ERROR_MESSAGE));

			//ログ：エラー
			logger.error(LogMessage.CENTER_INFO_UPDATE_VIEW + LogMessage.INITIAL_DISPLAY + ErrorMessage.CENTERINFO_DELETE_LINKING_ERROR_MESSAGE);

		} catch (NullPointerException NullError) {
			//Nullエラー処理
			redirectAttributes.addFlashAttribute("errorMsg", MessageManager.getMessage(messageSource, ErrorMessage.LIST_EMPTY_ERROR_MESSAGE));

			//ログ：エラー
			logger.error(LogMessage.CENTER_INFO_UPDATE_VIEW + LogMessage.INITIAL_DISPLAY + ErrorMessage.LIST_EMPTY_ERROR_MESSAGE);

		} catch (Exception error) {
			//全ての例外処理
			redirectAttributes.addFlashAttribute("errorMsg", MessageManager.getMessage(messageSource, ErrorMessage.UNEXPECT_ERROR_MESSAGE));

			//ログ：エラー
			logger.error(LogMessage.CENTER_INFO_UPDATE_VIEW + LogMessage.INITIAL_DISPLAY + ErrorMessage.UNEXPECT_ERROR_MESSAGE);

		} 

		return "redirect:" + UrlConsts.CENTER_INFO_UPDATE+"/"+form.getCenterId();
	}

	/**
	 *削除画面表示
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(UrlConsts.CENTER_INFO_DELETE+"/{centerId}")
	public String getDelete(Model model, @PathVariable("centerId") Integer centerId) {

		try {
			//ログ：開始
			logger.info(LogMessage.CENTER_INFO_DELETE_VIEW + LogMessage.INITIAL_DISPLAY + LogMessage.PROCESS_START);
			
			// 在庫センター情報画面に表示するデータを取得
			CenterInfo centerInfoList = centerInfoService.getCenterInfoData(centerId);

			//デバッグ：データベースからデータを取得
			logger.debug(LogMessage.CENTER_INFO_DELETE_VIEW + LogMessage.DEBUG_GET_DATE);

			// 画面表示用に商品情報リストをセット
			model.addAttribute("centerInfoList", centerInfoList);

			//デバッグ：画面表示用のリストをセット
			logger.debug(LogMessage.CENTER_INFO_DELETE_VIEW + LogMessage.DEBUG_ADDATTRIBUTE);

			//ログ：終了
			logger.info(LogMessage.CENTER_INFO_DELETE_VIEW + LogMessage.INITIAL_DISPLAY + LogMessage.PROCESS_END);

			return UrlConsts.CENTER_INFO_DELETE;

		} catch (Exception error) {
			//全ての例外処理
			String errorMsg = MessageManager.getMessage(messageSource, ErrorMessage.UNEXPECT_ERROR_MESSAGE);
			model.addAttribute("errorMsg", errorMsg);

			//ログ：エラー
			logger.error(LogMessage.CENTER_INFO_DELETE_VIEW + LogMessage.INITIAL_DISPLAY + ErrorMessage.UNEXPECT_ERROR_MESSAGE);

			return UrlConsts.CENTER_INFO_DELETE;
		}

	}

	/**
	 * 削除処理
	 * 
	 * @param model
	 * @param form
	 * @return
	 */
	@PatchMapping(UrlConsts.CENTER_INFO_DELETE)
	public String postDelete(@ModelAttribute("centerId") Integer centerId, Model model, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		try {
			//ログ：開始
			logger.info(LogMessage.CENTER_INFO_DELETE_VIEW + LogMessage.DELETE + LogMessage.PROCESS_START);

			// Valid項目チェック
			if (bindingResult.hasErrors()) {

				// エラーメッセージをプロパティファイルから取得
				redirectAttributes.addFlashAttribute("errorMsg", MessageManager.getMessage(messageSource, bindingResult.getGlobalError().getDefaultMessage()));

				//警告：エラーメッセージ発生
				logger.warn(LogMessage.CENTER_INFO_DELETE_VIEW + LogMessage.WARN_ERROR_MESSAGE);

				return "redirect:" + UrlConsts.CENTER_INFO_DELETE+"/"+centerId;

			}

			//デバッグ：削除処理実行
			logger.debug(LogMessage.CENTER_INFO_DELETE_VIEW + LogMessage.DEBUG_SAVE_DELETE_START);

			// データの更新(論理削除)処理を実行する
			centerInfoService.deleteCenterInfoData(centerId);

			//デバッグ：削除処理完了
			logger.debug(LogMessage.CENTER_INFO_DELETE_VIEW + LogMessage.DEBUG_SAVE_DELETE_END);

			// 削除成功時のメッセージを設定する
			redirectAttributes.addFlashAttribute("systemMsg", MessageManager.getMessage(messageSource, SystemMessage.CENTERINFO_DELETE_SUCCESS));

			//ログ：終了
			logger.info(LogMessage.CENTER_INFO_DELETE_VIEW + LogMessage.DELETE + LogMessage.PROCESS_END);

			// 削除完了時に在庫センター情報画面にリダイレクト
			return "redirect:" + UrlConsts.CENTER_INFO;

		}catch(Error error) {
			//在庫一覧でセンター名が使用されている際のエラー処理
			redirectAttributes.addFlashAttribute("errorMsg", MessageManager.getMessage(messageSource, ErrorMessage.CENTERINFO_DELETE_LINKING_ERROR_MESSAGE));

			//ログ：エラー
			logger.error(LogMessage.CENTER_INFO_DELETE_VIEW + LogMessage.INITIAL_DISPLAY + ErrorMessage.CENTERINFO_DELETE_LINKING_ERROR_MESSAGE);

			return "redirect:" + UrlConsts.CENTER_INFO_DELETE+"/"+centerId;
		}
		catch (NullPointerException NullError) {
			//Nullエラー処理
			redirectAttributes.addFlashAttribute("errorMsg", MessageManager.getMessage(messageSource, ErrorMessage.LIST_EMPTY_ERROR_MESSAGE));

			//ログ：エラー
			logger.error(LogMessage.CENTER_INFO_DELETE_VIEW + LogMessage.INITIAL_DISPLAY + ErrorMessage.LIST_EMPTY_ERROR_MESSAGE);

			return "redirect:" + UrlConsts.CENTER_INFO_DELETE+"/"+centerId;
		} 
		catch (Exception error) {
			//全ての例外処理
			redirectAttributes.addFlashAttribute("errorMsg", MessageManager.getMessage(messageSource, ErrorMessage.UNEXPECT_ERROR_MESSAGE));

			//ログ：エラー
			logger.error(LogMessage.CENTER_INFO_DELETE_VIEW + LogMessage.INITIAL_DISPLAY + ErrorMessage.UNEXPECT_ERROR_MESSAGE);

			return "redirect:" + UrlConsts.CENTER_INFO_DELETE+"/"+centerId;
		} 

	}
}
