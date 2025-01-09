
package com.digitalojt.web.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.digitalojt.web.entity.AdminInfo;
import com.digitalojt.web.repository.AdminInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ログイン画面のサービスクラス
 *
 * @author ueno
 * 
 */
@Service
@RequiredArgsConstructor
public class LoginService {

	/** 管理者情報テーブル リポイジトリー */
	private final AdminInfoRepository repository;
	
	/** パスワードエンコーダー */
	private final PasswordEncoder passwordEncoder;

	/**
	 * ユーザ情報テーブル 主キー検索
	 * 
	 * @param loginId ログインID
	 * @return ユーザ情報テーブルを主キー検索した結果(1件)
	 */
	public Optional<AdminInfo> searchUserById(String loginId) {
		return repository.findById(loginId);
	}
}
