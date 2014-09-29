package kr.co.hanalee.component;

import javax.swing.*;
import java.awt.*;

/**
 * @author <a href="mailto:voyaging@leehana.co.kr">Hana Lee</a>
 *         <p>
 *         Created by Hana Lee on 2014. 9. 29..
 */
public class ImageDisplayLabelPanel extends JPanel {

	public ImageDisplayLabelPanel() {
		super();
		init();
	}

	private void init() {
		setMaximumSize(new Dimension(400, 26));
		setPreferredSize(new Dimension(400, 26));
		setBackground(Color.ORANGE);

		add(new ImageDisplayLabel());
	}
}
