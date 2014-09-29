package kr.co.hanalee.util;

import javax.swing.*;

/**
 * Created by Hana Lee on 2014. 9. 29..
 */
public class DPIButtonFactory {
	private static JRadioButton ldpiRadioBtn = new JRadioButton(DPIName.ldpi.getName());
	private static JRadioButton mdpiRadioBtn = new JRadioButton(DPIName.mdpi.getName());
	private static JRadioButton hdpiRadioBtn = new JRadioButton(DPIName.hdpi.getName());
	private static JRadioButton xhdpiRadioBtn = new JRadioButton(DPIName.xhdpi.getName());
	private static JRadioButton xxhdpiRadioBtn = new JRadioButton(DPIName.xxhdpi.getName());
	private static JRadioButton tvdpiRadioBtn = new JRadioButton(DPIName.tvdpi.getName());

	private static ButtonGroup dpiButtonGroup = new ButtonGroup();

	static {
		dpiButtonGroup.add(ldpiRadioBtn);
		dpiButtonGroup.add(mdpiRadioBtn);
		dpiButtonGroup.add(tvdpiRadioBtn);
		dpiButtonGroup.add(hdpiRadioBtn);
		dpiButtonGroup.add(xhdpiRadioBtn);
		dpiButtonGroup.add(xxhdpiRadioBtn);
	}

	public static JRadioButton create(DPIName dpiName) {
		switch (dpiName) {
			case xxhdpi:
				return xxhdpiRadioBtn;
			case xhdpi:
				return xhdpiRadioBtn;
			case hdpi:
				return hdpiRadioBtn;
			case mdpi:
				return mdpiRadioBtn;
			case ldpi:
				return ldpiRadioBtn;
			case tvdpi:
				return tvdpiRadioBtn;
			default:
				break;
		}

		return null;
	}

	public static ButtonGroup getDPIButtonGroup() {
		return dpiButtonGroup;
	}
}
