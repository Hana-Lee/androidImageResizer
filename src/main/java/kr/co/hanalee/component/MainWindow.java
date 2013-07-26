package kr.co.hanalee.component;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import kr.co.hanalee.util.ImageScalablePanelFactory;
import kr.co.hanalee.util.ScalablePane;

import org.apache.commons.lang3.StringUtils;

/**
 * @author HanaLee <voyaging.hana@gmail.com>
 * 
 */
public class MainWindow {

	private JFrame mainWindow;
	private JPanel imageFileListPanel;
	private JList<String> imageFileList;
	private DefaultListModel<File> imgFileListModel;
	private JPanel previewImageParentPanel;
	private ButtonGroup dpiButtonGroup;
	private final Action action = new DirOpenAction();
	private static final String DEFAULT_FONT = "Arial";
	private Map<String, List<Double>> dpisCalModel;
	private List<String> dpisModel;
	private final Action action_1 = new ExitAction();

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * @return
	 */
	public JFrame getMainWindow() {
		return mainWindow;
	}

	/**
	 * @return
	 */
	protected String getDefaultFontName() {
		GraphicsEnvironment e = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		Font[] fonts = e.getAllFonts(); // Get the fonts
		List<String> fontNames = new ArrayList<String>();
		for (Font font : fonts) {
			fontNames.add(font.getName());
		}

		if (fontNames.contains("나눔고딕")) {
			return "나눔고딕";
		}
		if (fontNames.contains("맑은 고딕")) {
			return "맑은 고딕";
		}
		if (fontNames.contains("굴림")) {
			return "굴림";
		}
		return DEFAULT_FONT;
	}

	/**
	 * Initialize the contents of the frame. Window Builder Generate
	 */
	private void initialize() {
		dpisCalModel = new LinkedHashMap<String, List<Double>>();
		List<Double> dp = new ArrayList<Double>();
		dp.add(1.5);
		dp.add(2.0);
		dp.add(3.007518796992481);
		dp.add(4.0);
		dpisCalModel.put("xxhdpi", dp);

		dp = new ArrayList<Double>();
		dp.add(1.333333333333333);
		dp.add(2.0);
		dp.add(1.503759398496241);
		dp.add(2.666666666666666);
		dpisCalModel.put("xhdpi", dp);

		dp = new ArrayList<Double>();
		dp.add(1.5);
		dp.add(1.12781954887218);
		dp.add(2.0);
		dpisCalModel.put("hdpi", dp);

		dp = new ArrayList<Double>();
		dp.add(1.33);
		dp.add(1.333333333333333);
		dpisCalModel.put("mdpi", dp);

		dp = new ArrayList<Double>();
		dp.add(1.773333333333333);
		dpisCalModel.put("tvdpi", dp);

		dpisCalModel.put("ldpi", null);

		dpisModel = new ArrayList<String>();
		dpisModel.add("xxhdpi");
		dpisModel.add("xhdpi");
		dpisModel.add("hdpi");
		dpisModel.add("mdpi");
		dpisModel.add("tvdpi");
		dpisModel.add("ldpi");

		UIManager.put("OptionPane.font", getDefaultFontName());
		UIManager.put("Label.font", getDefaultFontName());
		UIManager.put("Button.font", getDefaultFontName());
		UIManager.put("FileChooser.font", getDefaultFontName());

		mainWindow = new JFrame();
		mainWindow.setResizable(false);
		mainWindow.setTitle("Image Resizer");
		mainWindow
				.setIconImage(Toolkit
						.getDefaultToolkit()
						.getImage(
								MainWindow.class
										.getResource("/com/sun/java/swing/plaf/motif/icons/DesktopIcon.gif")));
		mainWindow.setPreferredSize(new Dimension(800, 500));
		mainWindow.setBounds(100, 100, 450, 300);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.pack();

		JMenuBar menuBar = new JMenuBar();
		mainWindow.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpenFile = new JMenuItem("Open");
		mntmOpenFile.setAction(action);
		mntmOpenFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_MASK));
		mnFile.add(mntmOpenFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setAction(action_1);
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				InputEvent.CTRL_MASK));
		mnFile.add(mntmExit);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		mainWindow.getContentPane()
				.setLayout(
						new BoxLayout(mainWindow.getContentPane(),
								BoxLayout.PAGE_AXIS));

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setPreferredSize(new Dimension(800, 53));
		buttonsPanel.setBorder(new EmptyBorder(3, 3, 5, 3));
		buttonsPanel.setBackground(UIManager
				.getColor("InternalFrame.borderLight"));
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		mainWindow.getContentPane().add(buttonsPanel);

		JPanel dpiPanel = new JPanel();
		dpiPanel.setName("Source Image");
		dpiPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		dpiPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttonsPanel.add(dpiPanel);

		JRadioButton ldpiRadioBtn = new JRadioButton("ldpi");

		JRadioButton mdpiRadioBtn = new JRadioButton("mdpi");

		JRadioButton hdpiRadioBtn = new JRadioButton("hdpi");

		JRadioButton xhdpiRadioBtn = new JRadioButton("xhdpi");

		JRadioButton xxhdpiRadioBtn = new JRadioButton("xxhdpi");

		JRadioButton tvdpiRadioBtn = new JRadioButton("tvdpi");

		dpiButtonGroup = new ButtonGroup();
		dpiButtonGroup.add(ldpiRadioBtn);
		dpiButtonGroup.add(tvdpiRadioBtn);
		dpiButtonGroup.add(mdpiRadioBtn);
		dpiButtonGroup.add(hdpiRadioBtn);
		dpiButtonGroup.add(xhdpiRadioBtn);
		dpiButtonGroup.add(xxhdpiRadioBtn);

		JLabel imageSourceLabel = new JLabel("Source Select");
		dpiPanel.add(imageSourceLabel);

		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(2, 10));
		separator.setOrientation(SwingConstants.VERTICAL);
		dpiPanel.add(separator);
		dpiPanel.add(ldpiRadioBtn);

		separator = new JSeparator();
		separator.setPreferredSize(new Dimension(2, 10));
		separator.setOrientation(SwingConstants.VERTICAL);
		dpiPanel.add(separator);
		dpiPanel.add(tvdpiRadioBtn);

		separator = new JSeparator();
		separator.setPreferredSize(new Dimension(2, 10));
		separator.setOrientation(SwingConstants.VERTICAL);
		dpiPanel.add(separator);
		dpiPanel.add(mdpiRadioBtn);

		separator = new JSeparator();
		separator.setPreferredSize(new Dimension(2, 10));
		separator.setOrientation(SwingConstants.VERTICAL);
		dpiPanel.add(separator);
		dpiPanel.add(hdpiRadioBtn);

		separator = new JSeparator();
		separator.setPreferredSize(new Dimension(2, 10));
		separator.setOrientation(SwingConstants.VERTICAL);
		dpiPanel.add(separator);
		dpiPanel.add(xhdpiRadioBtn);

		separator = new JSeparator();
		separator.setPreferredSize(new Dimension(2, 10));
		separator.setOrientation(SwingConstants.VERTICAL);
		dpiPanel.add(separator);
		dpiPanel.add(xxhdpiRadioBtn);

		Button resizeBtn = new Button("Resize");
		resizeBtn.setPreferredSize(new Dimension(70, 25));
		resizeBtn.addMouseListener(new ConvertButtonActionMouseListener());
		buttonsPanel.add(resizeBtn);

		JPanel previewPanel = new JPanel();
		previewPanel.setBorder(null);
		previewPanel.setPreferredSize(new Dimension(800, 400));
		mainWindow.getContentPane().add(previewPanel);
		previewPanel.setLayout(new GridLayout(1, 2, 0, 0));

		imageFileListPanel = new JPanel();
		imageFileListPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		imageFileListPanel.setBackground(Color.PINK);
		previewPanel.add(imageFileListPanel);
		imageFileListPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel imageListLabel = new JLabel("Image List");
		imageFileListPanel.add(imageListLabel);

		imageFileList = new JList<String>();
		imageFileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		imageFileList.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		imageFileList.addMouseListener(new ImageFileListActionMouseListener());
		imageFileList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));

		JScrollPane scrollPane = new JScrollPane(imageFileList);
		scrollPane.setPreferredSize(new Dimension(370, 350));
		imageFileListPanel.add(scrollPane);

		JPanel previewImagePanel = new JPanel();
		previewImagePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		previewImagePanel.setBackground(Color.ORANGE);
		previewPanel.add(previewImagePanel);
		previewImagePanel.setLayout(new BoxLayout(previewImagePanel,
				BoxLayout.PAGE_AXIS));

		JPanel selectedImageLabelPanel = new JPanel();
		selectedImageLabelPanel.setMaximumSize(new Dimension(400, 26));
		selectedImageLabelPanel.setPreferredSize(new Dimension(400, 26));
		selectedImageLabelPanel.setBackground(Color.ORANGE);
		previewImagePanel.add(selectedImageLabelPanel);

		JLabel previewLabel = new JLabel("Selected Image Preview");
		selectedImageLabelPanel.add(previewLabel);
		previewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		previewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		previewLabel.setBorder(null);
		previewLabel.setAlignmentX(0.5f);

		previewImageParentPanel = new JPanel();
		previewImageParentPanel.setMaximumSize(new Dimension(370, 350));
		previewImageParentPanel.setBackground(SystemColor.info);
		previewImageParentPanel.setBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null));
		previewImageParentPanel.setPreferredSize(new Dimension(370, 350));
		previewImagePanel.add(previewImageParentPanel);
		previewImageParentPanel.setLayout(new BoxLayout(
				previewImageParentPanel, BoxLayout.LINE_AXIS));
	}

	/**
	 * @return
	 */
	public String getDpiSelectedButtonText() {
		Enumeration<AbstractButton> buttons = dpiButtonGroup.getElements();
		if (buttons == null || !buttons.hasMoreElements()) {
			return null;
		}
		while (buttons.hasMoreElements()) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return button.getText();
			}
		}

		return null;
	}

	/**
	 * @author HanaLee <voyaging.hana@gmail.com>
	 * 
	 */
	protected class ConvertButtonActionMouseListener extends MouseAdapter {

		/**
		 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			if (imgFileListModel == null) {
				JOptionPane.showMessageDialog(mainWindow,
						"Please select image files or directory", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				String selectedDpi = getDpiSelectedButtonText();
				if (StringUtils.isBlank(selectedDpi)) {
					JOptionPane.showMessageDialog(mainWindow,
							"Please select original image dpi", "Wraning",
							JOptionPane.WARNING_MESSAGE);
				} else if (!selectedDpi.equals("ldpi")) {
					Enumeration<File> imgFiles = imgFileListModel.elements();
					boolean result = imageResize(imgFiles, selectedDpi);
					if (result) {
						JOptionPane.showMessageDialog(mainWindow,
								"Image resize work done.", "Info",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(mainWindow,
								"Image resize work fail.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else if (selectedDpi.equals("ldpi")) {
					JOptionPane.showMessageDialog(mainWindow,
							"Do not resize ldpi images", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	/**
	 * @param imgFiles
	 * @param selectedDpi
	 * @return
	 */
	protected boolean imageResize(Enumeration<File> imgFiles, String selectedDpi) {
		if (imgFiles == null) {
			return false;
		}

		int idx = dpisModel.indexOf(selectedDpi);
		for (int i = 0; i <= idx; i++) {
			dpisModel.remove(0);
		}

		boolean result = false;
		while (imgFiles.hasMoreElements()) {
			File imgFile = imgFiles.nextElement();
			BufferedImage oriImage = null;
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

					Map<String, Integer> dimension = null;

					int count = 0;
					for (String dpi : dpisModel) {
						String newDirectoryName = imgFile.getParent()
								+ File.separator + dpi;

						File outputDir = new File(newDirectoryName);
						if (!outputDir.exists() && !outputDir.mkdir()) {
							JOptionPane.showMessageDialog(mainWindow,
									"Make directory failed", "Error",
									JOptionPane.ERROR_MESSAGE);
							return false;
						}

						File outputFile = new File(newDirectoryName,
								imgFile.getName());

						dimension = reCalculateSize(
								oriImage.getWidth(),
								oriImage.getHeight(),
								dpisCalModel.get(selectedDpi).get(count),
								selectedDpi.equals("mdpi") && count == 0 ? false
										: true);

						Image newImage = oriImage.getScaledInstance(
								dimension.get("width"),
								dimension.get("height"), Image.SCALE_SMOOTH);

						result = ImageIO.write(
								convertingImageToBufferedImage(newImage,
										dimension.get("width"),
										dimension.get("height")), reader
										.getFormatName(), outputFile);
						count++;
					}
				}
			} catch (IOException ex) {
				ex.printStackTrace();
				return false;
			}
		}
		return result;
	}

	/**
	 * @param width
	 * @param height
	 * @param number
	 * @param div
	 * @return
	 */
	protected Map<String, Integer> reCalculateSize(int width, int height,
			double number, boolean div) {
		Double widthResult = null;
		Double heightResul = null;
		if (div) {
			widthResult = width / number;
			heightResul = height / number;
		} else {
			widthResult = width * number;
			heightResul = height * number;
		}
		Long lwResult = Math.round(widthResult);
		Long lhResult = Math.round(heightResul);
		Map<String, Integer> dimension = new HashMap<String, Integer>();
		dimension.put("width", lwResult.intValue());
		dimension.put("height", lhResult.intValue());
		return dimension;
	}

	/**
	 * @param image
	 * @param width
	 * @param height
	 * @return
	 */
	protected BufferedImage convertingImageToBufferedImage(Image image,
			int width, int height) {
		BufferedImage after = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		Graphics bg = after.getGraphics();
		bg.drawImage(image, 0, 0, null);
		bg.dispose();
		return after;
	}

	protected class ImageFileListActionMouseListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			@SuppressWarnings("unchecked")
			JList<String> fileList = (JList<String>) e.getSource();
			final File imgFile = imgFileListModel.getElementAt(fileList
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
				previewImageParentPanel.removeAll();
				previewImageParentPanel.add(component);
				previewImageParentPanel.revalidate();
			}
		}
	}

	/**
	 * @author HanaLee <voyaging.hana@gmail.com>
	 * 
	 */
	protected class DirOpenAction extends AbstractAction {

		private static final long serialVersionUID = -6298572972089576940L;

		public DirOpenAction() {
			putValue(NAME, "Open");
			putValue(SHORT_DESCRIPTION, "Open files or directory");
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileDlg = new JFileChooser();
			fileDlg.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			fileDlg.setFileFilter(new ImageFilter());
			fileDlg.setMultiSelectionEnabled(true);
			fileDlg.setAcceptAllFileFilterUsed(false);
			fileDlg.setFileView(new ImageFileView());
			fileDlg.setAccessory(new ImagePreview(fileDlg));

			int result = fileDlg.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				File[] imgFiles = fileDlg.getSelectedFiles();
				if (imgFiles != null && imgFiles.length > 0) {
					if (imgFiles.length == 1 && imgFiles[0].isDirectory()) {
						imgFiles = imgFiles[0].listFiles(new ImageFilter());
					}
					makeImageList(imgFiles);
				}
			}
		}

		/**
		 * @param imgFiles
		 */
		protected void makeImageList(File[] imgFiles) {
			DefaultListModel<String> defaultListModel = new DefaultListModel<String>();
			imgFileListModel = new DefaultListModel<File>();
			for (File imgFile : imgFiles) {
				try {
					if (imgFile.isDirectory()) {
						continue;
					}
					imgFileListModel.addElement(imgFile);
					defaultListModel.addElement(getImageInformation(imgFile));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			imageFileList.setModel(defaultListModel);
		}

		/**
		 * @param imgFile
		 * @return
		 * @throws IOException
		 */
		protected String getImageInformation(File imgFile) throws IOException {
			BufferedImage image = ImageIO.read(imgFile);
			ImageIcon icon = new ImageIcon(image);
			int height = icon.getIconHeight();
			int width = icon.getIconWidth();
			StringBuilder sb = new StringBuilder();
			sb.append("name : ");
			sb.append(imgFile.getName());
			sb.append(" | ");
			sb.append("size : ");
			sb.append(width);
			sb.append(" x ");
			sb.append(height);
			return sb.toString();
		}
	}

	private class ExitAction extends AbstractAction {

		private static final long serialVersionUID = 5184695362515645756L;

		public ExitAction() {
			putValue(NAME, "Exit");
			putValue(SHORT_DESCRIPTION, "Programe exit");
		}

		public void actionPerformed(ActionEvent e) {
			String message = "Are You Sure to Close this Application?";
			String title = "Exit Confirmation";
			int reply = JOptionPane.showConfirmDialog(mainWindow, message,
					title, JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}
}
