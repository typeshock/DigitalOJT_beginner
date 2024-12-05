package com.digitalojt.web.consts;

/**
 * パーツカテゴリー Enumクラス
 * 
 * @author ueno
 */
public enum PartsRegion {
	
	FRAME("フレーム"),
	PROPELLER("プロペラ"),
	MOTOR("電動モーター"),
	REGULATOR("電子速度調整器"),
	BATTERY("バッテリー"),
	FLIGHTCONTROLLER("フライトコントローラー"),
	REMOTECONTROLLER("リモートコントローラー"),
	RECEIVER("受信機"),
	GPS("GPSモジュール"),
	SENSOR("カメラ／センサー");
	
    private final String name;

	PartsRegion(String partsName) {
        this.name = partsName;
    }

    public String getPartsName() {
        return name;
    }
}
