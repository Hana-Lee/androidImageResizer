package kr.co.hanalee.component;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * @author <a href="mailto:voyaging@leehana.co.kr">Hana Lee</a>
 *         <p>
 *         Created by Hana Lee on 2014. 9. 29..
 */
public class ImageDisplayParentPanel extends JPanel {

	public ImageDisplayParentPanel() {
		super();
		init();
	}

	private void init() {
		setMaximumSize(new Dimension(370, 350));
		setBackground(SystemColor.info);
		setBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null));
		setPreferredSize(new Dimension(370, 350));

		setLayout(new BoxLayout(
				this, BoxLayout.LINE_AXIS));
	}
}
