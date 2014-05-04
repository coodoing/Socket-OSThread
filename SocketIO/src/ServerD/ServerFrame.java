package ServerD;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import ClientD.ClientFrame;

class ImagePanel extends JPanel {
	private BufferedImage image;
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),null);
	}
}

public class ServerFrame extends JFrame{
	private ImagePanel srcPanel=new ImagePanel();//显示图片区域
	private BufferedImage bfim;
	private Servers server;
	private ClientFrame cFrame;

	private int lock=1;////判断是否是图片 默认的不是
	private File files ;
	private JLabel lab;
	private String str=null;
	public ServerFrame() throws IOException{
		super("Server");
    	init();
		thread();	
    }
	private void thread() {
		new Thread(new Runnable(){
			public void run() {	
				try{
			server=new Servers(serverFr());	
				}catch(Exception e){}
           }	
		}).start();		
	}
	public int getlock() {
		return lock;
	}
	public void setlock(int lock) {
		this.lock = lock;
	}
	public ServerFrame serverFr(){
		return this;
	}
	public File getFiles() {
		return files;
	}
    public JLabel getLabel(){
    	return lab;
    }
	public ImagePanel getSrcPanel() {
		return srcPanel;
	}
	public void setSrcPanel(ImagePanel srcPanel) {
		this.srcPanel = srcPanel;
	}
	public BufferedImage getbfim() {
		return bfim;
	}
	public void setbfim(BufferedImage bfim) {
		this.bfim = bfim;
	}
	private void init() {

		InetAddress inet;
		try {
			inet = InetAddress.getLocalHost();
			this.setTitle("Server:"+inet.getHostAddress());
		} catch (UnknownHostException e1) {			
		}		
		this.setSize(500,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		JPanel ButtonPan=new JPanel();	
		JPanel picturePan=new JPanel();
		JPanel connePan=new JPanel();
	    
	    JPanel acceptPan=new JPanel();
	    acceptPan.setLayout(new FlowLayout());
	    lab=new JLabel("Waitting....");//显示文件的目录
		final JButton saveButton =new JButton("Save");	
		acceptPan.add(lab);
		acceptPan.add(saveButton);
		
		final JButton sendButton =new JButton("Send");		
		
		ButtonPan.add(sendButton);
		ButtonPan.add(acceptPan);	
		
		srcPanel.setEnabled(true);
		picturePan.setLayout(new GridLayout(1,1));//用于填满整个srcPanel区域
		picturePan.setBorder(new TitledBorder("Image show"));
		picturePan.add(srcPanel);				
			
		
		connePan.setLayout(new FlowLayout());
		JButton conbutton=new JButton("Connect");
		JButton exitbutton=new JButton("Exit");
		final JTextField jtf=new JTextField(10);
		connePan.add(conbutton);
		connePan.add(jtf);
		connePan.add(exitbutton);
		
		sendButton.setEnabled(false);
		saveButton.setEnabled(false);		
		this.add(ButtonPan,BorderLayout.NORTH);
		this.add(picturePan,BorderLayout.CENTER);
		this.add(connePan,BorderLayout.SOUTH);
		
		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();			
				//注意与  JFileChooser jfc=new JFileChooser(".");   //文件选择器 以当前的目录打开    之间的区别
				jfc.setSelectedFile(new File("g:\\"+lab.getText().substring(lab.getText().lastIndexOf('d')+1)));//下面这句是让默认保存到g盘，文件名不变
				jfc.setMultiSelectionEnabled(true);
				if (JFileChooser.APPROVE_OPTION == jfc.showSaveDialog((Component) e.getSource())) {
				    	 files = jfc.getSelectedFile();
					     lock=2;
				}							
			}			
		});
		sendButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable(){
					public void run() {
						System.out.print(str);
						cFrame=new ClientFrame(str);	
					    cFrame.setVisible(true);
				 	}}).start();    	
			}		
		});
		conbutton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
			   str=jtf.getText();
			   if(str==null)
			   {
					sendButton.setEnabled(false);
					saveButton.setEnabled(false);
			   }
			   if(str!=null)
			   {	
				   sendButton.setEnabled(true);
				   saveButton.setEnabled(true);
			   }
			}
		});
		exitbutton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}	
	public void isPicture() {
		String name=files.getName();//由文件的名字判断是否为图片 其中保存的文件的名字是所选取的文件的名字		
		 if(name.indexOf('.')==-1) 
		 {   
			  name  ="";   
		 }  
		 else  
		 {   
			  name=name.substring(name.lastIndexOf('.')+1);   
		 }		 
		 String imgeArray [][] = {{"bmp", "0"}, {"dib", "1"}, {"gif", "2"},{"jfif", "3"}, {"jpe", "4"}, {"jpeg", "5"},{"jpg", "6"}, {"png", "7"} ,{"tif", "8"},{"tiff", "9"}, {"ico", "10"}};
	         for(int i = 0; i<imgeArray.length;i++){
	        	 if(imgeArray [i][0].equals(name.toLowerCase()))
	        	 {
	        		 try
	     			{
	     				   bfim=ImageIO.read(files);
	     			} catch (IOException e) {
	     			}
	     			srcPanel.setImage(bfim);
	     		    repaint();
	        	 }        	 
	         }
	}
	public static void main(String[] args)  {
		}
	
}

