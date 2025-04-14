package com.digitalojt.web.consts;

/**
 * ログメッセージ定数クラス
 * 
 * @author dotlife
 * 
 */
public class LogMessage {

	//IPアドレス
	public static final String IP_ADDRESS_KEY = "ipAddress";

	//ユーザーID
	public static final String USER_ID_KEY = "userId";

	//アクセスログ
	//ACCESSだけ表示される
	public static final String ACCESS_LOG = "ACCESS";

	//エラーログ
	//(GETかPOST)-(処理対象の関数名)-(エラー内容)
	public static final String ERROR_LOG = "ERROR: %s - %s - %s";

	//アプリケーションログ
	//(GETかPOST)-(処理対象の関数名)-(開始か終了)
	public static final String APP_LOG = "APP: %s - %s - %s";

	public static final String PROCESS_START = "START";
	public static final String PROCESS_END = "END";
	public static final String FLASH_ATTRIBUTE_ERROR = "errorMsg";

	//HTTPメソッド
	public static final String HTTP_GET = "GET";
	public static final String HTTP_POST = "POST";

	//画面名
	public static final String CENTER_INFO_VIEW = "在庫センター情報画面";
	public static final String CENTER_INFO_SERCH_VIEW = "在庫センター情報検索画面";
	public static final String CENTER_INFO_REGISTER_VIEW = "在庫センター情報登録画面";
	public static final String CENTER_INFO_UPDATE_VIEW = "在庫センター情報更新／削除画面";
	public static final String CENTER_INFO_DELETE_VIEW = "在庫センター情報削除画面";

	//処理内容
	public static final String INITIAL_DISPLAY = "初期表示";
	public static final String SERCH = "検索処理";
	public static final String REGISTER = "データ登録処理";
	public static final String UPDATE = "データ更新処理";
	public static final String DELETE = "データ削除処理";
	
	//警告
	public static final String WARN_ERROR_MESSAGE = "エラーメッセージ発生";
	
	//デバッグ用処理内容
	public static final String DEBUG_GET_DATE = "データベースからデータを取得";
	public static final String DEBUG_ADDATTRIBUTE = "画面表示用のリストをセット";
	public static final String DEBUG_SAVE_REGISTER_START = "データの登録を実行";
	public static final String DEBUG_SAVE_REGISTER_END = "データの登録が完了";
	public static final String DEBUG_SAVE_UPDATE_START = "データの更新を実行";
	public static final String DEBUG_SAVE_UPDATE_END = "データの更新が完了";
	public static final String DEBUG_SAVE_DELETE_START = "データの削除を実行";
	public static final String DEBUG_SAVE_DELETE_END = "データの削除が完了";

}
