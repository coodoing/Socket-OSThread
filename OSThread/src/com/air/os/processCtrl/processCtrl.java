package com.air.os.processCtrl;

import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JTable;

public class processCtrl {

	/*
	 * 一个进程块PCB含有的元素 ：：： 优先级 时间片 执行时间
	 * 
	 * 将时间片的时间设置为3 
	 * 
	 * 就绪状态与等待状态之间的转化 
	 * 
	 */

	static int id = 2323;
	static int counter = 3;

	public static int numMax = 3; // 就绪队列所能容纳的最大数

	private int proPolicy; // 进程调度策略 1代表先来先服务 2代表最短作业优先 3代表优级 4代表分时轮转法
	private int runTime; // 程序执行时间
	private int processID = 2323; // 进程ID
	private int priority; // 优先权
	// private int counter; // 时间片
	// private long submitTime; // 提交时间

	@SuppressWarnings("unchecked")
	/*
	 * LinkList 类实现了List的接口 并提供了一个链接列表的数据结构
	 */
	public LinkedList waitQueue = new LinkedList();// 等待队列
	public LinkedList readyQueue = new LinkedList();// 就绪队列
	public LinkedList runQueue = new LinkedList();// 运行队列
	public LinkedList complQueue = new LinkedList();// 完成队列

	public processCtrl() {

	}

	public int createId() {
		id = id + 10;
		return id;
	}

	/**
	 * 创建一个新的进程 并进入等待队列
	 */
	public JTable createProcess(int priority, int runTime/* , long submitTime */) {
		// this.counter = priority;
		this.priority = priority;/*
		 * this.submitTime = submitTime;
		 */
		this.runTime = runTime;
		this.processID = createId();

		Vector PCB = new Vector(4); // 进程控制块：(PCB) 0对应进程ID 1对应进程优先级 2对应进程时间片
		// 3代表进程执行时间
		PCB.add(new Integer(processID));
		PCB.add(new Integer(priority));
		PCB.add(new Integer(counter));
		// PCB.add(new Long(submitTime));
		PCB.add(new Integer(runTime));

		waitQueue.add(PCB); // 入等待队列 假设随机产生的进程都入等待队列

		String[] headings = new String[] { "进程ID", "优先级", "时间片",/* "提交时间", */
		"执行时间" };
		Object[][] data = new Object[waitQueue.size()][4];

		for (int i = 0; i < waitQueue.size(); i++) {
			Vector vec = (Vector) waitQueue.get(i);
			data[i][0] = (Integer) vec.get(0);
			data[i][1] = (Integer) vec.get(1);
			data[i][2] = (Integer) vec.get(2);
			data[i][3] = (Integer) vec.get(3);
		}
		JTable table = new JTable(data, headings);/*
		 * System.out.println(waitQueue.size());//
		 * 测试获取 当前waitQueue的长度 //
		 * 随着进程添加而不断发生变化
		 */
		return table;
	}

	public JTable getReady() { // 获得就绪状态下的线程table

		String[] headings = new String[] { "ID", "优先级", "时间片",/* "提交时间", */
		"执行时间" };
		Object[][] data = new Object[readyQueue.size()][4];

		for (int i = 0; i < readyQueue.size(); i++) {
			Vector vec = (Vector) readyQueue.get(i);

			data[i][0] = (Integer) vec.get(0);
			data[i][1] = (Integer) vec.get(1);
			data[i][2] = (Integer) vec.get(2);
			data[i][3] = (Integer) vec.get(3);
		}

		JTable table = new JTable(data, headings);
		return table;

	}

	public JTable getWait() { // 获取等待状态下的线程table

		// waitToReady();
		String[] headings = new String[] { "ID", "优先级", "时间片",/* "提交时间", */
		"执行时间" };
		Object[][] data = new Object[waitQueue.size()][4];

		for (int i = 0; i < waitQueue.size(); i++) {
			Vector vec = (Vector) waitQueue.get(i);

			data[i][0] = (Integer) vec.get(0);
			data[i][1] = (Integer) vec.get(1);
			data[i][2] = (Integer) vec.get(2);
			data[i][3] = (Integer) vec.get(3);
		}

		JTable table = new JTable(data, headings);
		return table;

	}

	public JTable getCompl() { // 获取完成状态下的线程table

		String[] headings = new String[] { "ID", "优先级", "时间片",/* "提交时间", */
		"执行时间" };
		Object[][] data = new Object[complQueue.size()][4];

		for (int i = 0; i < complQueue.size(); i++) {
			Vector vec = (Vector) complQueue.get(i);

			data[i][0] = (Integer) vec.get(0);
			data[i][1] = (Integer) vec.get(1);
			data[i][2] = (Integer) vec.get(2);
			data[i][3] = (Integer) vec.get(3);
		}

		JTable table = new JTable(data, headings);
		return table;

	}

	/*
	 * 
	 * 状态之间的转换，包括： 1 执行状态向等待状态转换 2 等待状态向就绪状态转化 3 就绪向执行状态转化
	 * 
	 */

	public void runToWait(Vector vec) { // 执行状态到等待状态

		waitQueue.addLast(vec);
	}

	public void runToReady() { // 执行到就绪
		// TODO 此程序中没有涉及run到ready的转化
	}

	public void readyToRun(Vector vec) { // 由就绪状态向执行状态转化

		// TODO 程序中自行转化
	}

	public LinkedList waitToReady() { // 等待状态到就绪状态 最初开始的状态转化

		System.out.println(waitQueue.size()); // 看一下waitQueue的size到底变化了没有

		if (waitQueue.size() <= numMax && readyQueue.size() == 0) { // 最初时候的转化

			readyQueue.addAll(waitQueue);
			waitQueue.clear();
			/*
			 * for (int i = 0; i < waitQueue.size(); i++) {
			 * readyQueue.add((Vector) waitQueue.remove(i)); }
			 */
		}

		if (readyQueue.size() < numMax) { //readyQueue都处于满的状态直到waitQueue没有进程等待 当执行到readyQueue里面没有满 而现在外界又输入进程的情况

			for (int i = 0; i < waitQueue.size(); i++) {
				readyQueue.add((Vector) waitQueue.remove(i));
			}

		}
		
		if(readyQueue.size()==numMax&&waitQueue.size()>0){
			//readyQueue.removeFirst();
			/*
			 * 只有在就绪队列里面才有 不同线程因为优先级和执行时间不同而发生资源抢占及优先级的排序
			 * 
			 * */
			readyQueue.add((Vector) waitQueue.removeFirst());
			
		}
		/*
		 * 更一般的是 if(readyQueue.size()<=numMax){ //
		 * 就绪序列不为full 由waitQueue一个一个加入readyQueue
		 * 
		 * for (int i = 0; i < waitQueue.size(); i++) { readyQueue.add((Vector)
		 * waitQueue.remove(i)); } }
		 */
		/*
		 * 等待队列个数大于就绪队列能容纳的 if(waitQueue.size()>numMax){ for(int i=0;i<numMax;i++) {
		 * readyQueue.add((Vector) waitQueue.remove(i)); } }
		 */
		// if (/*readyQueue.size() == 3 && */waitQueue.size() > numMax) {
		// readyQueue.add((Vector) waitQueue.removeFirst());
		// }
		return readyQueue;
	}

	public void waitToReady1(int proId) { // 等待状态到就绪状态 制定特定的进程 由参数proId传入

		int k = 0;
		for (int i = 0; i < waitQueue.size(); i++) {
			Vector vec = (Vector) waitQueue.get(i);
			int num = (Integer) vec.get(0);
			if (num == proId) {
				k = i;
			}
		}
		readyQueue.addFirst(waitQueue.remove(k));
		// readyQueue.add(k);
	}

	// 下面是对表格的按 优先级 执行时间的排序;

	public LinkedList sortByPrio() // 根据优先级进行排序
	{
		Vector vec = (Vector) waitQueue.get(0);
		int max = (Integer) vec.get(1);
		for (int i = 1; i < waitQueue.size(); i++) {
			vec = (Vector) waitQueue.get(i);
			int m = (Integer) vec.get(1);
			if (m > max) { // 若有数字大于最大值
				i = 0;
			}
			waitQueue.remove(0);
			waitQueue.addFirst(i);
			waitQueue.addLast(0);
		}
		return waitQueue;
	}

	public LinkedList sortByTime() { // 根据执行时间进行排序

		return waitQueue;
	}

	/*
	 * 下面就是 对具体的每种算法的描述
	 */

	public boolean canRun = true; // 判断就绪 等待队列是否为空来判断

	public boolean canSchedule() { // 看就绪 等待队列的值是否为空

		if (waitQueue.size() == 0 && readyQueue.size() == 0)
			canRun = false;
		else
			canRun = true;
		return canRun;
	}

	public Vector setSchedule(int proPolicy) { // 具体设置调度算法

		this.proPolicy = proPolicy;
		Vector vec = new Vector();
		/*
		 * System.out.println("\n" + readyQueue.size() +
		 * "asdjgasdgjsajdgkaslgnklasjgkasdgj");// 测试方法是否有效
		 */switch (proPolicy) {
		case 1:
			vec = FIFO(); // 调用先进先出算法
			break;
		case 2:
			vec = SJF(); // 调用最短时间优先算法
			break;
		case 3:
			vec = prio(); // 调用优先级算法
			break;
		case 4:
			vec = timeSlice(); // 调用时间片算法
			break;
		}
		return vec;

	}

	public Vector FIFO() {

		readyQueue = waitToReady();
		Vector vec = (Vector) readyQueue.removeFirst();
		return vec;
	}

	public Vector SJF() {
		// readyQueue=waitToReady(sortByTime());
		readyQueue = waitToReady();
		int iNum = new Integer("23");
		int n = 0;
		for (int i = 0; i < readyQueue.size(); i++) {

			Vector vec = (Vector) readyQueue.get(i);
			Integer m = (Integer) vec.get(3);
			if (m.compareTo(iNum) < 0) {
				iNum = m;
				n = i;
			}
		}
		return (Vector) readyQueue.remove(n);
	}

	public Vector prio() {
		// readyQueue=waitToReady(sortByPrio());
		readyQueue = waitToReady();
		int iNum = new Integer("0");
		int n = 0;
		for (int i = 0; i < readyQueue.size(); i++) {

			Vector vec = (Vector) readyQueue.get(i);
			Integer m = (Integer) vec.get(1);
			if (m.compareTo(iNum) > 0) {
				iNum = m;
				n = i;
			}
		}
		return (Vector) readyQueue.remove(n);
	}

	public int timeSlice = 0; // 时间片分割标志

	public Vector timeSlice() {

		readyQueue = waitToReady();
		Vector vec = (Vector) readyQueue.getFirst();
		Integer counter = (Integer) vec.get(2); // 时间片
		Integer runTime = (Integer) vec.get(3); // 执行时间
		if (counter.compareTo(runTime) >= 0) {
			vec.add(2, new Integer(0));
			vec.add(3, new Integer(0));
			timeSlice = 1;
			return (Vector) readyQueue.removeFirst();

		} else {
			int m = runTime.intValue();
			int n = counter.intValue();
			vec.add(3, new Integer(m - n));
			readyQueue.add(vec);
			timeSlice = 0;
			return (Vector) readyQueue.removeFirst();
		}
	}
}
