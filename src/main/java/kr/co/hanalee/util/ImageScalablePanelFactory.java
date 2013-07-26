package kr.co.hanalee.util;

import java.awt.image.BufferedImage;

public class ImageScalablePanelFactory {

	public static ScalablePane create(BufferedImage image) {
		return new ScalablePane(image);
	}
}
