package kr.co.hanalee.util;

/**
 * Created by Hana Lee on 2014. 9. 28..
 * <p/>
 * 어플리케이션에서 공통으로 참조할 DPI 의 이름을 enum 으로 정의하여
 * DPI 이름 변경에 대해 최소의 수정을 보장한다.
 */
public enum DPIName {

	xxhdpi("xxhdpi"), xhdpi("xhdpi"), hdpi("hdpi"), mdpi("mdpi"),
	ldpi("ldpi"), tvdpi("tvdpi");

	private String name;

	DPIName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
