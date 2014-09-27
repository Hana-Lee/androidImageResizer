package kr.co.hanalee.util;

import kr.co.hanalee.model.*;

/**
 * Created by Hana Lee on 2014. 9. 28..
 */
public class DPIFactory {

	public static DPI create(DPIName dpiName) {
		switch (dpiName) {
			case xxhdpi:
				return new XXHDPI();
			case xhdpi:
				return new XHDPI();
			case hdpi:
				return new HDPI();
			case mdpi:
				return new MDPI();
			case ldpi:
				return new LDPI();
			case tvdpi:
				return new TVDPI();
			default:
				break;
		}

		return null;
	}
}
