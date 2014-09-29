package kr.co.hanalee.component;

import kr.co.hanalee.context.RenderContext;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by Hana Lee on 2014. 9. 29..
 */
public class ImageFileListDisplayPanel extends JPanel {

	private RenderContext context;

	public ImageFileListDisplayPanel(RenderContext context) {
		super();
		this.context = context;
		init();
	}

	private void init() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		setBackground(Color.PINK);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel imageListLabel = new JLabel("Image List");
		add(imageListLabel);

		JScrollPane scrollPane = new JScrollPane(context.getImageFileList());
		scrollPane.setPreferredSize(new Dimension(370, 350));
		add(scrollPane);
	}
}
