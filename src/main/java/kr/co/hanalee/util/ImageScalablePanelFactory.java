package kr.co.hanalee.util;

import java.awt.image.BufferedImage;

/**
 * @author HanaLee <voyaging.hana@gmail.com>
 *
 */
public class ImageScalablePanelFactory {

	/**
	 * @param image
	 * @return
	 */
	public static ScalablePane create(BufferedImage image) {
		return new ScalablePane(image);
	}
}
