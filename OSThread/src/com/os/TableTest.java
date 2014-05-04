package com.os;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TableTest implements ActionListener {

	JFrame frame = new JFrame("JTable Test");
	JButton button = new JButton("增加");
	JTextField fname = new JTextField(20);
	JTextField lname = new JTextField(20);
	JPanel north = new JPanel();
	JLabel l1 = new JLabel("姓名");
	JLabel l2 = new JLabel("联系方式");
	DefaultTableModel dtm = new DefaultTableModel(0, 2);
	JTable table = new JTable(dtm);
	JScrollPane jsp = new JScrollPane(table);
	String[] temp = new String[2];

	public void init() {
		frame.setBounds(200, 200, 640, 480);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		north.add(l1);
		north.add(fname);
		north.add(l2);
		north.add(lname);
		button.addActionListener(this);
		north.add(button);
		//dtm.setColumnIdentifiers(new String[] { "姓名", "联系方式" });
		//dtm.setDataVector(new int[][]{{1,2}}, new String[] { "姓名", "联系方式" });
		frame.add(north, BorderLayout.NORTH);
		frame.add(jsp, BorderLayout.CENTER);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		TableTest tt = new TableTest();
		tt.init();
	}

	public void actionPerformed(ActionEvent e) {
		temp[0] = fname.getText();
		temp[1] = lname.getText();
		dtm.insertRow(dtm.getRowCount(), temp);
	}

}
