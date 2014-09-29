package kr.co.hanalee.main;

import kr.co.hanalee.component.MainWindow;
import kr.co.hanalee.context.RenderContext;

import java.awt.*;

/**
 * @author <a href="mailto:voyaging@leehana.co.kr">Hana Lee</a>
 *         <p>
 *         Created by Hana Lee on 2014. 9. 29..
 */
public class Resizer {

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
