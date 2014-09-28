package kr.co.hanalee.util;

import kr.co.hanalee.model.*;

/**
 * Created by Hana Lee on 2014. 9. 28..
 */
public class DPIFactory {

	private static XXHDPI xxhdpi = new XXHDPI();
	private static XHDPI xhdpi = new XHDPI();
	private static HDPI hdpi = new HDPI();
	private static MDPI mdpi = new MDPI();
	private static LDPI ldpi = new LDPI();
	private static TVDPI tvdpi = new TVDPI();

	public static DPI create(DPIName dpiName) {
		switch (dpiName) {
			case xxhdpi:
				return xxhdpi;
			case xhdpi:
				return xhdpi;
			case hdpi:
				return hdpi;
			case mdpi:
				return mdpi;
			case ldpi:
				return ldpi;
			case tvdpi:
				return tvdpi;
			default:
				break;
		}

		return null;
	}
}
