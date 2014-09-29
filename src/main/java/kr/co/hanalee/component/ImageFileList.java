package kr.co.hanalee.component;

import kr.co.hanalee.context.RenderContext;
import kr.co.hanalee.util.ImageScalablePanelFactory;
import kr.co.hanalee.util.ScalablePane;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Hana Lee on 2014. 9. 29..
 */
public class ImageFileList extends JList {

	private RenderContext context;

	public ImageFileList(RenderContext context) {
		super();
		this.context = context;
		init();
	}

	private void init() {
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setFont(context.getDefaultFont());
		addMouseListener(new ImageFileListActionMouseListener());
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	}

	private class ImageFileListActionMouseListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			JList fileList = (JList) e.getSource();
			final File imgFile = (File) context.getImageFileListModel().getElementAt(fileList
					.getSelectedIndex());

			BufferedImage selectedImage = null;
			try {
				selectedImage = ImageIO.read(imgFile);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}

			if (selectedImage != null) {
				ScalablePane component = ImageScalablePanelFactory
						.create(selectedImage);
				context.getImageDisplayParentPanel().removeAll();
				context.getImageDisplayParentPanel().add(component);
				context.getImageDisplayParentPanel().revalidate();
			}
		}
	}
}
