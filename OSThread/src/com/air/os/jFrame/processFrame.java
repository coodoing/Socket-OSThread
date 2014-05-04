package com.air.os.jFrame;

import java.awt.*;
import java.awt.event.*;
import java.sql.Time;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.*;

import com.air.os.processCtrl.processCtrl;

public class processFrame extends JFrame {

	/**
	 * 进程调度模拟
	 */
	processCtrl proctrl = new processCtrl();

	private javax.swing.Timer time;

	private static final long serialVersionUID = -8203814851876525357L;
	private static final int DEFAULT_HEIGHT = 930;
	private static final int DEFAULT_WIDTH = 930;

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
	public processFrame() {

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
		contentPanel.setLayout(new GridLayout(3, 1));

		choosePanel = new JPanel();
		choosePanel.setBorder(new TitledBorder("选择进程调度算法"));
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

		contentPanel.add(choosePanel);

		processPanel = new JPanel();
		processPanel.setBorder(new TitledBorder("进程状态之间的切换"));
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

		contentPanel.add(processPanel);

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

		contentPanel.add(buttonPanel);

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
				randomProButActionPerformed(event);
			}

		});
		handProButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				handProButActionPerformed(event);
			}

		});
		blockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				blockButActionPerformed(event);
			}

		});

		awokeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				awokeButActionPerformed(event);
			}

		});

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				startButActionPerformed(event);
			}

		});

		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}

		});

		time = new Timer(10, new ActionListener() {  //用于每隔10响应time事件
			public void actionPerformed(ActionEvent event) {
				startButActionPerformed(event);
			}

		});

	}
	
	public void delay(){  //给每个进程一个延迟时间  以此来代表其运行时间
		
		long time = new Date().getTime() + 2000;  //延迟2秒
		while (true) {
			if (new Date().getTime() > time)
				break;
		}

	}
	
	
	/*
	 * 不论何种情况 都是先将线程先放入等待队列
	 * 
	 */
	public void randomProButActionPerformed(ActionEvent e) {

		Random random = new Random();
		int priority = (random.nextInt(4)) % 10 + 2; // 优先级 将时间片的时间设置为优先级
		int runTime = (random.nextInt(8)) % 10 + 1; // 程序执行时间
		/*
		 * long submitTime = System.currentTimeMillis();
		 * System.out.print("submitTime=" + submitTime);
		 */
		JTable table = proctrl
				.createProcess(priority, runTime/* , submitTime */);
		System.out.println(table);
		proWaitPanel.removeAll();// 每次将前一次的界面中的数据全部删除
		proWaitPanel.add(new JScrollPane(table),BorderLayout.CENTER);// 与JScrollPane连用
		proWaitPanel.setVisible(true);
		proWaitPanel.updateUI();// 更新proReadyPanel这个界面

	}

	public void handProButActionPerformed(ActionEvent e) {

		JFrame frame1 = new JFrame();
		frame1.setTitle("手动添加进程");
		frame1.setSize(230, 230);

		JPanel pane1 = new JPanel();
		JLabel label1 = new JLabel("进程的优先级:priority");
		JLabel label2 = new JLabel("进程的执行时间：runTime");

		final JTextField txf1 = new JTextField(23);
		final JTextField txf2 = new JTextField(23);

		JButton submBut = new JButton("提交创建"); // 提交按钮

		submBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/* long submitTime = System.currentTimeMillis(); */
				//System.out.print(Integer.parseInt(txf1.getText()));
				JTable table;
				table = proctrl.createProcess(Integer.parseInt(txf1.getText()),
						Integer.parseInt(txf2.getText())/* , submitTime */);
				// table=proctrl.createProcess(4, 6);
				proWaitPanel.removeAll();
//				proWaitPanel.setLayout(new BorderLayout());
				proWaitPanel.add(new JScrollPane(table), BorderLayout.CENTER);
				proWaitPanel.setVisible(true);
				proWaitPanel.updateUI();
			}
		});

		pane1.setLayout(new GridLayout(3, 2));

		pane1.add(label1);
		pane1.add(txf1);
		pane1.add(label2);
		pane1.add(txf2);
		pane1.add(submBut);

		// frame1.getContentPane().add(pane1);
		frame1.add(pane1);

		frame1.setVisible(true);
		frame1.setResizable(false);
        frame1.pack();
	}

	/*
	 * 此处的block阻塞 主要是是执行到等待状态 (其实也可以将进程的执行转化为就绪状态) 这里假设就绪队列已满
	 * */
	public void blockButActionPerformed(ActionEvent e) {

		Vector v = new Vector(20);
		if(radioBut1.isSelected()==true)
		{
			v=proctrl.setSchedule(1);   //FIFO算法
			/*System.out.println("撒旦国际卡里说过");  //看其是否执行
*/				proctrl.complQueue.add(v);
		}
		
		if(radioBut2.isSelected()==true)
		{
			v=proctrl.setSchedule(2);   //SJF算法
			proctrl.complQueue.add(v);
		}
		if(radioBut3.isSelected()==true)
		{
			v=proctrl.setSchedule(3);   //FIFO算法
			proctrl.complQueue.add(v);
		}
		if(radioBut4.isSelected()==true)
		{
			v=proctrl.setSchedule(4);   //FIFO算法
			if(proctrl.timeSlice==1)
			proctrl.complQueue.add(v);
		}
		
			//int proId=(Integer)v.get(0);
			proctrl.runToWait(v);
	}

	/*
	 * 
	 * 唤醒队列则是  将等待状态转化为就绪状态
	 * 
	 * 唤醒等待队列进入就绪   前提是就绪队列有空
	 * */ 
	public void awokeButActionPerformed(ActionEvent e) {

		JFrame frame1 = new JFrame();
		frame1.setTitle("手动添加进程");
		frame1.setSize(230, 230);

		JPanel pane = new JPanel();
		JLabel label = new JLabel("进程的优先级:priority");

		final JTextField jtf = new JTextField(23);

		JButton awokeBut = new JButton("唤醒"); // 唤醒按钮

		
		awokeBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(proctrl.readyQueue.size()<proctrl.numMax)
					proctrl.waitToReady1(Integer.parseInt(jtf.getText()));
				proWaitPanel.removeAll();
				proReadyPanel.removeAll();
				JScrollPane jsp1 = new JScrollPane(proctrl.getReady());
				proReadyPanel.add(jsp1);
				JScrollPane jsp2 = new JScrollPane(proctrl.getWait());
				proWaitPanel.add(jsp2);
			}
		});
		pane.setLayout(new GridLayout(2,1));
		pane.add(label);
		pane.add(jtf);
		pane.add(awokeBut);
		frame1.add(pane);
		
		frame1.setVisible(true);
		frame1.setResizable(false);
        frame1.pack();
		
		
	}

	public void startButActionPerformed(ActionEvent e) {

		if (e.getSource() == startButton) {   //若是按 开始 按钮
			if (e.getActionCommand().equals("开始")) {
				//proctrl.waitToReady();
				proComplPanel.removeAll();
				proctrl.complQueue.clear();
				time.start();
				startButton.setText("结束");
			}
			if (e.getActionCommand().equals("结束")) {
				time.stop();
				startButton.setText("开始");
			}

		} 
		else if (e.getSource() == time)  //若资源是time这一资源
		{
			Vector v = new Vector(20);
			String runStr = "";  //用来显示 执行进程中的各个不同进程值

			if (proctrl.canSchedule() == false) { //看等待  就绪队列是否为空,若为空退出
				proReadyPanel.removeAll();
				proWaitPanel.removeAll();

				startButton.setText("开始");
				runLabel.setText("");
				JScrollPane jsp1 = new JScrollPane(proctrl.getReady());
				proReadyPanel.add(jsp1);
				JScrollPane jsp2 = new JScrollPane(proctrl.getWait());
				proWaitPanel.add(jsp2);
				time.stop();
				return;
			}
			
			delay();
			
			if(radioBut1.isSelected()==true)
			{
				v=proctrl.setSchedule(1);   //FIFO算法
				/*System.out.println("撒旦国际卡里说过");  //看其是否执行
*/				proctrl.complQueue.add(v);
			}
			
			if(radioBut2.isSelected()==true)
			{
				v=proctrl.setSchedule(2);   //SJF算法
				proctrl.complQueue.add(v);
			}
			if(radioBut3.isSelected()==true)
			{
				v=proctrl.setSchedule(3);   //FIFO算法
				proctrl.complQueue.add(v);
			}
			if(radioBut4.isSelected()==true)
			{
				v=proctrl.setSchedule(4);   //FIFO算法
				if(proctrl.timeSlice==1)
				proctrl.complQueue.add(v);
			}
			
			// 获取运行状态值是在readyQueue存在的情况下			
			String aa = "运行队列ID:" + v.get(0);
			String bb = "进程优先级:" + v.get(1);
			String cc = "进程时间片:" + v.get(2);
			String dd = "进程执行时间:" + v.get(3);
			runStr = "<html>" + aa + "<br>" + bb + "<br>" + cc + "<br>" + dd
					+ "<br>" + "</html>";
			
			proReadyPanel.removeAll();
			proWaitPanel.removeAll();
			proComplPanel.removeAll();
			
			startButton.setText("开始");
			runLabel.setText(runStr);
			
			JScrollPane sp1 = new JScrollPane(proctrl.getReady());
			proReadyPanel.add(sp1);			
			JScrollPane sp2 = new JScrollPane(proctrl.getWait());
			proWaitPanel.add(sp2);
			JScrollPane sp3 = new JScrollPane(proctrl.getCompl());
			proComplPanel.add(sp3);
			startButton.setText("结束");
			
		}
	}

	public static void main(String[] args) {
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new processFrame().setVisible(true);
			}
		});
		new processFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
