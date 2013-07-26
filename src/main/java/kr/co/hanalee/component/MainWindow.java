package kr.co.hanalee.component;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
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
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
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

public class MainWindow {

	private JFrame mainWindow;
	private JPanel imageFileListPanel;
	private JList<String> imageFileList;
	private DefaultListModel<File> imgFileListModel;
	private JPanel previewImageParentPanel;
	private final Action action = new DirOpenAction();
	private static final String DEFAULT_FONT = "Arial";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.mainWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

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

		JMenuItem mntmOpenFile = new JMenuItem("Open Dir");
		mntmOpenFile.setAction(action);
		mntmOpenFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_MASK));
		mnFile.add(mntmOpenFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);

		JMenu mnSetting = new JMenu("Setting");
		menuBar.add(mnSetting);

		JMenuItem mntmSettings = new JMenuItem("Preferences");
		mnSetting.add(mntmSettings);

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

		ButtonGroup dpiButtonGroup = new ButtonGroup();
		dpiButtonGroup.add(ldpiRadioBtn);
		dpiButtonGroup.add(mdpiRadioBtn);
		dpiButtonGroup.add(hdpiRadioBtn);
		dpiButtonGroup.add(xhdpiRadioBtn);
		dpiButtonGroup.add(xxhdpiRadioBtn);
		dpiButtonGroup.add(tvdpiRadioBtn);

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

		separator = new JSeparator();
		separator.setPreferredSize(new Dimension(2, 10));
		separator.setOrientation(SwingConstants.VERTICAL);
		dpiPanel.add(separator);
		dpiPanel.add(tvdpiRadioBtn);

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

	protected class ConvertButtonActionMouseListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println(e.getClickCount());
			JOptionPane.showMessageDialog(mainWindow, "선택해주삼", "헐...",
					JOptionPane.WARNING_MESSAGE);
			super.mouseClicked(e);
		}
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

	protected class DirOpenAction extends AbstractAction {

		private static final long serialVersionUID = -6298572972089576940L;

		public DirOpenAction() {
			putValue(NAME, "Open");
			putValue(SHORT_DESCRIPTION, "Open files or directory");
		}

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
}
