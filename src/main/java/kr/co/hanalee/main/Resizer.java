package kr.co.hanalee.main;

import java.awt.EventQueue;

import kr.co.hanalee.component.MainWindow;

/**
 * @author HanaLee <voyaging.hana@gmail.com>
 *
 */
public class Resizer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.getMainWindow().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
