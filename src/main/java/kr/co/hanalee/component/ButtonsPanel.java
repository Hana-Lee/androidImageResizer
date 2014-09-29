package kr.co.hanalee.component;

import kr.co.hanalee.context.RenderContext;
import kr.co.hanalee.util.DPIButtonFactory;
import kr.co.hanalee.util.DPIFactory;
import kr.co.hanalee.util.DPIName;
import kr.co.hanalee.util.ImageResizeUtil;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

/**
 * @author <a href="mailto:voyaging@leehana.co.kr">Hana Lee</a>
 *     
 * Created by Hana Lee on 2014. 9. 29..
 */
public class ButtonsPanel extends JPanel {

	private RenderContext context;

	public ButtonsPanel(RenderContext context) {
		super();
		this.context = context;
		init();
	}

	private void init() {
		setPreferredSize(new Dimension(800, 53));
		setBorder(new EmptyBorder(3, 3, 5, 3));
		setBackground(UIManager
				.getColor("InternalFrame.borderLight"));
		setLayout(new FlowLayout(FlowLayout.CENTER));

		add(new DPIButtonsPanel());

		Button resizeBtn = new Button("Resize");
		resizeBtn.setPreferredSize(new Dimension(70, 25));
		resizeBtn.addMouseListener(new ConvertButtonActionMouseListener());
		add(resizeBtn);
	}

	private String getDpiSelectedButtonText() {
		Enumeration<AbstractButton> buttons = DPIButtonFactory.getDPIButtonGroup().getElements();
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

	protected class ConvertButtonActionMouseListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (context.getImageFileListModel() == null || context.getImageFileListModel().isEmpty()) {
				JOptionPane.showMessageDialog(context.getMainFrame(),
						"Please select image files or directory", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				String selectedDpiText = getDpiSelectedButtonText();
				if (StringUtils.isBlank(selectedDpiText)) {
					JOptionPane.showMessageDialog(context.getMainFrame(),
							"Please select original image dpi", "Wraning",
							JOptionPane.WARNING_MESSAGE);
				} else if (!selectedDpiText.equals(DPIName.ldpi.getName())) {
					Enumeration imgFiles = context.getImageFileListModel()
							.elements();
					ImageResizeUtil.resize(imgFiles,
							DPIFactory.create(DPIName.valueOf(selectedDpiText)), context);

				} else if (selectedDpiText.equals(DPIName.ldpi.getName())) {
					JOptionPane.showMessageDialog(context.getMainFrame(),
							"Do not resize ldpi images", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
