package com.iteroa.util;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 显示更新信息
 * 
 * @author Daniel Duan
 *
 */
@SuppressWarnings("serial")
public class ShowInfo extends JFrame {

	private final JTextArea jtextarea = new JTextArea();
	private JScrollPane sp;
	private static ShowInfo showInfo = new ShowInfo();
	private static int num = 0;

	/**
	 * 获取实例，并设置关闭按钮是否关闭整个应用程序
	 * 
	 * @param isClose
	 * @return
	 */
	public static ShowInfo getInstance(boolean isClose, String title) {
		showInfo.setTitle(title);
		// 如果已经关闭则打开该程序并清空之前输出信息
		if (!showInfo.isVisible()) {
			showInfo.jtextarea.setText("");
		}
		// 关闭程序
		if (isClose) {
			showInfo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		showInfo.setVisible(true);
		return showInfo;
	}

	public static synchronized void setContext(String context) {
		num++;
		showInfo.jtextarea.setText("第" + num + "条信息，" + context + "\n" + showInfo.jtextarea.getText());
	}

	private ShowInfo() {
		super();
		this.setSize(650, 400);
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		this.setBackground(Color.red);
		this.add(getJTextArea(), null);
		this.setResizable(false);
	}

	private JScrollPane getJTextArea() {
		sp = new JScrollPane(jtextarea);
		sp.setBounds(0, 0, 630, 360);
		return sp;
	}

	public static void main(String[] args) {
		ShowInfo.getInstance(true, "测试");
		new Thread() {
			public void run() {
				for (int i = 0; i <= 100; i++) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ShowInfo.getInstance(true, "测试").jtextarea
							.setText(i + "\n" + ShowInfo.getInstance(true, "测试").jtextarea.getText());
				}
			}
		}.start();
	}
}