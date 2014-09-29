package kr.co.hanalee.main;

import kr.co.hanalee.component.MainWindow;
import kr.co.hanalee.context.RenderContext;

import java.awt.*;

/**
 * @author HanaLee <voyaging.hana@gmail.com>
 */
public class Resizer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					RenderContext context = new RenderContext();

					MainWindow window = new MainWindow(context);
					window.render(context);
					context.getMainFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
