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
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
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

		JSeparator separator_5 = new JSeparator();
		separator_5.setPreferredSize(new Dimension(2, 10));
		separator_5.setOrientation(SwingConstants.VERTICAL);
		dpiPanel.add(separator_5);

		dpiPanel.add(ldpiRadioBtn);

		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(2, 10));
		separator.setOrientation(SwingConstants.VERTICAL);
		dpiPanel.add(separator);
		dpiPanel.add(mdpiRadioBtn);

		JSeparator separator_1 = new JSeparator();
		separator_1.setPreferredSize(new Dimension(2, 10));
		separator_1.setOrientation(SwingConstants.VERTICAL);
		dpiPanel.add(separator_1);
		dpiPanel.add(hdpiRadioBtn);

		JSeparator separator_2 = new JSeparator();
		separator_2.setPreferredSize(new Dimension(2, 10));
		separator_2.setOrientation(SwingConstants.VERTICAL);
		dpiPanel.add(separator_2);
		dpiPanel.add(xhdpiRadioBtn);

		JSeparator separator_3 = new JSeparator();
		separator_3.setPreferredSize(new Dimension(2, 10));
		separator_3.setOrientation(SwingConstants.VERTICAL);
		dpiPanel.add(separator_3);
		dpiPanel.add(xxhdpiRadioBtn);

		JSeparator separator_4 = new JSeparator();
		separator_4.setPreferredSize(new Dimension(2, 10));
		separator_4.setOrientation(SwingConstants.VERTICAL);
		dpiPanel.add(separator_4);
		dpiPanel.add(tvdpiRadioBtn);

		Button resizeBtn = new Button("Resize");
		resizeBtn.setPreferredSize(new Dimension(70, 25));
		buttonsPanel.add(resizeBtn);

		JPanel previewPanel = new JPanel();
		previewPanel.setBorder(null);
		previewPanel.setPreferredSize(new Dimension(800, 400));
		mainWindow.getContentPane().add(previewPanel);
		previewPanel.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel originalImagePanel = new JPanel();
		originalImagePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		originalImagePanel.setBackground(Color.PINK);
		previewPanel.add(originalImagePanel);
		originalImagePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(370, 370));
		originalImagePanel.add(scrollPane);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBackground(Color.WHITE);
		scrollPane.setColumnHeaderView(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setSize(new Dimension(340, 1));
		panel.setPreferredSize(new Dimension(340, 10));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.setAutoscrolls(true);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setViewportView(panel);

		JLabel lblNewLabel_2 = new JLabel("Image Icon1");
		lblNewLabel_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		ImageIcon imageIcon = new ImageIcon(
				"C:\\Users\\Hana\\Dropbox\\study\\android\\pinkmini\\imgs\\20130722\\런쳐3.png");
		lblNewLabel_2.setIcon(imageIcon);
		lblNewLabel_2.setPreferredSize(new Dimension(40, 30));

		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Image Icon2");
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("New label");
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("New label");
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_8 = new JLabel("New label");
		panel.add(lblNewLabel_8);

		JLabel lblNewLabel_6 = new JLabel("New label");
		panel.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("New label");
		panel.add(lblNewLabel_7);

		JLabel label = new JLabel("Image Icon1");
		label.setIcon(new ImageIcon(
				"C:\\Users\\Hana\\Dropbox\\study\\android\\pinkmini\\imgs\\20130722\\런쳐3.png"));
		label.setPreferredSize(new Dimension(40, 130));
		label.setAlignmentX(0.5f);
		panel.add(label);

		JLabel label_1 = new JLabel("Image Icon1");
		label_1.setPreferredSize(new Dimension(40, 30));
		label_1.setAlignmentX(0.5f);
		panel.add(label_1);

		JLabel label_2 = new JLabel("Image Icon1");
		label_2.setPreferredSize(new Dimension(40, 30));
		label_2.setAlignmentX(0.5f);
		panel.add(label_2);

		JLabel label_3 = new JLabel("Image Icon1");
		label_3.setPreferredSize(new Dimension(40, 130));
		label_3.setAlignmentX(0.5f);
		panel.add(label_3);

		JLabel label_4 = new JLabel("Image Icon1");
		label_4.setPreferredSize(new Dimension(80, 130));
		label_4.setAlignmentX(0.5f);
		panel.add(label_4);

		JLabel label_5 = new JLabel("Image Icon1");
		label_5.setPreferredSize(new Dimension(80, 130));
		label_5.setAlignmentX(0.5f);
		panel.add(label_5);

		JLabel label_7 = new JLabel("Image Icon1");
		label_7.setPreferredSize(new Dimension(80, 130));
		label_7.setAlignmentX(0.5f);
		panel.add(label_7);

		JLabel label_8 = new JLabel("Image Icon1");
		label_8.setPreferredSize(new Dimension(80, 130));
		label_8.setAlignmentX(0.5f);
		panel.add(label_8);

		JLabel label_10 = new JLabel("Image Icon1");
		label_10.setPreferredSize(new Dimension(80, 130));
		label_10.setAlignmentX(0.5f);
		panel.add(label_10);

		JLabel label_9 = new JLabel("Image Icon1");
		label_9.setPreferredSize(new Dimension(80, 130));
		label_9.setAlignmentX(0.5f);
		panel.add(label_9);

		JLabel label_12 = new JLabel("Image Icon1");
		label_12.setPreferredSize(new Dimension(80, 130));
		label_12.setAlignmentX(0.5f);
		panel.add(label_12);

		JLabel label_11 = new JLabel("Image Icon1");
		label_11.setPreferredSize(new Dimension(80, 130));
		label_11.setAlignmentX(0.5f);
		panel.add(label_11);

		JLabel label_6 = new JLabel("Image Icon1");
		label_6.setPreferredSize(new Dimension(80, 130));
		label_6.setAlignmentX(0.5f);
		panel.add(label_6);

		JPanel resizeImagePanel = new JPanel();
		resizeImagePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		resizeImagePanel.setBackground(Color.ORANGE);
		previewPanel.add(resizeImagePanel);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(370, 370));
		resizeImagePanel.add(scrollPane_1);

		JLabel lblNewLabel_1 = new JLabel("New label");
		scrollPane_1.setColumnHeaderView(lblNewLabel_1);

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(340, 10));
		scrollPane_1.setViewportView(panel_1);
	}

}
