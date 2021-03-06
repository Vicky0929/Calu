package com.zhengye.calc;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javafx.beans.InvalidationListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * 计算器窗口(View)
 * 
 * @author Administrator
 *
 */
public class CalcFrame extends JFrame implements Observer, CalcCallback {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 显示计算结果
	 */
	private JLabel labelResult;

	/**
	 * 按钮上的标签
	 */
	private String[] titles = { "", "", "", "C", "7", "8", "9", "+", "4", "5",
			"6", "-", "1", "2", "3", "*", ".", "0", "=", "/" };
	private JButton[] buttons = new JButton[titles.length];
	// 按钮监听器
	private Controller controller = null;

	/**
	 * 构造方法
	 */

	public CalcFrame(Controller controller) {

		this.controller = controller;
		initUi();
		setVisible(true);
	}

	private void initUi() {

		setTitle("计算器--Vicky作品");
		setSize(320, 480);
		setResizable(false);// 不可调整大小
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// 添加结果标签
		labelResult = new JLabel("0.0");
		labelResult.setBorder(new EmptyBorder(32, 10, 32, 10));
		labelResult.setHorizontalAlignment(SwingConstants.RIGHT);
		labelResult.setFont(new Font("微软雅黑", Font.PLAIN, 32));
		add(labelResult, BorderLayout.NORTH);

		// 添加按钮布局
		JPanel buttonPanel = new JPanel();
		// 设置布局
		buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));
		add(buttonPanel, BorderLayout.CENTER);

		for (int i = 0; i < titles.length; i++) {
			if (titles[i].length() == 0) {
				// 显示内容的标签
				buttonPanel.add(new JLabel(""));
			} else {
				// 按钮
				buttons[i] = new JButton(titles[i]);
				buttons[i].setActionCommand(titles[i]);

				// 按钮的点击事件监听器
				buttons[i].addActionListener(controller);
				buttonPanel.add(buttons[i]);
			}
		}
	}

	/**
	 * 模型更新视图（回调）
	 * 
	 */
	@Override
	public void update(Observable o, Object arg) {

		String result = (String) arg;
		labelResult.setText(result);
	}

	@Override
	public void showResult(String result) {
		// labelResult.setText(result);
	}

}
