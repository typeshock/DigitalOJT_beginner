package com.digitalojt.web.util;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		if (val != null) {
			return Arrays.stream(InvalidCharacter.values())
					.anyMatch(invalidChar -> val.indexOf(invalidChar.getCharacter()) >= 0);
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
		if (val != null) {
			// MIN_NUMBERより小さい値、またはMAX_NUMBERより大きい値の時、エラー判定を示すtrueを返す
			return val < NumberValidConsts.MIN_NUMBER || val > NumberValidConsts.MAX_NUMBER;
		}
		//valがnullの時にエラー判定を示すtrueを返す
		return true;
	}

	/**
	 * String形式の数字の範囲チェック (MIN_NUMBERからMAX_NUMBERの範囲の値)
	 *  
	 * @param val
	 * @return
	 */
	public static boolean isWithinRange(String val) {

		if (val != null) {
			// 文字列を数字に変換する
			Integer number = Integer.valueOf(val);

			// MIN_NUMBERより小さい値、またはMAX_NUMBERより大きい値の時、エラー判定を示すtrueを返す
			return number < NumberValidConsts.MIN_NUMBER || number > NumberValidConsts.MAX_NUMBER;
		}
		//valがnullの時にエラー判定を示すtrueを返す
		return true;
	}

	/**
	 * 以上・以下のフラグチェック
	 *  
	 * @param val
	 * @return
	 */
	public static boolean isAmountRange(Integer val) {
		if (val != null) {
			// DOWN_RANGE_NUMBERより小さい値、またはUP_RANGE_NUMBERより大きい値の時、エラー判定を示すtrueを返す
			return NumberValidConsts.UP_RANGE_NUMBER > val || NumberValidConsts.DOWN_RANGE_NUMBER < val;
		}
		//valがnullの時にエラー判定を示すtrueを返す
		return true;
	}

	/**
	 * 郵便番号形式チェック
	 *  
	 * @param val
	 * @return
	 */
	public static boolean isPostCode(String val) {

		if (val != null) {

			// 郵便番号の正規表現を定義する
			Pattern pattern = Pattern.compile(NumberValidConsts.PATTERN_POST_CODE);

			// valと正規表現と一致するか確認する
			Matcher matcher = pattern.matcher(val);
			if (matcher.find()) {
				//一致している場合、エラー無しを示すfalseを返す
				return false;
			}
			// パターンと不一致の時にエラー判定を示すtrueを返す
			return true;

		}
		//valがnullの時にエラー判定を示すtrueを返す
		return true;
	}

	/**
	 * 電話番号形式チェック
	 *  
	 * @param val
	 * @return
	 */
	public static boolean isPhoneNumber(String val) {

		if (val != null) {

			// 電話番号の正規表現を定義する
			Pattern patternA = Pattern.compile(NumberValidConsts.PATTERN_PHONE_NUMBER_A);
			Pattern patternB = Pattern.compile(NumberValidConsts.PATTERN_PHONE_NUMBER_B);
			Pattern patternC = Pattern.compile(NumberValidConsts.PATTERN_PHONE_NUMBER_C);

			// valと正規表現と一致するか確認する
			Matcher matcherA = patternA.matcher(val);
			Matcher matcherB = patternB.matcher(val);
			Matcher matcherC = patternC.matcher(val);

			if (matcherA.find() || matcherB.find() || matcherC.find() ) {
				//電話番号の形式いずれかと一致している場合、エラー無しを示すfalseを返す
				return false;
			}
			// パターンと不一致の時にエラー判定を示すtrueを返す
			return true;

		}
		//valがnullの時にエラー判定を示すtrueを返す
		return true;
	}

	/**
	 * 稼働・停止のフラグチェック
	 *  
	 * @param val
	 * @return
	 */
	public static boolean isOperationStatusFlag(String val) {
		if (val != null) {
			// 文字列を数字に変換する
			Integer number = Integer.valueOf(val);

			// DOWN_RANGE_NUMBERより小さい値、またはUP_RANGE_NUMBERより大きい値の時、エラー判定を示すtrueを返す
			return NumberValidConsts.UP_RANGE_NUMBER > number || NumberValidConsts.DOWN_RANGE_NUMBER < number;
		}
		//valがnullの時にエラー判定を示すtrueを返す
		return true;

	}

	/**
	 * 現在容量が最大容量を上回らないかチェック
	 *  
	 * @param maxStorageCapacity
	 * @param currentStorageCapacity
	 * @return
	 */
	public static boolean isStorageCapacityOver(String maxStorageCapacity,String currentStorageCapacity) {
		if (maxStorageCapacity != null || currentStorageCapacity != null) {
			// 文字列を数字に変換する
			Integer maxStrageNumber = Integer.valueOf(maxStorageCapacity);
			Integer currentStrageNumber = Integer.valueOf(currentStorageCapacity);
			
			// maxStorageCapacityよりcurrentStorageCapacityが大きい時、エラー判定を示すtrueを返す
			return maxStrageNumber < currentStrageNumber;
		}
		//maxStorageCapacityまたはcurrentStorageCapacityがnullの時にエラー判定を示すtrueを返す
		return true;
	}	
	

}
