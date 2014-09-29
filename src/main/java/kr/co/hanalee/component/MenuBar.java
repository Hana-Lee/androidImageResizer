package kr.co.hanalee.component;

import kr.co.hanalee.context.RenderContext;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * @author <a href="mailto:voyaging@leehana.co.kr">Hana Lee</a>
 *         <p>
 *         Created by Hana Lee on 2014. 9. 29..
 */
public class MenuBar extends JMenuBar {

	private RenderContext context;

	public MenuBar(RenderContext context) {
		super();
		this.context = context;
		init();
	}

	private void init() {
		JMenu mnFile = new JMenu("File");
		JMenuItem mntmOpenFile = new JMenuItem("Open");
		mntmOpenFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (context.getImageFileChooseDialog().isShowing() == Boolean.FALSE) {
					context.getImageFileChooseDialog().showDialog(context.getMainFrame(), null);
				}
			}
		});
		mntmOpenFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_MASK));
		mnFile.add(mntmOpenFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setAction(new ExitAction());
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				InputEvent.CTRL_MASK));
		mnFile.add(mntmExit);

		JMenu mnHelp = new JMenu("Help");
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);

		add(mnFile);
		add(mnHelp);
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
			int reply = JOptionPane.showConfirmDialog(context.getMainFrame(), message,
					title, JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}
}
