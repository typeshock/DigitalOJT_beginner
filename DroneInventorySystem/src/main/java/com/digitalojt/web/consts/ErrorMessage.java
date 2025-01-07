package com.digitalojt.web.consts;

/**
 * エラーメッセージ定数クラス
 * 
 * @author ueno
 *
 */
public class ErrorMessage {
	
	// ログイン情報の入力に誤りがあった場合に、出力するエラーメッセージのID
	public static final String  LOGIN_WRONG_INPUT = "login.wrongInput";

	// すべての項目が空の場合のエラーメッセージ
	public static final String ALL_FIELDS_EMPTY_ERROR_MESSAGE = "allField.empty";

	// 空文字検索に関するエラーメッセージ
	public static final String UNEXPECTED_INPUT_ERROR_MESSAGE = "unexpected.input";

	// 不正な文字列を使用した検索に関するエラーメッセージ
	public static final String INVALID_INPUT_ERROR_MESSAGE = "invalid.input";

	// 文字超過に関するエラーメッセージ
	public static final String CENTER_NAME_LENGTH_ERROR_MESSAGE = "centerName.length.wrongInput";
	
	// リストが空の時のエラーメッセージ
	public static final String LIST_EMPTY_ERROR_MESSAGE = "list.empty";

	//データベース接続失敗時のエラーメッセージ
	public static final String DB_DISCONNECTED_ERROR_MESSAGE = "db.disconnected";

	//予期せぬエラー発生時のエラーメッセージ
	public static final String UNEXPECT_ERROR_MESSAGE = "unexpected.error";

	//文字数上限設定
	public static final int MAX_LENGTH = 20;

}
