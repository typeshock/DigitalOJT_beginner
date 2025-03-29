package com.digitalojt.web.consts;

/**
 * 入力値制限用数字定数クラス
 * 
 * @author ueno
 *
 */
public class NumberValidConsts {

	//検索文字数上限設定
	public static final int MAX_LENGTH = 20;

	//検索文字数上限設定
	public static final int MAX_ADDRESS_LENGTH = 26;

	//在庫最小数値
	public static final int MIN_NUMBER = 1;

	//在庫最大数値
	public static final int MAX_NUMBER = 10000;

	//数値範囲 以上の設定
	public static final int UP_RANGE_NUMBER = 0;

	//数値範囲 以下の設定
	public static final int DOWN_RANGE_NUMBER = 1;

	//郵便番号の形式の設定
	public static final String PATTERN_POST_CODE = "^[0-9]{3}-[0-9]{4}$";

	//電話番号の形式の設定
	public static final String PATTERN_PHONE_NUMBER = "^[0-9]{3}-[0-9]{4}-[0-9]{4}$";

	//稼働状態の値
	public static final String VALID_NUMBER = "0";

	//稼働停止状態の値
	public static final String INVALID_NUMBER = "1";
}