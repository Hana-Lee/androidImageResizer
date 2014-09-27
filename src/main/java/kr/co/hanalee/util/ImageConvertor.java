package kr.co.hanalee.util;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Hana Lee on 2014. 9. 28..
 */
public class ImageConvertor {

	public static BufferedImage convertingImageToBufferedImage(Image image,
	                                                           int width, int height) {
		BufferedImage after = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		Graphics bg = after.getGraphics();
		bg.drawImage(image, 0, 0, null);
		bg.dispose();
		return after;
	}
}
