package kr.co.hanalee.component;

import kr.co.hanalee.util.*;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author HanaLee <voyaging.hana@gmail.com>
 * 
 */
public class MainWindow {

	private static final String DEFAULT_FONT = "Arial";
	private final Action action = new DirOpenAction();
	private final Action action_1 = new ExitAction();
	private JFrame mainWindow;
	private JPanel imageFileListPanel;
	@SuppressWarnings("rawtypes")
	private JList imageFileList;
	@SuppressWarnings("rawtypes")
	private DefaultListModel imgFileListModel;
	private JPanel previewImageParentPanel;
	private ButtonGroup dpiButtonGroup;

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
	@SuppressWarnings("rawtypes")
	private void initialize() {
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

		JRadioButton ldpiRadioBtn = new JRadioButton(DPIName.ldpi.getName());

		JRadioButton mdpiRadioBtn = new JRadioButton(DPIName.mdpi.getName());

		JRadioButton hdpiRadioBtn = new JRadioButton(DPIName.hdpi.getName());

		JRadioButton xhdpiRadioBtn = new JRadioButton(DPIName.xhdpi.getName());

		JRadioButton xxhdpiRadioBtn = new JRadioButton(DPIName.xxhdpi.getName());

		JRadioButton tvdpiRadioBtn = new JRadioButton(DPIName.tvdpi.getName());

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

		imageFileList = new JList();
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
		@SuppressWarnings("unchecked")
		@Override
		public void mouseClicked(MouseEvent e) {
			if (imgFileListModel == null) {
				JOptionPane.showMessageDialog(mainWindow,
						"Please select image files or directory", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				String selectedDpiText = getDpiSelectedButtonText();
				if (StringUtils.isBlank(selectedDpiText)) {
					JOptionPane.showMessageDialog(mainWindow,
							"Please select original image dpi", "Wraning",
							JOptionPane.WARNING_MESSAGE);
				} else if (!selectedDpiText.equals(DPIName.ldpi.getName())) {
					Enumeration<File> imgFiles = (Enumeration<File>) imgFileListModel
							.elements();
					boolean result = ImageResizeUtil.resize(imgFiles, DPIFactory.create(DPIName.valueOf(selectedDpiText)));
					if (result) {
						JOptionPane.showMessageDialog(mainWindow,
								"Image resize work done.", "Info",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(mainWindow,
								"Image resize work fail.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else if (selectedDpiText.equals(DPIName.ldpi.getName())) {
					JOptionPane.showMessageDialog(mainWindow,
							"Do not resize ldpi images", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	protected class ImageFileListActionMouseListener extends MouseAdapter {

		@SuppressWarnings("rawtypes")
		@Override
		public void mouseClicked(MouseEvent e) {
			JList fileList = (JList) e.getSource();
			final File imgFile = (File) imgFileListModel.getElementAt(fileList
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
		@SuppressWarnings({ "unchecked", "rawtypes" })
		protected void makeImageList(File[] imgFiles) {
			DefaultListModel defaultListModel = new DefaultListModel();
			imgFileListModel = new DefaultListModel();
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
