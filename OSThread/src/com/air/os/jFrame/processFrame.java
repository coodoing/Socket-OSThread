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
	 * ���̵���ģ��
	 */
	processCtrl proctrl = new processCtrl();

	private javax.swing.Timer time;

	private static final long serialVersionUID = -8203814851876525357L;
	private static final int DEFAULT_HEIGHT = 930;
	private static final int DEFAULT_WIDTH = 930;

	private JPanel processPanel;// ���н��̵�panel
	private JPanel buttonPanel;// ��ťpanel
	private JPanel choosePanel;// ѡ������㷨panel
	private ButtonGroup group1;
	private ButtonGroup group2;

	private JPanel waitPanel;
	private JPanel readyPanel;
	private JPanel runPanel;
	private JPanel complPanel;
	
	private JPanel proWaitPanel;
	private JPanel proReadyPanel;
	private JPanel proRunPanel;
	private JPanel proComplPanel;// ���panel
	
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
	 * private JButton changePrioButton;//���Ľ������ȼ� �Ӷ�ʵ�ֽ���֮����л�
	 */
	private JButton blockButton; // ������ť
	private JButton awokeButton; // ���Ѱ�ť

	// ��������㷨�е��ĸ�radioButton
	private JRadioButton radioBut1;
	private JRadioButton radioBut2;
	private JRadioButton radioBut3;
	private JRadioButton radioBut4;

	JRadioButton tempRadio = new JRadioButton();// ����ѡť
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

		setTitle("Process Handling(���̵���)");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLayout(null);
		setResizable(false);
		createProInterface();

	}

	/**
	 * �����û�����
	 */
	@SuppressWarnings( { "static-access", "static-access", "static-access" })
	public void createProInterface() {
		Container contentPanel = getContentPane();
		contentPanel.setLayout(new GridLayout(3, 1));

		choosePanel = new JPanel();
		choosePanel.setBorder(new TitledBorder("ѡ����̵����㷨"));
		group1 = new ButtonGroup();
		radioBut1 = new JRadioButton("�Ƚ��ȳ�����FIFO");
		radioBut1.setSelected(true);
		radioBut2 = new JRadioButton("�����ҵ����SJF");
		radioBut3 = new JRadioButton("���ȼ�����");
		radioBut4 = new JRadioButton("��ת��(ʱ��Ƭ)");

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
		processPanel.setBorder(new TitledBorder("����״̬֮����л�"));
		processPanel.setLayout(new GridLayout(2, 2));
		
		// proWaitPanel.setBackground(new java.awt.Color(255, 255, 255));
		waitPanel = new JPanel();
		label1=new JLabel("�ȴ�����Panel");
		waitPanel.add(label1,BorderLayout.NORTH);
		proWaitPanel = new JPanel();
		proWaitPanel.setLayout(new javax.swing.BoxLayout(proWaitPanel,
				javax.swing.BoxLayout.Y_AXIS));
		waitPanel.add(proWaitPanel,BorderLayout.CENTER);		
		processPanel.add(waitPanel);
		
		// proReadyPanel.setBackground(new java.awt.Color(255, 255, 255));
		readyPanel = new JPanel();
		label2=new JLabel("��������panel");
		readyPanel.add(label2,BorderLayout.NORTH);
		proReadyPanel = new JPanel();
		proReadyPanel.setLayout(new javax.swing.BoxLayout(proReadyPanel,
				javax.swing.BoxLayout.Y_AXIS));
		readyPanel.add(proReadyPanel,BorderLayout.CENTER);
		processPanel.add(readyPanel);
		
		runPanel = new JPanel();
		label3=new JLabel("ִ�н���Panel");
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
		label4=new JLabel("��ɽ���panel");
		complPanel.add(label4,BorderLayout.NORTH);
		proComplPanel = new JPanel();
		proComplPanel.setLayout(new javax.swing.BoxLayout(proComplPanel,
				javax.swing.BoxLayout.Y_AXIS));
		// proComplPanel.setBackground(new java.awt.Color(255, 255, 255));
		complPanel.add(proComplPanel,BorderLayout.CENTER);
		processPanel.add(complPanel);

		contentPanel.add(processPanel);

		buttonPanel = new JPanel();
		buttonPanel.setBorder(new TitledBorder("���ư�ť"));
		buttonPanel.setLayout(new GridLayout(3, 1));
		pan1 = new JPanel();
		pan2 = new JPanel();
		pan3 = new JPanel();
		group2 = new ButtonGroup();
		randomProButton = new JButton("�����������");
		handProButton = new JButton("�ֶ���������");
		blockButton = new JButton("����");
		awokeButton = new JButton("����");
		startButton = new JButton("��ʼ");
		exitButton = new JButton("�˳�");

		// changePrioButton = new JButton("�޸����ȼ���(��������)");

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
		 * ����¼���Ӧ
		 * 
		 */

		// ���radio�¼���Ӧ
		radioBut1.addActionListener(listener1);
		radioBut1.addActionListener(listener1);
		radioBut1.addActionListener(listener1);
		radioBut1.addActionListener(listener1);

		// ���Button�¼���Ӧ
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

		time = new Timer(10, new ActionListener() {  //����ÿ��10��Ӧtime�¼�
			public void actionPerformed(ActionEvent event) {
				startButActionPerformed(event);
			}

		});

	}
	
	public void delay(){  //��ÿ������һ���ӳ�ʱ��  �Դ�������������ʱ��
		
		long time = new Date().getTime() + 2000;  //�ӳ�2��
		while (true) {
			if (new Date().getTime() > time)
				break;
		}

	}
	
	
	/*
	 * ���ۺ������ �����Ƚ��߳��ȷ���ȴ�����
	 * 
	 */
	public void randomProButActionPerformed(ActionEvent e) {

		Random random = new Random();
		int priority = (random.nextInt(4)) % 10 + 2; // ���ȼ� ��ʱ��Ƭ��ʱ������Ϊ���ȼ�
		int runTime = (random.nextInt(8)) % 10 + 1; // ����ִ��ʱ��
		/*
		 * long submitTime = System.currentTimeMillis();
		 * System.out.print("submitTime=" + submitTime);
		 */
		JTable table = proctrl
				.createProcess(priority, runTime/* , submitTime */);
		System.out.println(table);
		proWaitPanel.removeAll();// ÿ�ν�ǰһ�εĽ����е�����ȫ��ɾ��
		proWaitPanel.add(new JScrollPane(table),BorderLayout.CENTER);// ��JScrollPane����
		proWaitPanel.setVisible(true);
		proWaitPanel.updateUI();// ����proReadyPanel�������

	}

	public void handProButActionPerformed(ActionEvent e) {

		JFrame frame1 = new JFrame();
		frame1.setTitle("�ֶ���ӽ���");
		frame1.setSize(230, 230);

		JPanel pane1 = new JPanel();
		JLabel label1 = new JLabel("���̵����ȼ�:priority");
		JLabel label2 = new JLabel("���̵�ִ��ʱ�䣺runTime");

		final JTextField txf1 = new JTextField(23);
		final JTextField txf2 = new JTextField(23);

		JButton submBut = new JButton("�ύ����"); // �ύ��ť

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
	 * �˴���block���� ��Ҫ����ִ�е��ȴ�״̬ (��ʵҲ���Խ����̵�ִ��ת��Ϊ����״̬) ������������������
	 * */
	public void blockButActionPerformed(ActionEvent e) {

		Vector v = new Vector(20);
		if(radioBut1.isSelected()==true)
		{
			v=proctrl.setSchedule(1);   //FIFO�㷨
			/*System.out.println("�������ʿ���˵��");  //�����Ƿ�ִ��
*/				proctrl.complQueue.add(v);
		}
		
		if(radioBut2.isSelected()==true)
		{
			v=proctrl.setSchedule(2);   //SJF�㷨
			proctrl.complQueue.add(v);
		}
		if(radioBut3.isSelected()==true)
		{
			v=proctrl.setSchedule(3);   //FIFO�㷨
			proctrl.complQueue.add(v);
		}
		if(radioBut4.isSelected()==true)
		{
			v=proctrl.setSchedule(4);   //FIFO�㷨
			if(proctrl.timeSlice==1)
			proctrl.complQueue.add(v);
		}
		
			//int proId=(Integer)v.get(0);
			proctrl.runToWait(v);
	}

	/*
	 * 
	 * ���Ѷ�������  ���ȴ�״̬ת��Ϊ����״̬
	 * 
	 * ���ѵȴ����н������   ǰ���Ǿ��������п�
	 * */ 
	public void awokeButActionPerformed(ActionEvent e) {

		JFrame frame1 = new JFrame();
		frame1.setTitle("�ֶ���ӽ���");
		frame1.setSize(230, 230);

		JPanel pane = new JPanel();
		JLabel label = new JLabel("���̵����ȼ�:priority");

		final JTextField jtf = new JTextField(23);

		JButton awokeBut = new JButton("����"); // ���Ѱ�ť

		
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

		if (e.getSource() == startButton) {   //���ǰ� ��ʼ ��ť
			if (e.getActionCommand().equals("��ʼ")) {
				//proctrl.waitToReady();
				proComplPanel.removeAll();
				proctrl.complQueue.clear();
				time.start();
				startButton.setText("����");
			}
			if (e.getActionCommand().equals("����")) {
				time.stop();
				startButton.setText("��ʼ");
			}

		} 
		else if (e.getSource() == time)  //����Դ��time��һ��Դ
		{
			Vector v = new Vector(20);
			String runStr = "";  //������ʾ ִ�н����еĸ�����ͬ����ֵ

			if (proctrl.canSchedule() == false) { //���ȴ�  ���������Ƿ�Ϊ��,��Ϊ���˳�
				proReadyPanel.removeAll();
				proWaitPanel.removeAll();

				startButton.setText("��ʼ");
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
				v=proctrl.setSchedule(1);   //FIFO�㷨
				/*System.out.println("�������ʿ���˵��");  //�����Ƿ�ִ��
*/				proctrl.complQueue.add(v);
			}
			
			if(radioBut2.isSelected()==true)
			{
				v=proctrl.setSchedule(2);   //SJF�㷨
				proctrl.complQueue.add(v);
			}
			if(radioBut3.isSelected()==true)
			{
				v=proctrl.setSchedule(3);   //FIFO�㷨
				proctrl.complQueue.add(v);
			}
			if(radioBut4.isSelected()==true)
			{
				v=proctrl.setSchedule(4);   //FIFO�㷨
				if(proctrl.timeSlice==1)
				proctrl.complQueue.add(v);
			}
			
			// ��ȡ����״ֵ̬����readyQueue���ڵ������			
			String aa = "���ж���ID:" + v.get(0);
			String bb = "�������ȼ�:" + v.get(1);
			String cc = "����ʱ��Ƭ:" + v.get(2);
			String dd = "����ִ��ʱ��:" + v.get(3);
			runStr = "<html>" + aa + "<br>" + bb + "<br>" + cc + "<br>" + dd
					+ "<br>" + "</html>";
			
			proReadyPanel.removeAll();
			proWaitPanel.removeAll();
			proComplPanel.removeAll();
			
			startButton.setText("��ʼ");
			runLabel.setText(runStr);
			
			JScrollPane sp1 = new JScrollPane(proctrl.getReady());
			proReadyPanel.add(sp1);			
			JScrollPane sp2 = new JScrollPane(proctrl.getWait());
			proWaitPanel.add(sp2);
			JScrollPane sp3 = new JScrollPane(proctrl.getCompl());
			proComplPanel.add(sp3);
			startButton.setText("����");
			
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
