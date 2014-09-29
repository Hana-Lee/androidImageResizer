package kr.co.hanalee.component;

import kr.co.hanalee.context.RenderContext;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by Hana Lee on 2014. 9. 29..
 */
public class ImageFileChooseDialog extends JFileChooser {

	protected static final long SLEEP_TIME = 1 * 1000;

	private RenderContext context;

	public ImageFileChooseDialog(RenderContext context) {
		super();
		this.context = context;
		init();
	}

	private void init() {
		setFileSelectionMode(FILES_AND_DIRECTORIES);
		setFileFilter(new ImageFilter());
		setMultiSelectionEnabled(true);
		setAcceptAllFileFilterUsed(false);
		setFileView(new ImageFileView());
		setAccessory(new ImagePreview(this));
		setDialogType(OPEN_DIALOG);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String command = e.getActionCommand();

				if (command.equals(APPROVE_SELECTION)) {
					File[] selectedFiles = getSelectedFiles();
					if (selectedFiles != null && selectedFiles.length > 0) {
						if (selectedFiles.length == 1) {
							if (selectedFiles[0].isDirectory()) {
								selectedFiles = selectedFiles[0].listFiles(new ImageFilter());
							}
						}

						final File[] finalSelectedFiles = selectedFiles;
						SwingWorker<Void, Void> imageFileListMakeWorker = new SwingWorker<Void, Void>() {
							@Override
							protected Void doInBackground() throws Exception {
								makeImageList(finalSelectedFiles);

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
									}
								}
							}
						});
						imageFileListMakeWorker.execute();

						JProgressBar progressBar = new JProgressBar();
						progressBar.setIndeterminate(true);
						JPanel panel = new JPanel(new BorderLayout());
						panel.add(progressBar, BorderLayout.CENTER);
						panel.add(new JLabel("Please wait......."), BorderLayout.PAGE_START);
						panel.setBorder(new EmptyBorder(10, 10, 10, 10));
						dialog.add(panel);
						dialog.pack();
						dialog.setLocationRelativeTo(context.getMainFrame());
						dialog.setVisible(true);
					}
				}
			}
		});
	}

	protected void makeImageList(File[] imgFiles) {
		DefaultListModel defaultListModel = new DefaultListModel();
		for (File imgFile : imgFiles) {
			try {
				if (imgFile.isDirectory()) {
					continue;
				}
				context.getImageFileListModel().addElement(imgFile);
				defaultListModel.addElement(getImageInformation(imgFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		context.getImageFileList().setModel(defaultListModel);
	}

	protected String getImageInformation(File imgFile) throws IOException {
		BufferedImage image = ImageIO.read(imgFile);
		ImageIcon icon = new ImageIcon(image);
		int height = icon.getIconHeight();
		int width = icon.getIconWidth();
		StringBuilder sb = new StringBuilder();
		sb.append("name : ");
		sb.append(imgFile.getName());
		sb.append("\t|\t");
		sb.append("dimension : ");
		sb.append(width);
		sb.append(" x ");
		sb.append(height);
		return sb.toString();
	}
}
