package kr.co.hanalee.component;

import kr.co.hanalee.util.Utils;

import javax.swing.*;
import javax.swing.filechooser.FileView;
import java.io.File;

/**
 * @author <a href="mailto:voyaging@leehana.co.kr">Hana Lee</a>
 *         <p>
 *         Created by Hana Lee on 2014. 9. 29..
 */
public class ImageFileView extends FileView {
	ImageIcon jpgIcon = Utils
			.createImageIcon("/kr/co/hanalee/images/jpgIcon.gif");
	ImageIcon pngIcon = Utils
			.createImageIcon("/kr/co/hanalee/images/pngIcon.png");

	@Override
	public String getName(File f) {
		return null; // let the L&F FileView figure this out
	}

	@Override
	public String getDescription(File f) {
		return null; // let the L&F FileView figure this out
	}

	@Override
	public Boolean isTraversable(File f) {
		return null; // let the L&F FileView figure this out
	}

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
