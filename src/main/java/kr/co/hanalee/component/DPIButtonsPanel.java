package kr.co.hanalee.component;

import kr.co.hanalee.util.DPIButtonFactory;
import kr.co.hanalee.util.DPIName;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * Created by Hana Lee on 2014. 9. 29..
 */
public class DPIButtonsPanel extends JPanel {

	public DPIButtonsPanel() {
		super();
		init();
	}

	private void init() {
		setName("Source Image");
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel imageOriginalDpiLabel = new JLabel("Original DPI : ");
		add(imageOriginalDpiLabel);

		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(2, 10));
		separator.setOrientation(SwingConstants.VERTICAL);
		add(separator);
		add(DPIButtonFactory.create(DPIName.ldpi));

		separator = new JSeparator();
		separator.setPreferredSize(new Dimension(2, 10));
		separator.setOrientation(SwingConstants.VERTICAL);
		add(separator);
		add(DPIButtonFactory.create(DPIName.tvdpi));

		separator = new JSeparator();
		separator.setPreferredSize(new Dimension(2, 10));
		separator.setOrientation(SwingConstants.VERTICAL);
		add(separator);
		add(DPIButtonFactory.create(DPIName.mdpi));

		separator = new JSeparator();
		separator.setPreferredSize(new Dimension(2, 10));
		separator.setOrientation(SwingConstants.VERTICAL);
		add(separator);
		add(DPIButtonFactory.create(DPIName.hdpi));

		separator = new JSeparator();
		separator.setPreferredSize(new Dimension(2, 10));
		separator.setOrientation(SwingConstants.VERTICAL);
		add(separator);
		add(DPIButtonFactory.create(DPIName.xhdpi));

		separator = new JSeparator();
		separator.setPreferredSize(new Dimension(2, 10));
		separator.setOrientation(SwingConstants.VERTICAL);
		add(separator);
		add(DPIButtonFactory.create(DPIName.xxhdpi));
	}
}
