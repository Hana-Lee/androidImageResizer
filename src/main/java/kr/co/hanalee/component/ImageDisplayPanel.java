package kr.co.hanalee.component;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by Hana Lee on 2014. 9. 29..
 */
public class ImageDisplayPanel extends JPanel {

	public ImageDisplayPanel() {
		super();
		init();
	}

	private void init() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		setBackground(Color.ORANGE);
		setLayout(new BoxLayout(this,
				BoxLayout.PAGE_AXIS));

		add(new ImageDisplayLabelPanel());
	}
}
