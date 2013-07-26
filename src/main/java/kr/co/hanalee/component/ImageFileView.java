package kr.co.hanalee.component;

import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileView;

import kr.co.hanalee.util.Utils;

/**
 * @author HanaLee <voyaging.hana@gmail.com>
 * 
 */
public class ImageFileView extends FileView {
	ImageIcon jpgIcon = Utils
			.createImageIcon("/kr/co/hanalee/images/jpgIcon.gif");
	ImageIcon pngIcon = Utils
			.createImageIcon("/kr/co/hanalee/images/pngIcon.png");

	/**
	 * @see javax.swing.filechooser.FileView#getName(java.io.File)
	 */
	@Override
	public String getName(File f) {
		return null; // let the L&F FileView figure this out
	}

	/**
	 * @see javax.swing.filechooser.FileView#getDescription(java.io.File)
	 */
	@Override
	public String getDescription(File f) {
		return null; // let the L&F FileView figure this out
	}

	/**
	 * @see javax.swing.filechooser.FileView#isTraversable(java.io.File)
	 */
	@Override
	public Boolean isTraversable(File f) {
		return null; // let the L&F FileView figure this out
	}

	/**
	 * @see javax.swing.filechooser.FileView#getTypeDescription(java.io.File)
	 */
	@Override
	public String getTypeDescription(File f) {
		String extension = Utils.getExtension(f);
		String type = null;

		if (extension != null) {
			if (extension.equals(Utils.jpeg) || extension.equals(Utils.jpg)) {
				type = "JPEG Image";
			} else if (extension.equals(Utils.png)) {
				type = "PNG Image";
			}
		}
		return type;
	}

	/**
	 * @see javax.swing.filechooser.FileView#getIcon(java.io.File)
	 */
	@Override
	public Icon getIcon(File f) {
		String extension = Utils.getExtension(f);
		Icon icon = null;

		if (extension != null) {
			if (extension.equals(Utils.jpeg) || extension.equals(Utils.jpg)) {
				icon = jpgIcon;
			} else if (extension.equals(Utils.png)) {
				icon = pngIcon;
			}
		}
		return icon;
	}
}
