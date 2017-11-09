package Frame;



import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import Function.OrclFunction;
import Listener.JPanellistener;
import OracleClass.Commond;
import OracleClass.DataBaseOracle;

/**
 * ��Ϸ�ͻ���
 * @author ��
 *
 */
public class ClientFrame extends JFrame implements JPanellistener,ActionListener{
       /**
	 * 
	 */
	private static final long serialVersionUID = -4437661981079306004L;
	
	/*
	 * ���������
	 */
       private JLabel jl=new JLabel("�Է��ǳƣ�");
       private JLabel IPlist=new JLabel(); //���߶���
       
	   private JPanel imagePanel=null;   //�������
	   private JLabel   beijing=new JLabel();
       private GamePanel gamePanel=null;  //�������
       
       private JLabel  jpg=new JLabel();
       private JLabel  jpg2=new JLabel();
       
       
       private JLabel j3=new JLabel();            //����ִ������
       private JLabel j2=new JLabel("��1�غ�:");  //��ʾ�غ���
       private JButton jb=new JButton();         //����غ�������ɫ
       
       private JButton select=new JButton("ս����ѯ");
  	   private JButton  jb1=new JButton("ƥ��");
       private JButton  jb2=new JButton("�˳�");
       

      private String[] colortype={"��ɫ","��ɫ"};
      private ImageIcon board=null;
      
      private String uname="";

       /**
        * 
        * @param type ��������
        * @param uname �˻�
        */
       public ClientFrame(String uname){
    	  this.uname=uname;
    	  
    	  this.setTitle("������");
     	  this.setSize(650, 485);
     	  this.setLocationRelativeTo(null);
     	  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     	  this.setResizable(false);
     	  this.setLayout(null);
     	  
     	  
     	  this.jl.setBounds(470, 10, 100, 30);
     	  this.IPlist.setFont(new Font("����",Font.PLAIN,16));
    	  this.IPlist.setBounds(470, 40, 170, 30);
    	  this.add(jl);
    	  this.add(IPlist);
     	  
     	  this.beijing.setBounds(0, 0,452 , 452);
     	  this.board=new ImageIcon("png/1.png");
     	  this.beijing.setIcon(board);
     	  imagePanel = (JPanel)this.getContentPane();
          imagePanel.setOpaque(false);              
          this.getLayeredPane().setLayout(null);
     	  this.getLayeredPane().add(beijing,new Integer(Integer.MIN_VALUE));    //���õײ㱳������
 
     	  
     	  this.j3.setBounds(470, 80, 100, 30);
     	  this.j3.setText("��ִ");
     	  this.j2.setBounds(470, 120, 100, 30);
    	  this.jb.setBounds(540, 120, 30, 30);
    	  this.jb.setContentAreaFilled(false); 
//		  this.jb.setBorderPainted(false);
    	  this.jb.setIcon(new ImageIcon("png/white.png"));
    	  this.add(j2);
		  this.add(jb);
		  this.add(j3);
		  
		  this.select.setBounds(490, 355, 100, 30);
		  this.select.addActionListener(this);
		  this.jb1.setBounds(470, 400, 60, 30);
     	  this.jb1.addActionListener(this);
     	  this.jb2.setBounds(540, 400, 60, 30);
     	  this.jb2.addActionListener(this);
     	  this.add(select);
     	  this.add(jb1);
     	  this.add(jb2);
     	  
     	  this.gamePanel=new GamePanel(uname);
     	  this.gamePanel.setLocation(0, 0);
     	  this.gamePanel.addJPanellistener(this);
     	  this.add(gamePanel);
     	  
     	 
     	  

          
     	this.jpg.setBounds(550, 30, 60, 60);
     	this.jpg2.setBounds(470, 180, 150, 150);
     	this.jpg2.setIcon(new ImageIcon("png/"+OrclFunction.updateUname(uname)+".jpg"));
     	this.add(jpg);
     	this.add(jpg2);
       }
    /**
     * ����Ŀǰ˭�Ļغϣ���ǰ�غϵĲ���
     */
	@Override
	public void outtype(ImageIcon chess,int bushu) {
		// TODO Auto-generated method stub
		this.jb.setIcon(chess);
		this.j2.setText("��"+bushu+"�غϣ�");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.select){
			GradesFrame gf=new GradesFrame();
			gf.setVisible(true);
		}else if(e.getSource()==this.jb1){
			this.jb1.setEnabled(false);
			this.gamePanel.getClient().send(1+":"+OrclFunction.updateUname(uname));
		}
		else if(e.getSource()==this.jb2){
			try {
				this.gamePanel.getClient().send("�Ͽ�����");
				this.gamePanel.setDuishou("");
				DataBaseOracle.UpDate(new Commond[]{
		        		new Commond("update F_User set UserState=? where Usernum=?",new Object[]{0,uname})
				});
			    }catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.dispose();
		}
	}

   /**
    * �������ֵ�����
    */
	public void outuname(String uname,int i) {
		// TODO Auto-generated method stub
		this.IPlist.setText(uname);
		this.jpg.setIcon(new ImageIcon("png/"+uname+"2.jpg"));
		 this.j3.setText("��ִ"+this.colortype[i-1]);
	}

}
