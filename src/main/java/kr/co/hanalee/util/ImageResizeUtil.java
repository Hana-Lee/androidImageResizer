package kr.co.hanalee.util;

import kr.co.hanalee.model.DPI;
import kr.co.hanalee.model.MDPI;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Hana Lee on 2014. 9. 28..
 */
public class ImageResizeUtil {

	public static boolean resize(Enumeration<File> imgFiles, DPI selectedDPI) {
		if (imgFiles == null) {
			return false;
		}

		boolean result = false;
		while (imgFiles.hasMoreElements()) {
			File imgFile = imgFiles.nextElement();
			BufferedImage oriImage;
			try {
				oriImage = ImageIO.read(imgFile);
				if (oriImage != null) {
					ImageInputStream iis = ImageIO
							.createImageInputStream(imgFile);
					Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);

					ImageReader reader = null;
					if (iter != null && iter.hasNext()) {
						reader = iter.next();
					}

					Dimension originalImageDimension = new Dimension(oriImage.getWidth(), oriImage.getHeight());
					Dimension newDimension;

					Map<DPIName, Double> numbersForCalculation = selectedDPI.getBasicNumbersForCalculation();
					for (DPIName dpiName : numbersForCalculation.keySet()) {
						String currentDPIName = dpiName.getName();
						String newDirectoryName = imgFile.getParent()
								+ File.separator + currentDPIName;

						File outputFile = new File(newDirectoryName,
								imgFile.getName());

						boolean divAvailable = selectedDPI.equals(MDPI.class) && dpiName.equals(DPIName.tvdpi);
						newDimension = DPICalculator.calculateNewDimension(originalImageDimension,
								numbersForCalculation.get(dpiName), divAvailable);

						Image newImage = oriImage.getScaledInstance(
								(int) newDimension.getWidth(),
								(int) newDimension.getHeight(), Image.SCALE_SMOOTH);

						result = ImageIO.write(
								ImageConvertor.convertingImageToBufferedImage(
										newImage,
										(int) newDimension.getWidth(),
										(int) newDimension.getHeight()
								),
								reader.getFormatName(), outputFile);
					}
				}
			} catch (IOException ex) {
				ex.printStackTrace();
				return false;
			}
		}
		return result;
	}
}
