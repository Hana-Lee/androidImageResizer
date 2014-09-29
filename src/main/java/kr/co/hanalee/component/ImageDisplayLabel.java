package kr.co.hanalee.component;

import javax.swing.*;

/**
 * @author <a href="mailto:voyaging@leehana.co.kr">Hana Lee</a>
 *         <p>
 *         Created by Hana Lee on 2014. 9. 29..
 */
public class ImageDisplayLabel extends JLabel {

	public ImageDisplayLabel() {
		super("Selected Image Preview");
		init();
	}

	private void init() {
		setHorizontalTextPosition(SwingConstants.CENTER);
		setHorizontalAlignment(SwingConstants.CENTER);
		setBorder(null);
		setAlignmentX(0.5f);
	}
}
