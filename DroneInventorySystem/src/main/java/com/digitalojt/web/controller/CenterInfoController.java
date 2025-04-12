package com.digitalojt.web.controller;

import java.util.Arrays;
import java.util.List;

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
	public String getRegister() {

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
			// Valid項目チェック
			if (bindingResult.hasErrors()) {

				// エラーメッセージをプロパティファイルから取得
				redirectAttributes.addFlashAttribute("errorMsg", MessageManager.getMessage(messageSource, bindingResult.getGlobalError().getDefaultMessage()));

				return "redirect:" + UrlConsts.CENTER_INFO_REGISTER;

			}

			// データの登録処理を実行する
			centerInfoService.saveCenterInfoData(form);

			// 登録成功時のメッセージを設定する
			String systemMsg = MessageManager.getMessage(messageSource, SystemMessage.CENTERINFO_REGISTER_SUCCESS);
			redirectAttributes.addFlashAttribute("systemMsg", systemMsg);

			// 登録完了時に在庫センター情報画面にリダイレクト
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

	/**
	 *更新画面表示
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(UrlConsts.CENTER_INFO_UPDATE+"/{centerId}")
	public String getUpdate(Model model, @PathVariable("centerId") Integer centerId) {

		try {
			// 在庫センター情報画面に表示するデータを取得
			CenterInfo centerInfoList = centerInfoService.getCenterInfoData(centerId);

			// 画面表示用に商品情報リストをセット
			model.addAttribute("centerInfoList", centerInfoList);

			return UrlConsts.CENTER_INFO_UPDATE;

		} catch (Exception error) {
			//全ての例外処理
			String errorMsg = MessageManager.getMessage(messageSource, ErrorMessage.UNEXPECT_ERROR_MESSAGE);
			model.addAttribute("errorMsg", errorMsg);
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
			// Valid項目チェック
			if (bindingResult.hasErrors()) {

				// エラーメッセージをプロパティファイルから取得
				redirectAttributes.addFlashAttribute("errorMsg", MessageManager.getMessage(messageSource, bindingResult.getGlobalError().getDefaultMessage()));

				return "redirect:" + UrlConsts.CENTER_INFO_UPDATE+"/"+form.getCenterId();

			}

			// データの更新処理を実行する
			centerInfoService.updateCenterInfoData(form);

			// 更新成功時のメッセージを設定する
			redirectAttributes.addFlashAttribute("systemMsg", MessageManager.getMessage(messageSource, SystemMessage.CENTERINFO_UPDATE_SUCCESS));

			// 更新完了時に在庫センター情報画面にリダイレクト
			return "redirect:" + UrlConsts.CENTER_INFO;

		} catch (Error linkingError) {
			//エラー処理
			redirectAttributes.addFlashAttribute("errorMsg", MessageManager.getMessage(messageSource, ErrorMessage.CENTERINFO_DELETE_LINKING_ERROR_MESSAGE));
			return "redirect:" + UrlConsts.CENTER_INFO_UPDATE+"/"+form.getCenterId();
		
		} catch (NullPointerException NullError) {
			//Nullエラー処理
			redirectAttributes.addFlashAttribute("errorMsg", MessageManager.getMessage(messageSource, ErrorMessage.LIST_EMPTY_ERROR_MESSAGE));
			return "redirect:" + UrlConsts.CENTER_INFO_UPDATE+"/"+form.getCenterId();

		} catch (Exception error) {
			//全ての例外処理
			redirectAttributes.addFlashAttribute("errorMsg", MessageManager.getMessage(messageSource, ErrorMessage.UNEXPECT_ERROR_MESSAGE));
			return "redirect:" + UrlConsts.CENTER_INFO_UPDATE+"/"+form.getCenterId();
		} 

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
			// 在庫センター情報画面に表示するデータを取得
			CenterInfo centerInfoList = centerInfoService.getCenterInfoData(centerId);

			// 画面表示用に商品情報リストをセット
			model.addAttribute("centerInfoList", centerInfoList);

			return UrlConsts.CENTER_INFO_DELETE;

		} catch (Exception error) {
			//全ての例外処理
			String errorMsg = MessageManager.getMessage(messageSource, ErrorMessage.UNEXPECT_ERROR_MESSAGE);
			model.addAttribute("errorMsg", errorMsg);
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
	public String postDelete(Model model, @Valid CenterInfoForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		try {
			// Valid項目チェック
			if (bindingResult.hasErrors()) {

				// エラーメッセージをプロパティファイルから取得
				redirectAttributes.addFlashAttribute("errorMsg", MessageManager.getMessage(messageSource, bindingResult.getGlobalError().getDefaultMessage()));

				return "redirect:" + UrlConsts.CENTER_INFO_DELETE+"/"+form.getCenterId();

			}

			// データの更新(論理削除)処理を実行する
			centerInfoService.deleteCenterInfoData(form);

			// 削除成功時のメッセージを設定する
			redirectAttributes.addFlashAttribute("systemMsg", MessageManager.getMessage(messageSource, SystemMessage.CENTERINFO_DELETE_SUCCESS));

			// 削除完了時に在庫センター情報画面にリダイレクト
			return "redirect:" + UrlConsts.CENTER_INFO;

		}catch(Error error) {
			//在庫一覧でセンター名が使用されている際のエラー処理
			redirectAttributes.addFlashAttribute("errorMsg", MessageManager.getMessage(messageSource, ErrorMessage.CENTERINFO_DELETE_LINKING_ERROR_MESSAGE));
			return "redirect:" + UrlConsts.CENTER_INFO_DELETE+"/"+form.getCenterId();
		}
		catch (NullPointerException NullError) {
			//Nullエラー処理
			redirectAttributes.addFlashAttribute("errorMsg", MessageManager.getMessage(messageSource, ErrorMessage.LIST_EMPTY_ERROR_MESSAGE));
			return "redirect:" + UrlConsts.CENTER_INFO_DELETE+"/"+form.getCenterId();
		} 
		catch (Exception error) {
			//全ての例外処理
			redirectAttributes.addFlashAttribute("errorMsg", MessageManager.getMessage(messageSource, ErrorMessage.UNEXPECT_ERROR_MESSAGE));
			return "redirect:" + UrlConsts.CENTER_INFO_DELETE+"/"+form.getCenterId();
		} 

	}
}
