package kr.co.hanalee.util;

import java.io.File;

import javax.swing.ImageIcon;

/**
 * @author HanaLee <voyaging.hana@gmail.com>
 * 
 */
public class Utils {
	public final static String jpeg = "jpeg";
	public final static String jpg = "jpg";
	public final static String png = "png";

	/**
	 * @param f
	 * @return
	 */
	public static String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}

	/**
	 * @param path
	 * @return
	 */
	public static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = Utils.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
}
