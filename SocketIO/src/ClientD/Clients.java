package ClientD;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.net.UnknownHostException;

import ServerD.Servers;

public class Clients {
	private Socket client = null;	
	public Clients(File file, String ip) {
		try {
			//client = new Socket("127.0.0.1",1254);
			client = new Socket(ip,Servers.PORT);
			System.out.println("PORT="+Servers.PORT);
			OutputStream out= client.getOutputStream(); 
			RandomAccessFile outraf=new RandomAccessFile(file,"r");     //�����͵��ļ�
			try {
				new PrintStream(client.getOutputStream()).println(file.getName().toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] byteBuffer = new byte[2048];
			int count ;
			while ((count = outraf.read(byteBuffer))!=-1) {
				out.write(byteBuffer, 0, count);// ���ļ�����д�����绺����
				out.flush();// ˢ�»�����				
			}		
			javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(),"�ѽ��ճɹ�","��ʾ!",javax.swing.JOptionPane.PLAIN_MESSAGE);
			out.close();
			outraf.close();
			client.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
		}
	}
}
