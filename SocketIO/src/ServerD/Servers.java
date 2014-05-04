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
			while(true){//目的是可以重复的运行程序
			ServerSocket sso= new ServerSocket(PORT);
			//ServerSocket sso= new ServerSocket(1254);
			s = sso.accept();
			DataInputStream dins = new DataInputStream(s.getInputStream());
			String str = dins.readLine();
			frame.getLabel().setText("File Send" + str);//给label设置字符串 从而得到文件名
			System.out.print(str);
			while (frame.getlock()!=2){};			
			RandomAccessFile inraf = new RandomAccessFile(frame.getFiles(), "rw");
			InputStream in = s.getInputStream();			
			byte[] byteBuffer = new byte[2048];
			int count;
		    while ((count=in.read(byteBuffer))!=-1) {// 是否读完文件
				inraf.write(byteBuffer, 0, count);// 把文件数据写出网络缓冲区
				}
			javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(),"已发送完毕","提示!",javax.swing.JOptionPane.PLAIN_MESSAGE); 
			inraf.close();
			in.close();
			s.close();
			sso.close();
			
			frame.setlock(3);//若是图片 则在接收后显示 否则不做处理 其中3的值可以用不同于2的任何正整数代替
			frame.isPicture();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}