package com.os;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class jFrameTest extends JFrame{

	public static void main(String[] args){
	JFrame frame1 = new JFrame();
	frame1.setTitle("�ֶ���ӽ���");
	frame1.setSize(300, 300);

	JPanel pane1 = new JPanel();
	JLabel label1 = new JLabel("���̵����ȼ�:priority");
	JLabel label2 = new JLabel("���̵�ִ��ʱ�䣺runTime");

	final JTextField txf1 = new JTextField(23);
	final JTextField txf2 = new JTextField(23);

	JButton submBut = new JButton("�ύ����"); //�ύ��ť

	submBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});

	pane1.setLayout(new GridLayout(3, 2));

	pane1.add(label1);
	pane1.add(txf1);
	pane1.add(label2);
	pane1.add(txf2);
	pane1.add(submBut);

	//frame1.getContentPane().add(pane1);
	frame1.add(pane1);
	
	frame1.setVisible(true);
	//setResizable(false);
	//frame1.pack();
	}
}
