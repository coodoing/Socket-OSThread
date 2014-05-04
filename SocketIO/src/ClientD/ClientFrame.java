package ClientD;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientFrame extends JFrame{
	private Clients clients;
	public File files;
	public ClientFrame(final String ip) {
		init(ip);
	}	
	private void init(final String ip) {				
				  JFileChooser jfc=new JFileChooser(".");	              	              
	              int ret=jfc.showOpenDialog(new javax.swing.JFrame());
	              if(ret==JFileChooser.APPROVE_OPTION)
	                    {
	            	    files = jfc.getSelectedFile();                  
	                    new Thread(new Runnable(){
	            			public void run() {
	            				clients=new Clients(files,ip);           				
	            	 	}}).start();
	                    }
			}				
	}
