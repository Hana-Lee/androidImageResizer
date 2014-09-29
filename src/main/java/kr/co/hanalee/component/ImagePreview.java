package kr.co.hanalee.component;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

/**
 * @author <a href="mailto:voyaging@leehana.co.kr">Hana Lee</a>
 *         <p>
 *         Created by Hana Lee on 2014. 9. 29..
 */
public class ImagePreview extends JComponent implements PropertyChangeListener {

	private static final long serialVersionUID = 7855395819180145374L;

	ImageIcon thumbnail = null;
	File file = null;

	public ImagePreview(JFileChooser fc) {
		setPreferredSize(new Dimension(100, 50));
		fc.addPropertyChangeListener(this);
	}

	public void loadImage() {
		if (file == null) {
			thumbnail = null;
			return;
		}

		ImageIcon tmpIcon = new ImageIcon(file.getPath());
		if (tmpIcon != null) {
			if (tmpIcon.getIconWidth() > 90) {
				thumbnail = new ImageIcon(tmpIcon.getImage().getScaledInstance(
						90, -1, Image.SCALE_DEFAULT));
			} else {
				thumbnail = tmpIcon;
			}
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		boolean update = false;
		String prop = e.getPropertyName();

		if (JFileChooser.DIRECTORY_CHANGED_PROPERTY.equals(prop)) {
			file = null;
			update = true;

		} else if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(prop)) {
			file = (File) e.getNewValue();
			update = true;
		}

		if (update) {
			thumbnail = null;
			if (isShowing()) {
				loadImage();
				repaint();
			}
		}
	}

	protected void paintComponent(Graphics g) {
		if (thumbnail == null) {
			loadImage();
		}
		if (thumbnail != null) {
			int x = getWidth() / 2 - thumbnail.getIconWidth() / 2;
			int y = getHeight() / 2 - thumbnail.getIconHeight() / 2;

			if (y < 0) {
				y = 0;
			}

			if (x < 5) {
				x = 5;
			}
			thumbnail.paintIcon(this, g, x, y);
		}
	}
}
