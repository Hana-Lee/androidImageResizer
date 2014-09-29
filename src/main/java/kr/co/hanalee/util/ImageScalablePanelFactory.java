package kr.co.hanalee.util;

import java.awt.image.BufferedImage;

/**
 * @author <a href="mailto:voyaging@leehana.co.kr">Hana Lee</a>
 *         <p>
 *         Created by Hana Lee on 2014. 9. 29..
 */
public class ImageScalablePanelFactory {

	public static ScalablePane create(BufferedImage image) {
		return new ScalablePane(image);
	}
}
