package DengluFr;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
//------------------------------------------------------------------
class pane extends JPanel
{
	JTextArea jta=new JTextArea("Socket 网络通信,实现的功能是可以用来传送文件和图片");
	public pane()
	{
		setLayout(new BorderLayout());
		jta.setEditable(false);
		add(jta,"Center");//注意在处理JFrame 框架与JPanel框架的时候有所区别 在JFrame中一般都通过声明一个Container 然后把标签和控件等加入container定义的对象中
	}	
}
public class Tree extends JFrame implements TreeSelectionListener{
    private JPanel p=new JPanel();
    JTree tr=null;
    pane pan1=new pane();    
    exPane pan2=new exPane();
    JTextArea jta=new JTextArea();
    static CardLayout cal=new CardLayout();
    Container con=getContentPane();      
	public Tree()
     {
    	 super("SocketConnection");
    	 setSize(500,700);
    	 setTitle("SocketConnection");
    	 setLocation(100,100);
    	 //DefaultMutableTreeNode 用于thread时候要注意
    	 DefaultMutableTreeNode root=new DefaultMutableTreeNode("SocketConnection");
    	 DefaultMutableTreeNode dmt1=new DefaultMutableTreeNode("开始界面");
    	 DefaultMutableTreeNode dmt2=new DefaultMutableTreeNode("通信界面");
    	 //DefaultMutableTreeNode dmt3=new DefaultMutableTreeNode("");
    	 root.add(dmt1);
    	 root.add(dmt2);
    	 tr=new JTree(root);
    	 tr.addTreeSelectionListener(this);
    	 p.setLayout(cal);
    	 p.add("0",pan1);
    	 p.add("1",pan2);
    	 JSplitPane jsp=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,tr,p);
    	 jsp.setEnabled(false);    	 
    	 con.add(jsp);
    	 setVisible(true);
    	 setResizable(false);
    	 
    	  JMenuBar jmb=new JMenuBar();
      	  setJMenuBar(jmb);
      	  JMenu jm1=new JMenu("通信");
      	  JMenu jm2=new JMenu("帮助");
      	  jmb.add(jm1);
      	  jmb.add(jm2);
      	  JMenuItem jmi1=new JMenuItem("开始界面");
          JMenuItem jmi2=new JMenuItem("通信界面");
      	  JMenuItem jmi3=new JMenuItem("退出");
      	  JMenuItem jmi4=new JMenuItem("关于");
      	  jm1.add(jmi1);
      	  jm1.add(jmi2);
      	  jm1.add(jmi3);
          jm2.add(jmi4);
      	  
          JScrollPane sp=new JScrollPane(jta);
    	  sp.setBounds(16, 32, 88, 180);
      	  con.add(jta);
      	  jta.setEditable(false);
      	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       jta.setText("");
       	  jmi1.addActionListener(
      				new ActionListener(){
      					public void actionPerformed( ActionEvent event )
      			         {
      			        		try{
      			        			showPan1(event);
      			        		}catch(Exception e)
      			        				{
      			        					e.getMessage();
      			        					e.printStackTrace();
      			        				}      			        	 
      			         }
      				}
    );
      	getContentPane().add(new JScrollPane(jta),BorderLayout.CENTER);//位置
      	  jmi2.addActionListener(
      				new ActionListener(){
      					public void actionPerformed( ActionEvent event )
      			         {
      			        		try{
      			        			
      			        			showPan2(event);
      			        			
      			        		}catch(Exception e)
      			        				{
      			        					e.getMessage();
      			        					e.printStackTrace();
      			        				}      			        	 
      			         }
      				}
      	  );
      	  jmi3.addActionListener(
    				new ActionListener(){
    					public void actionPerformed( ActionEvent event )
    			         {
    			        		try{
    			        			System.exit(0);
    			        			
    			        		}catch(Exception e)
    			        				{
    			        					e.getMessage();
    			        					e.printStackTrace();
    			        				}    			        	 
    			         }
    				}
    	  );
      	  jmi4.addActionListener(
      				new ActionListener(){
      					public void actionPerformed( ActionEvent event )
      			         {
      			        		try{
      			        			showText(event);
      			        			
      			        		}catch(Exception e)
      			        				{
      			        					e.getMessage();
      			        					e.printStackTrace();
      			        				}      			        	 
      			         }
      				}
      			  );
     }
	public void showText(ActionEvent e)
    {		
	  String str="Welcome to java world.And if you hava any questions,send email to jairdxs@yahoo.com.cn";
  	  jta.append(str);
  	  JOptionPane.showMessageDialog(null, str);
  	  
    }
	public void showPan1(ActionEvent e)
	{
		cal.show(p, "0");
	}
	public void showPan2(ActionEvent e)
	{
		cal.show(p, "1");		
	}
     public void valueChanged(TreeSelectionEvent e)
     {
    	 if(e.getSource()==tr)
    	 {
    		 DefaultMutableTreeNode node=(DefaultMutableTreeNode)tr.getLastSelectedPathComponent();
    		 String str=node.toString();
    		 if(str.equals("SocketConnection")||str.equals("开始界面"))
    		     cal.show(p, "0");
    		 else if(str.equals("通信界面"))
    		     cal.show(p, "1");
    		 
    	 }
     }
	public static void main(String args[])
	{
		Tree tf=new Tree();
	    tf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tf.show();
		
	}
}
