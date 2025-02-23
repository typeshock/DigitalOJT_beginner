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
	public static Boolean isParameterInvalid(String val) {
		return Arrays.stream(InvalidCharacter.values()).anyMatch(invalidChar -> val.indexOf(invalidChar.getCharacter()) >= 0);
	}

	/**
	 * 半角数字チェック
	 *  
	 * @param val
	 * @return
	 */
	public static Boolean isNumeric(Integer val) {
		if (null == val) {
			return false;
		}
		return !val.toString().matches("^-?[0-9]+$");
	}

	/**
	 * 数字の範囲チェック (0～99,999,999)
	 *  
	 * @param val
	 * @return
	 */
	public static Boolean isWithinRange(Integer val) {
		// 0～99,999,999の範囲チェック
		return val < NumberConsts.MIN_NUMBER || val > NumberConsts.MAX_NUMBER;
	}

}
