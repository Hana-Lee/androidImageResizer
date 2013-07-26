package kr.co.hanalee.component;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import kr.co.hanalee.util.Utils;

/**
 * @author HanaLee <voyaging.hana@gmail.com>
 * 
 */
public class ImageFilter extends FileFilter implements java.io.FileFilter {

	/**
	 * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}

		String extension = Utils.getExtension(f);
		if (extension != null) {
			if (extension.equals(Utils.jpeg) || extension.equals(Utils.jpg)
					|| extension.equals(Utils.png)) {
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	/**
	 * @see javax.swing.filechooser.FileFilter#getDescription()
	 */
	@Override
	public String getDescription() {
		return "multiple png, jpg, jpeg or single directory";
	}
}
