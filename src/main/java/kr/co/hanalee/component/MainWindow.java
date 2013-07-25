package kr.co.hanalee.component;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class MainWindow {

	private JFrame mainWindow;

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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainWindow = new JFrame();
		mainWindow.setResizable(false);
		mainWindow.setTitle("Image Resizer");
		mainWindow
				.setIconImage(Toolkit
						.getDefaultToolkit()
						.getImage(
								MainWindow.class
										.getResource("/com/sun/java/swing/plaf/motif/icons/DesktopIcon.gif")));
		mainWindow.setMinimumSize(new Dimension(500, 500));
		mainWindow.setPreferredSize(new Dimension(800, 500));
		mainWindow.setBounds(100, 100, 450, 300);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.pack();

		JMenuBar menuBar = new JMenuBar();
		mainWindow.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpenFile = new JMenuItem("Open File");
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
		buttonsPanel.add(resizeBtn);

		JPanel previewPanel = new JPanel();
		previewPanel.setBorder(null);
		previewPanel.setPreferredSize(new Dimension(800, 400));
		mainWindow.getContentPane().add(previewPanel);
		previewPanel.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel imageFileListPanel = new JPanel();
		imageFileListPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		imageFileListPanel.setBackground(Color.PINK);
		previewPanel.add(imageFileListPanel);
		imageFileListPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel imageListLabel = new JLabel("Image List");
		imageFileListPanel.add(imageListLabel);

		JList<String> imageFileList = new JList<String>();
		imageFileList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		imageFileList.setPreferredSize(new Dimension(370, 350));
		imageFileListPanel.add(imageFileList);

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

		JPanel previewImageParentPanel = new JPanel();
		previewImageParentPanel.setBackground(Color.ORANGE);
		FlowLayout fl_previewImageParentPanel = (FlowLayout) previewImageParentPanel
				.getLayout();
		fl_previewImageParentPanel.setVgap(0);
		previewImageParentPanel.setBorder(new EmptyBorder(0, 5, 5, 5));
		previewImageParentPanel.setPreferredSize(new Dimension(370, 350));
		previewImagePanel.add(previewImageParentPanel);

		JPanel selectedImagePreviewPanel = new JPanel();
		selectedImagePreviewPanel.setBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null));
		selectedImagePreviewPanel.setPreferredSize(new Dimension(370, 350));
		previewImageParentPanel.add(selectedImagePreviewPanel);
		selectedImagePreviewPanel.setLayout(new BoxLayout(
				selectedImagePreviewPanel, BoxLayout.PAGE_AXIS));

		JLabel selectedImageLabel = new JLabel("");
		selectedImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		selectedImagePreviewPanel.add(selectedImageLabel);
	}

}
