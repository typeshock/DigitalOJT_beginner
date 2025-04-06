package com.digitalojt.web.consts;

/**
 * エラーメッセージ定数クラス
 * 
 * @author ueno
 *
 */
public class ErrorMessage {

	// ログイン情報の入力に誤りがあった場合に、出力するエラーメッセージのID
	public static final String LOGIN_WRONG_INPUT = "login.wrongInput";

	// すべての項目が空の場合のエラーメッセージ
	public static final String ALL_FIELDS_EMPTY_ERROR_MESSAGE = "allField.empty";

	// 項目が空の場合のエラーメッセージ
	public static final String FIELDS_EMPTY_ERROR_MESSAGE = "field.empty";

	// 空文字検索に関するエラーメッセージ
	public static final String UNEXPECTED_INPUT_ERROR_MESSAGE = "unexpected.input";

	// 不正な文字列を入力した際に関するエラーメッセージ
	public static final String INVALID_INPUT_ERROR_MESSAGE = "invalid.input";

	//不正なフォーマットを入力した際に関するエラーメッセージ
	public static final String INVALID_FORMAT_ERROR_MESSAGE = "invaled.format";

	// 文字超過に関するエラーメッセージ センター情報
	public static final String CENTER_NAME_LENGTH_ERROR_MESSAGE = "centerName.length.input";

	// 文字超過に関するエラーメッセージ 分類情報
	public static final String CATEGORY_NAME_LENGTH_ERROR_MESSAGE = "categoryName.length.input";

	// リストが空の時のエラーメッセージ
	public static final String LIST_EMPTY_ERROR_MESSAGE = "list.empty";

	//予期せぬエラー発生時のエラーメッセージ
	public static final String UNEXPECT_ERROR_MESSAGE = "unexpected.error";

	// 半角数字以外が入力されたときに関するエラーメッセージ
	public static final String NON_NUMERIC_INPUT_ERROR_MESSAGE = "non.numeric.input";

	//Integer型として受け付けない数値が入力されたときに関するエラーメッセージ
	public static final String UNEXPECTED_NUMBER_INPUT_ERROR_MESSAGE = "unexpected.number.input";

	//文字超過に関するエラーメッセージ
	public static final String NAME_LENGTH_ERROR_MESSAGE = "name.length.wrongInput";

	//住所の文字超過に関するエラーメッセージ
	public static final String ADDLESS_LENGTH_ERROR_MESSAGE = "address.length.wrongInput";

	//入力数値の範囲に関するエラーメッセージ
	public static final String NUMBER_LENGTH_ERROR_MESSAGE = "number.length.wrongInput";

	//FromToの理論に異常があるときに関するエラーメッセージ
	public static final String FROM_TO_INPUT_ERROR_MESSAGE = "from.to.input";

	// 在庫が存在する在庫センターを削除しようとしたときに関するエラーメッセージ
	public static final String CANNOT_DELETE_CENTER_ERROR_MESSAGE = "cannot.delete.center";

	// 在庫センター登録・更新画面で必須項目が全て入力されていないときに関するエラーメッセージ
	public static final String DATA_FILED_EMPTY_ERROR_MESSAGE = "centerInfo.date.fieldEmpty";

	// 在庫センター登録・更新画面で最大容量に数字以外が入力されているときに関するエラーメッセージ
	public static final String MAX_STORAGE_ERROR_MESSAGE = "centerInfo.date.maxStorageCapacity.wrongInput";

	// 在庫センター登録・更新画面で現在容量に数字以外が入力されているときに関するエラーメッセージ
	public static final String CURRENT_STORAGE_ERROR_MESSAGE = "centerInfo.date.currentStorageCapacity.wrongInput";

	// 在庫センター登録・更新画面で現在容量が最大容量を上回る数値が入力されたときに関するエラーメッセージ
	public static final String OVER_STORAGE_ERROR_MESSAGE = "centerInfo.date.overStorageCapacity.wrongInput";

	// 在庫センター登録画面でデータ登録時にトランザクションエラーが起きたときに関するエラーメッセージ
	public static final String TRANSACTION_ERROR_MESSAGE = "centerInfoRegister.transactionError";

	// 在庫センター更新画面でデータ更新時にトランザクションエラーが起きたときに関するエラーメッセージ
	public static final String UPDATE_ERROR_MESSAGE = "centerInfoUpdate.transactionError";

	// 在庫センター登録・更新画面で入力されたデータの形式が正しくないときに関するエラーメッセージ
	public static final String FORMAT_ERROR_MESSAGE = "centerInfo.data.format.wrongInput";

	// 在庫センター登録・更新画面で数字以外入力されたときに関するエラーメッセージ
	public static final String NUMBER_INPUT_ERROR_MESSAGE = "centerInfo.data.number.wrongInput";

}
