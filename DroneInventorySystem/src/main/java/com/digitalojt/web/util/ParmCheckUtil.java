package com.digitalojt.web.util;

import java.util.Arrays;

import com.digitalojt.web.consts.InvalidCharacter;
import com.digitalojt.web.consts.NumberConsts;

/**
 * パラメーターチェックに関する処理を行うクラス
 * 
 * @author ueno
 *
 */
public class ParmCheckUtil {

	/**
	 * 不正文字チェック
	 *  
	 * @param val
	 * @return
	 */
	public static boolean isParameterInvalid(String val) {
		if(val != null) {
			return Arrays.stream(InvalidCharacter.values()).anyMatch(invalidChar -> val.indexOf(invalidChar.getCharacter()) >= 0);
		}
		return false;
	}

	/**
	 * 数字の範囲チェック (1～10000)
	 *  
	 * @param val
	 * @return
	 */
	public static boolean isWithinRange(Integer val) {
		if(val != null) {
			// 1～10000の範囲チェック
			return val < NumberConsts.MIN_NUMBER || val > NumberConsts.MAX_NUMBER;
		}
		return false;
	}

	/**
	 * 以上・以下のフラグチェック
	 *  
	 * @param val
	 * @return
	 */
	public static boolean isAmountRange(Integer val) {
		if(val != null) {
			// 0or1以外の数値入力チェック
			return NumberConsts.UP_RANGE_NUMBER > val || NumberConsts.DOWN_RANGE_NUMBER < val;
		}
		return false;
	}

}
