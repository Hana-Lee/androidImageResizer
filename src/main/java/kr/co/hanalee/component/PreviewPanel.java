package kr.co.hanalee.component;

import javax.swing.*;
import java.awt.*;

/**
 * @author <a href="mailto:voyaging@leehana.co.kr">Hana Lee</a>
 *         <p>
 *         Created by Hana Lee on 2014. 9. 29..
 */
public class PreviewPanel extends JPanel {

	public PreviewPanel() {
		super();
		init();
	}

	private void init() {
		setBorder(null);
		setPreferredSize(new Dimension(800, 400));
		setLayout(new GridLayout(1, 2, 0, 0));
	}
}
