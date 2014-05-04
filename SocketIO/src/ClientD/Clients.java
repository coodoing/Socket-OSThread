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
			RandomAccessFile outraf=new RandomAccessFile(file,"r");     //欲发送的文件
			try {
				new PrintStream(client.getOutputStream()).println(file.getName().toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] byteBuffer = new byte[2048];
			int count ;
			while ((count = outraf.read(byteBuffer))!=-1) {
				out.write(byteBuffer, 0, count);// 把文件数据写入网络缓冲区
				out.flush();// 刷新缓冲区				
			}		
			javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(),"已接收成功","提示!",javax.swing.JOptionPane.PLAIN_MESSAGE);
			out.close();
			outraf.close();
			client.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
		}
	}
}
