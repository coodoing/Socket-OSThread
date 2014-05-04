package DengluFr;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ServerD.ServerFrame;
public class exPane extends JPanel implements ActionListener//,ItemListener 
{
     JButton bu1=new JButton("ConServer");
     JButton bu2=new JButton("ConClient");
     JButton bu3=new JButton("Exit");
     
     JLabel lab1=new JLabel("连接服务端");
     JLabel lab2=new JLabel("连接用户端");
     JPanel p1=new JPanel();
     JPanel p11=new JPanel();
     JPanel p12=new JPanel();
     JPanel p13=new JPanel();

	 public exPane()
     {
		 setLayout(new BorderLayout());
		 setVisible(true);
		 p1.setLayout(new GridLayout(2,1));
		 p11.setLayout(new GridLayout(1,2));
		 p12.setLayout(new GridLayout(1,2));
	     p1.add(p11);
		 p1.add(p12);
		 p11.add(lab1);
		 p11.add(bu1);
		 p12.add(lab2);
		 p12.add(bu2);
		 p13.add(bu3);
		 
		 add(p1,BorderLayout.NORTH);
		 add(p13,BorderLayout.SOUTH);
		 bu1.addActionListener(this);
		 bu2.addActionListener(this);
		 bu3.addActionListener(this);
		 bu2.setEnabled(false);
		 
     }
     public void actionPerformed(ActionEvent event)
     {
    	if(event.getSource()==bu1)
    	{
    		final ServerFrame frame;
    		try {
    			frame = new ServerFrame();
    			frame.setVisible(true);		
    		} catch (IOException e) {
    		}		
    	}
    	if(event.getSource()==bu2)
    	{
    	}
    	if(event.getSource()==bu3)
    	{
    		System.exit(0);
    	}
     }
	public static void main(String arg[]) throws IOException
	{	
		//try{
		//new ServerFrame();
		//}catch(IOException e)
		//{}
	}
}
