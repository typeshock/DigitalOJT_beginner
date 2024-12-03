package com.digitalojt.web.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * 分類情報画面のサービスクラス
 *
 * @author ueno
 * 
 */
@Service
@RequiredArgsConstructor
public class CategoryInfoService {

	/**
	 * HelloWorldの画面出力用データの作成
	 * @return
	 */
	public String getHelloWorld() {
		return "HelloWorld";
	}
	
	
}
