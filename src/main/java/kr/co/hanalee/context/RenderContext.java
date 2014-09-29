package kr.co.hanalee.context;

import kr.co.hanalee.component.*;
import kr.co.hanalee.component.MenuBar;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Hana Lee on 2014. 9. 29..
 */
public class RenderContext {

	private static final String DEFAULT_FONT_NAME = "Arial";
	private static final Font DEFAULT_FONT = new Font("Arial", Font.PLAIN, 12);

	private JFrame mainFrame;
	private MenuBar menuBar;
	private ButtonsPanel buttonsPanel;
	private PreviewPanel previewPanel;

	private ImageFileListDisplayPanel imageFileListDisplayPanel;
	private ImageDisplayParentPanel imageDisplayParentPanel;
	private ImageDisplayPanel imageDisplayPanel;

	private JList imageFileList;
	private DefaultListModel imageFileListModel;

	private Font defaultFont;
	private String defaultFontName;

	private ImageFileChooseDialog imageFileChooseDialog;

	public void render() {
		mainFrame = new JFrame();
		mainFrame.setResizable(false);
		mainFrame.setTitle("Image Resizer");
		mainFrame
				.setIconImage(Toolkit
						.getDefaultToolkit()
						.getImage(
								MainWindow.class
										.getResource("/com/sun/java/swing/plaf/motif/icons/DesktopIcon.gif")));
		mainFrame.setPreferredSize(new Dimension(800, 500));
		mainFrame.setBounds(100, 100, 450, 300);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();

		mainFrame.setJMenuBar(menuBar);

		mainFrame.getContentPane()
				.setLayout(
						new BoxLayout(mainFrame.getContentPane(),
								BoxLayout.PAGE_AXIS));

		mainFrame.getContentPane().add(buttonsPanel);

		imageDisplayPanel.add(imageDisplayParentPanel);

		previewPanel.add(imageFileListDisplayPanel);
		previewPanel.add(imageDisplayPanel);

		mainFrame.getContentPane().add(previewPanel);
	}

	public String getDefaultFontName() {
		GraphicsEnvironment e = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		Font[] fonts = e.getAllFonts();

		for (Font font : fonts) {
			if (font.getName().equals("나눔고딕")) {
				this.defaultFont = font;
				this.defaultFontName = font.getName();
			}
			if (font.getName().equals("맑은 고딕")) {
				this.defaultFont = font;
				this.defaultFontName = font.getName();
			}
			if (font.getName().equals("굴림")) {
				this.defaultFont = font;
				this.defaultFontName = font.getName();
			}
		}

		if (StringUtils.isBlank(defaultFontName)) {
			this.defaultFont = DEFAULT_FONT;
			this.defaultFontName = DEFAULT_FONT_NAME;
		}

		return defaultFontName;
	}

	public Font getDefaultFont() {
		if (this.defaultFont == null) {
			getDefaultFontName();
		}

		return defaultFont;
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public MenuBar getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(MenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public ButtonsPanel getButtonsPanel() {
		return buttonsPanel;
	}

	public void setButtonsPanel(ButtonsPanel buttonsPanel) {
		this.buttonsPanel = buttonsPanel;
	}

	public PreviewPanel getPreviewPanel() {
		return previewPanel;
	}

	public void setPreviewPanel(PreviewPanel previewPanel) {
		this.previewPanel = previewPanel;
	}

	public ImageDisplayParentPanel getImageDisplayParentPanel() {
		if (imageDisplayParentPanel == null) {
			this.imageDisplayParentPanel = new ImageDisplayParentPanel();
		}
		return imageDisplayParentPanel;
	}

	public void setImageDisplayParentPanel(ImageDisplayParentPanel imageDisplayParentPanel) {
		this.imageDisplayParentPanel = imageDisplayParentPanel;
	}

	public JList getImageFileList() {
		if (imageFileList == null) {
			this.imageFileList = new ImageFileList(this);
		}
		return imageFileList;
	}

	public void setImageFileList(JList imageFileList) {
		this.imageFileList = imageFileList;
	}

	public DefaultListModel getImageFileListModel() {
		if (imageFileListModel == null) {
			this.imageFileListModel = new DefaultListModel();
		}
		return imageFileListModel;
	}

	public void setImageFileListModel(DefaultListModel imageFileListModel) {
		this.imageFileListModel = imageFileListModel;
	}

	public ImageFileChooseDialog getImageFileChooseDialog() {
		return imageFileChooseDialog;
	}

	public void setImageFileChooseDialog(ImageFileChooseDialog imageFileChooseDialog) {
		this.imageFileChooseDialog = imageFileChooseDialog;
	}

	public ImageFileListDisplayPanel getImageFileListDisplayPanel() {
		return imageFileListDisplayPanel;
	}

	public void setImageFileListDisplayPanel(ImageFileListDisplayPanel imageFileListDisplayPanel) {
		this.imageFileListDisplayPanel = imageFileListDisplayPanel;
	}

	public ImageDisplayPanel getImageDisplayPanel() {
		return imageDisplayPanel;
	}

	public void setImageDisplayPanel(ImageDisplayPanel imageDisplayPanel) {
		this.imageDisplayPanel = imageDisplayPanel;
	}
}
