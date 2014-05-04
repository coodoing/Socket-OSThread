package com.os;


import java.awt.*;
import java.awt.event.*;
import java.sql.Time;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.*;

import com.air.os.processCtrl.processCtrl;

public class frame extends JFrame {

	/**
	 * 进程调度模拟
	 */

	private javax.swing.Timer time;

	private static final long serialVersionUID = -8203814851876525357L;
	private static final int DEFAULT_HEIGHT = 930;
	private static final int DEFAULT_WIDTH = 930;

	private JPanel mainPanel1;
	private JPanel mainPanel2;
	private JPanel processPanel;// 所有进程的panel
	private JPanel buttonPanel;// 按钮panel
	private JPanel choosePanel;// 选择调度算法panel
	private ButtonGroup group1;
	private ButtonGroup group2;

	private JPanel waitPanel;
	private JPanel readyPanel;
	private JPanel runPanel;
	private JPanel complPanel;
	
	private JPanel proWaitPanel;
	private JPanel proReadyPanel;
	private JPanel proRunPanel;
	private JPanel proComplPanel;// 完成panel
	
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	
	private JPanel pan1;
	private JPanel pan2;
	private JPanel pan3;

	private JLabel runLabel;

	private JButton randomProButton;
	private JButton handProButton;
	private JButton startButton;
	private JButton exitButton;
	/*
	 * private JButton changePrioButton;//更改进程优先级 从而实现进程之间的切换
	 */
	private JButton blockButton; // 阻塞按钮
	private JButton awokeButton; // 唤醒按钮

	// 请求调度算法中的四个radioButton
	private JRadioButton radioBut1;
	private JRadioButton radioBut2;
	private JRadioButton radioBut3;
	private JRadioButton radioBut4;

	JRadioButton tempRadio = new JRadioButton();// 处理单选钮
	ActionListener listener1 = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			JRadioButton radio = (JRadioButton) event.getSource();			
			tempRadio = radio;

		}
	};
	/*
	 * ActionListener listener2 = new ActionListener() { public void
	 * actionPerformed(ActionEvent event) {
	 *  } };
	 */
	public frame() {

		setTitle("Process Handling(进程调度)");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLayout(null);
		setResizable(false);
		createProInterface();

	}

	/**
	 * 创建用户界面
	 */
	@SuppressWarnings( { "static-access", "static-access", "static-access" })
	public void createProInterface() {
		Container contentPanel = getContentPane();
		//contentPanel.setLayout(new BorderLayout(2, 1));

		choosePanel = new JPanel();
		choosePanel.setBorder(new TitledBorder("进程状态之间的切换"));
		group1 = new ButtonGroup();
		radioBut1 = new JRadioButton("先进先出服务FIFO");
		radioBut1.setSelected(true);
		radioBut2 = new JRadioButton("最短作业优先SJF");
		radioBut3 = new JRadioButton("优先级服务");
		radioBut4 = new JRadioButton("轮转法(时间片)");

		group1.add(radioBut1);
		choosePanel.add(radioBut1);
		group1.add(radioBut2);
		choosePanel.add(radioBut2);
		group1.add(radioBut3);
		choosePanel.add(radioBut3);
		group1.add(radioBut4);
		choosePanel.add(radioBut4);

		mainPanel1=new JPanel();
		mainPanel1.add(choosePanel);
		contentPanel.add(mainPanel1,BorderLayout.NORTH);

		
		
		
		processPanel = new JPanel();
		processPanel.setBorder(new TitledBorder("选择进程调度算法"));
		processPanel.setLayout(new GridLayout(2, 2));
		
		// proWaitPanel.setBackground(new java.awt.Color(255, 255, 255));
		waitPanel = new JPanel();
		label1=new JLabel("等待进程Panel");
		waitPanel.add(label1,BorderLayout.NORTH);
		proWaitPanel = new JPanel();
		proWaitPanel.setLayout(new javax.swing.BoxLayout(proWaitPanel,
				javax.swing.BoxLayout.Y_AXIS));
		waitPanel.add(proWaitPanel,BorderLayout.CENTER);		
		processPanel.add(waitPanel);
		
		// proReadyPanel.setBackground(new java.awt.Color(255, 255, 255));
		readyPanel = new JPanel();
		label2=new JLabel("就绪进程panel");
		readyPanel.add(label2,BorderLayout.NORTH);
		proReadyPanel = new JPanel();
		proReadyPanel.setLayout(new javax.swing.BoxLayout(proReadyPanel,
				javax.swing.BoxLayout.Y_AXIS));
		readyPanel.add(proReadyPanel,BorderLayout.CENTER);
		processPanel.add(readyPanel);
		
		runPanel = new JPanel();
		label3=new JLabel("执行进程Panel");
		runPanel.add(label3,BorderLayout.NORTH);
		//proRunPanel = new JPanel();
		// proRunPanel.setBackground(new java.awt.Color(255, 255, 255));
		runLabel = new JLabel();
		runLabel.setBackground(new java.awt.Color(102, 102, 255) );
		runLabel.setText("sdfg");
		//proRunPanel.add(runLabel,BorderLayout.CENTER);		
		runPanel.add(runLabel, BorderLayout.CENTER);
		processPanel.add(runPanel);
		
		complPanel = new JPanel();
		label4=new JLabel("完成进程panel");
		complPanel.add(label4,BorderLayout.NORTH);
		proComplPanel = new JPanel();
		proComplPanel.setLayout(new javax.swing.BoxLayout(proComplPanel,
				javax.swing.BoxLayout.Y_AXIS));
		// proComplPanel.setBackground(new java.awt.Color(255, 255, 255));
		complPanel.add(proComplPanel,BorderLayout.CENTER);
		processPanel.add(complPanel);

	
		//contentPanel.add(processPanel);
		
		

		buttonPanel = new JPanel();
		buttonPanel.setBorder(new TitledBorder("控制按钮"));
		buttonPanel.setLayout(new GridLayout(3, 1));
		pan1 = new JPanel();
		pan2 = new JPanel();
		pan3 = new JPanel();
		group2 = new ButtonGroup();
		randomProButton = new JButton("随机建立进程");
		handProButton = new JButton("手动建立进程");
		blockButton = new JButton("阻塞");
		awokeButton = new JButton("唤醒");
		startButton = new JButton("开始");
		exitButton = new JButton("退出");

		// changePrioButton = new JButton("修改优先级别(阻塞唤醒)");

		group2.add(randomProButton);
		pan1.add(randomProButton);
		buttonPanel.add(pan1);
		group2.add(handProButton);
		pan1.add(handProButton);
		buttonPanel.add(pan1);
		/*
		 * group2.add(changePrioButton); buttonPanel.add(changePrioButton);
		 */
		group2.add(blockButton);
		pan2.add(blockButton);
		buttonPanel.add(pan2);
		group2.add(awokeButton);
		pan2.add(awokeButton);
		buttonPanel.add(pan2);
		group2.add(startButton);
		pan3.add(startButton);
		buttonPanel.add(pan3);
		group2.add(exitButton);
		pan3.add(exitButton);
		buttonPanel.add(pan3);

		
		mainPanel2=new JPanel();
		mainPanel2.setLayout(new BorderLayout(2,1));
		mainPanel2.add(processPanel);
		mainPanel2.add(buttonPanel);
		contentPanel.add(mainPanel2,BorderLayout.CENTER);
		//contentPanel.add(buttonPanel);

		/*
		 * 
		 * 添加事件响应
		 * 
		 */

		// 添加radio事件响应
		radioBut1.addActionListener(listener1);
		radioBut1.addActionListener(listener1);
		radioBut1.addActionListener(listener1);
		radioBut1.addActionListener(listener1);

		// 添加Button事件响应
		randomProButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//randomProButActionPerformed(event);
			}

		});
		handProButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//handProButActionPerformed(event);
			}

		});
		blockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//blockButActionPerformed(event);
			}

		});

		awokeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//awokeButActionPerformed(event);
			}

		});

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//startButActionPerformed(event);
			}

		});

		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}

		});

		time = new Timer(5, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//startButActionPerformed(event);
			}

		});

	}

	public static void main(String[] args) {
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new frame().setVisible(true);
			}
		});
		new frame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
