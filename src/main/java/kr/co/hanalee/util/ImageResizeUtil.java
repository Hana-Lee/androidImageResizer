package kr.co.hanalee.util;

import kr.co.hanalee.context.RenderContext;
import kr.co.hanalee.model.DPI;
import kr.co.hanalee.model.MDPI;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Hana Lee on 2014. 9. 28..
 */
public class ImageResizeUtil {

	private static final long SLEEP_TIME = 1 * 1000;

	public static void resize(Enumeration<File> imgFiles, DPI selectedDPI, RenderContext context) {
		SwingWorker<Void, Void> imageFileListMakeWorker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				resize(imgFiles, selectedDPI);

				Thread.sleep(SLEEP_TIME);
				return null;
			}
		};

		JDialog dialog = new JDialog(context.getMainFrame(), "Dialog",
				Dialog.ModalityType.APPLICATION_MODAL);

		imageFileListMakeWorker.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals("state")) {
					if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
						dialog.dispose();

						JOptionPane.showMessageDialog(context.getMainFrame(),
								"Image resize work done.", "Info",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		imageFileListMakeWorker.execute();

		JProgressBar progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(progressBar, BorderLayout.CENTER);
		panel.add(new JLabel("Image resize working. Please wait"), BorderLayout.PAGE_START);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		dialog.add(panel);
		dialog.pack();
		dialog.setLocationRelativeTo(context.getMainFrame());
		dialog.setVisible(true);
	}

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

						File newDirectory = new File(newDirectoryName);
						if (newDirectory.exists() == Boolean.FALSE) {
							newDirectory.mkdir();
						}
						File outputFile = new File(newDirectoryName,
								imgFile.getName());

						boolean divAvailable = !(selectedDPI.equals(MDPI.class) && dpiName.equals(DPIName.tvdpi));
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
