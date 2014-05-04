package com.air.os.processCtrl;

import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JTable;

public class processCtrl {

	/*
	 * һ�����̿�PCB���е�Ԫ�� ������ ���ȼ� ʱ��Ƭ ִ��ʱ��
	 * 
	 * ��ʱ��Ƭ��ʱ������Ϊ3 
	 * 
	 * ����״̬��ȴ�״̬֮���ת�� 
	 * 
	 */

	static int id = 2323;
	static int counter = 3;

	public static int numMax = 3; // ���������������ɵ������

	private int proPolicy; // ���̵��Ȳ��� 1���������ȷ��� 2���������ҵ���� 3�����ż� 4�����ʱ��ת��
	private int runTime; // ����ִ��ʱ��
	private int processID = 2323; // ����ID
	private int priority; // ����Ȩ
	// private int counter; // ʱ��Ƭ
	// private long submitTime; // �ύʱ��

	@SuppressWarnings("unchecked")
	/*
	 * LinkList ��ʵ����List�Ľӿ� ���ṩ��һ�������б�����ݽṹ
	 */
	public LinkedList waitQueue = new LinkedList();// �ȴ�����
	public LinkedList readyQueue = new LinkedList();// ��������
	public LinkedList runQueue = new LinkedList();// ���ж���
	public LinkedList complQueue = new LinkedList();// ��ɶ���

	public processCtrl() {

	}

	public int createId() {
		id = id + 10;
		return id;
	}

	/**
	 * ����һ���µĽ��� ������ȴ�����
	 */
	public JTable createProcess(int priority, int runTime/* , long submitTime */) {
		// this.counter = priority;
		this.priority = priority;/*
		 * this.submitTime = submitTime;
		 */
		this.runTime = runTime;
		this.processID = createId();

		Vector PCB = new Vector(4); // ���̿��ƿ飺(PCB) 0��Ӧ����ID 1��Ӧ�������ȼ� 2��Ӧ����ʱ��Ƭ
		// 3�������ִ��ʱ��
		PCB.add(new Integer(processID));
		PCB.add(new Integer(priority));
		PCB.add(new Integer(counter));
		// PCB.add(new Long(submitTime));
		PCB.add(new Integer(runTime));

		waitQueue.add(PCB); // ��ȴ����� ������������Ľ��̶���ȴ�����

		String[] headings = new String[] { "����ID", "���ȼ�", "ʱ��Ƭ",/* "�ύʱ��", */
		"ִ��ʱ��" };
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
		 * ���Ի�ȡ ��ǰwaitQueue�ĳ��� //
		 * ���Ž�����Ӷ����Ϸ����仯
		 */
		return table;
	}

	public JTable getReady() { // ��þ���״̬�µ��߳�table

		String[] headings = new String[] { "ID", "���ȼ�", "ʱ��Ƭ",/* "�ύʱ��", */
		"ִ��ʱ��" };
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

	public JTable getWait() { // ��ȡ�ȴ�״̬�µ��߳�table

		// waitToReady();
		String[] headings = new String[] { "ID", "���ȼ�", "ʱ��Ƭ",/* "�ύʱ��", */
		"ִ��ʱ��" };
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

	public JTable getCompl() { // ��ȡ���״̬�µ��߳�table

		String[] headings = new String[] { "ID", "���ȼ�", "ʱ��Ƭ",/* "�ύʱ��", */
		"ִ��ʱ��" };
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
	 * ״̬֮���ת���������� 1 ִ��״̬��ȴ�״̬ת�� 2 �ȴ�״̬�����״̬ת�� 3 ������ִ��״̬ת��
	 * 
	 */

	public void runToWait(Vector vec) { // ִ��״̬���ȴ�״̬

		waitQueue.addLast(vec);
	}

	public void runToReady() { // ִ�е�����
		// TODO �˳�����û���漰run��ready��ת��
	}

	public void readyToRun(Vector vec) { // �ɾ���״̬��ִ��״̬ת��

		// TODO ����������ת��
	}

	public LinkedList waitToReady() { // �ȴ�״̬������״̬ �����ʼ��״̬ת��

		System.out.println(waitQueue.size()); // ��һ��waitQueue��size���ױ仯��û��

		if (waitQueue.size() <= numMax && readyQueue.size() == 0) { // ���ʱ���ת��

			readyQueue.addAll(waitQueue);
			waitQueue.clear();
			/*
			 * for (int i = 0; i < waitQueue.size(); i++) {
			 * readyQueue.add((Vector) waitQueue.remove(i)); }
			 */
		}

		if (readyQueue.size() < numMax) { //readyQueue����������״ֱ̬��waitQueueû�н��̵ȴ� ��ִ�е�readyQueue����û���� �����������������̵����

			for (int i = 0; i < waitQueue.size(); i++) {
				readyQueue.add((Vector) waitQueue.remove(i));
			}

		}
		
		if(readyQueue.size()==numMax&&waitQueue.size()>0){
			//readyQueue.removeFirst();
			/*
			 * ֻ���ھ�������������� ��ͬ�߳���Ϊ���ȼ���ִ��ʱ�䲻ͬ��������Դ��ռ�����ȼ�������
			 * 
			 * */
			readyQueue.add((Vector) waitQueue.removeFirst());
			
		}
		/*
		 * ��һ����� if(readyQueue.size()<=numMax){ //
		 * �������в�Ϊfull ��waitQueueһ��һ������readyQueue
		 * 
		 * for (int i = 0; i < waitQueue.size(); i++) { readyQueue.add((Vector)
		 * waitQueue.remove(i)); } }
		 */
		/*
		 * �ȴ����и������ھ������������ɵ� if(waitQueue.size()>numMax){ for(int i=0;i<numMax;i++) {
		 * readyQueue.add((Vector) waitQueue.remove(i)); } }
		 */
		// if (/*readyQueue.size() == 3 && */waitQueue.size() > numMax) {
		// readyQueue.add((Vector) waitQueue.removeFirst());
		// }
		return readyQueue;
	}

	public void waitToReady1(int proId) { // �ȴ�״̬������״̬ �ƶ��ض��Ľ��� �ɲ���proId����

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

	// �����ǶԱ��İ� ���ȼ� ִ��ʱ�������;

	public LinkedList sortByPrio() // �������ȼ���������
	{
		Vector vec = (Vector) waitQueue.get(0);
		int max = (Integer) vec.get(1);
		for (int i = 1; i < waitQueue.size(); i++) {
			vec = (Vector) waitQueue.get(i);
			int m = (Integer) vec.get(1);
			if (m > max) { // �������ִ������ֵ
				i = 0;
			}
			waitQueue.remove(0);
			waitQueue.addFirst(i);
			waitQueue.addLast(0);
		}
		return waitQueue;
	}

	public LinkedList sortByTime() { // ����ִ��ʱ���������

		return waitQueue;
	}

	/*
	 * ������� �Ծ����ÿ���㷨������
	 */

	public boolean canRun = true; // �жϾ��� �ȴ������Ƿ�Ϊ�����ж�

	public boolean canSchedule() { // ������ �ȴ����е�ֵ�Ƿ�Ϊ��

		if (waitQueue.size() == 0 && readyQueue.size() == 0)
			canRun = false;
		else
			canRun = true;
		return canRun;
	}

	public Vector setSchedule(int proPolicy) { // �������õ����㷨

		this.proPolicy = proPolicy;
		Vector vec = new Vector();
		/*
		 * System.out.println("\n" + readyQueue.size() +
		 * "asdjgasdgjsajdgkaslgnklasjgkasdgj");// ���Է����Ƿ���Ч
		 */switch (proPolicy) {
		case 1:
			vec = FIFO(); // �����Ƚ��ȳ��㷨
			break;
		case 2:
			vec = SJF(); // �������ʱ�������㷨
			break;
		case 3:
			vec = prio(); // �������ȼ��㷨
			break;
		case 4:
			vec = timeSlice(); // ����ʱ��Ƭ�㷨
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

	public int timeSlice = 0; // ʱ��Ƭ�ָ��־

	public Vector timeSlice() {

		readyQueue = waitToReady();
		Vector vec = (Vector) readyQueue.getFirst();
		Integer counter = (Integer) vec.get(2); // ʱ��Ƭ
		Integer runTime = (Integer) vec.get(3); // ִ��ʱ��
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
