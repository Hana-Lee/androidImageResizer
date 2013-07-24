package kr.co.hanalee.component;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.Canvas;
import java.awt.Button;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Toolkit;

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
		mainWindow.setTitle("Android Image Resizer");
		mainWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/com/sun/java/swing/plaf/motif/icons/DesktopIcon.gif")));
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
		mainWindow.getContentPane().setLayout(new BoxLayout(mainWindow.getContentPane(), BoxLayout.PAGE_AXIS));
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBorder(new EmptyBorder(3, 3, 5, 3));
		buttonsPanel.setMinimumSize(new Dimension(800, 40));
		buttonsPanel.setBackground(UIManager.getColor("InternalFrame.borderLight"));
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		mainWindow.getContentPane().add(buttonsPanel);
		
		Button ldpiBtn = new Button("ldpi");
		ldpiBtn.setPreferredSize(new Dimension(70, 25));
		buttonsPanel.add(ldpiBtn);
		
		Button hdpiBtn = new Button("hdpi");
		hdpiBtn.setPreferredSize(new Dimension(70, 25));
		buttonsPanel.add(hdpiBtn);
		
		Button xhtpiBtn = new Button("xhdpi");
		xhtpiBtn.setPreferredSize(new Dimension(70, 25));
		buttonsPanel.add(xhtpiBtn);
		
		Button xxhdpiBtn = new Button("xxhdpi");
		xxhdpiBtn.setPreferredSize(new Dimension(70, 25));
		buttonsPanel.add(xxhdpiBtn);
		
		Button tvdpiBtn = new Button("tvdpi");
		tvdpiBtn.setPreferredSize(new Dimension(70, 25));
		buttonsPanel.add(tvdpiBtn);
		
		Button alldpiBtn = new Button("all dpi");
		alldpiBtn.setPreferredSize(new Dimension(70, 25));
		buttonsPanel.add(alldpiBtn);
		
		JPanel previewPanel = new JPanel();
		previewPanel.setBorder(null);
		previewPanel.setPreferredSize(new Dimension(800, 400));
		mainWindow.getContentPane().add(previewPanel);
		previewPanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel originalImagePanel = new JPanel();
		originalImagePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		originalImagePanel.setBackground(Color.PINK);
		previewPanel.add(originalImagePanel);
		originalImagePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 170));
		
		JLabel noImageLabel = new JLabel("No Original Image");
		noImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		originalImagePanel.add(noImageLabel);
		
		JPanel resizeImagePanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) resizeImagePanel.getLayout();
		flowLayout.setVgap(170);
		resizeImagePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		resizeImagePanel.setBackground(Color.ORANGE);
		previewPanel.add(resizeImagePanel);
		
		JLabel resizeImageLabel = new JLabel("Preview Resize result");
		resizeImagePanel.add(resizeImageLabel);
	}

}
