package kr.co.hanalee.component;

import kr.co.hanalee.context.RenderContext;

import javax.swing.*;

/**
 * @author HanaLee <voyaging@leehana.co.kr>
 */
public class MainWindow {

	public MainWindow(RenderContext context) {
		initialize(context);
	}

	private void initialize(RenderContext context) {
		UIManager.put("OptionPane.font", context.getDefaultFontName());
		UIManager.put("Label.font", context.getDefaultFontName());
		UIManager.put("Button.font", context.getDefaultFontName());
		UIManager.put("FileChooser.font", context.getDefaultFontName());

		context.setButtonsPanel(new ButtonsPanel(context));
		context.setMenuBar(new MenuBar(context));
		context.setImageFileChooseDialog(new ImageFileChooseDialog(context));

		context.setImageFileListDisplayPanel(new ImageFileListDisplayPanel(context));
		context.setImageDisplayPanel(new ImageDisplayPanel());
		context.setImageDisplayParentPanel(new ImageDisplayParentPanel());
		context.setPreviewPanel(new PreviewPanel());
	}

	public void render(RenderContext context) {
		context.render();
	}
}
