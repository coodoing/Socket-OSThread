package ServerD;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;

public class Servers {
	private ServerFrame frame;
	public final static int PORT = 1452;
	public Servers(ServerFrame serverFr) {
		this.frame = serverFr;		
		Socket s = null;
		try {
			while(true){//Ŀ���ǿ����ظ������г���
			ServerSocket sso= new ServerSocket(PORT);
			//ServerSocket sso= new ServerSocket(1254);
			s = sso.accept();
			DataInputStream dins = new DataInputStream(s.getInputStream());
			String str = dins.readLine();
			frame.getLabel().setText("File Send" + str);//��label�����ַ��� �Ӷ��õ��ļ���
			System.out.print(str);
			while (frame.getlock()!=2){};			
			RandomAccessFile inraf = new RandomAccessFile(frame.getFiles(), "rw");
			InputStream in = s.getInputStream();			
			byte[] byteBuffer = new byte[2048];
			int count;
		    while ((count=in.read(byteBuffer))!=-1) {// �Ƿ�����ļ�
				inraf.write(byteBuffer, 0, count);// ���ļ�����д�����绺����
				}
			javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(),"�ѷ������","��ʾ!",javax.swing.JOptionPane.PLAIN_MESSAGE); 
			inraf.close();
			in.close();
			s.close();
			sso.close();
			
			frame.setlock(3);//����ͼƬ ���ڽ��պ���ʾ ���������� ����3��ֵ�����ò�ͬ��2���κ�����������
			frame.isPicture();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}