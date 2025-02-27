package com.digitalojt.web.util;

import java.util.Arrays;

import com.digitalojt.web.consts.InvalidCharacter;
import com.digitalojt.web.consts.NumberValidConsts;

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
	 * 数字の範囲チェック (MIN_NUMBERからMAX_NUMBERの範囲の値)
	 *  
	 * @param val
	 * @return
	 */
	public static boolean isWithinRange(Integer val) {
		if(val != null) {
			// MIN_NUMBERより小さい値、またはMAX_NUMBERより大きい値の時、trueを返す
			return val < NumberValidConsts.MIN_NUMBER || val > NumberValidConsts.MAX_NUMBER;
		}
		//valがnullの時にtrueを返す
		return true;
	}

	/**
	 * 以上・以下のフラグチェック
	 *  
	 * @param val
	 * @return
	 */
	public static boolean isAmountRange(Integer val) {
		if(val != null) {
			// DOWN_RANGE_NUMBERより小さい値、またはUP_RANGE_NUMBERより大きい値の時、trueを返す
			return NumberValidConsts.UP_RANGE_NUMBER > val || NumberValidConsts.DOWN_RANGE_NUMBER < val;
		}
		//valがnullの時にtrueを返す
		return true;
	}

}
