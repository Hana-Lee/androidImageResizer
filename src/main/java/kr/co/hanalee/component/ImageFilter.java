package kr.co.hanalee.component;

import kr.co.hanalee.util.Utils;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * @author <a href="mailto:voyaging@leehana.co.kr">Hana Lee</a>
 *         <p>
 *         Created by Hana Lee on 2014. 9. 29..
 */
public class ImageFilter extends FileFilter implements java.io.FileFilter {

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

	@Override
	public String getDescription() {
		return "multiple png, jpg, jpeg or single directory";
	}
}
