package Frame;



import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.*;


import Function.OrclFunction;
import OracleClass.DataBaseOracle;
import OracleClass.DataTable;

/**
 * ս����ѯ���
 * @author Administrator
 *
 */
public class GradesFrame extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3364096542831711122L;
	private JScrollPane jp=new JScrollPane();
	private JTextArea ja=new JTextArea();
	private JLabel jl=new JLabel("�ǳ�         ����      ʤ��      ����     ʤ��");
	private JLabel jl1=new JLabel("�����������ѯ����ҵ��ǳƣ�");
	private JTextField jf=new JTextField("���»�");
	private JButton jb=new JButton("��ѯ");
	private JButton jb2=new JButton("�˳�");
	
	 
    public GradesFrame(){
    	
    	  this.setTitle("ս�����а�");
    	  this.setSize(400,500);
    	  this.setLocationRelativeTo(null);
    	  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	  this.setResizable(false);
    	  this.setLayout(null);
    	  
    	  
    	  this.jl.setBounds(5, 5, 400, 30);
    	  this.jl.setFont(new Font("����",Font.PLAIN,16));
          jl.setForeground(Color.RED);

    	  this.add(jl);
    	  
    	  this.jp.setBounds(0,35, 395, 350);
    	  this.jp.getViewport().add(this.ja);
  		  this.add(jp);
  		  ja.setFont(new Font("����",Font.PLAIN,16));
//          ja.setForeground(Color.RED);
 		  this.ja.setEnabled(false);
  		  this.ja_append();
  		  
  		  this.jl1.setBounds(10, 390, 230, 30);
  		  jl1.setFont(new Font("����",Font.PLAIN,16));
  		  this.jf.setBounds(230, 390, 100, 30);
  		  jf.setFont(new Font("����",Font.PLAIN,16));
  		  this.jb.setBounds(100, 430, 100, 30);
  		  this.jb2.setBounds(220, 430, 100, 30);
  		  this.jb.addActionListener(this);
  		  this.jb2.addActionListener(this);
  		  this.add(jb2);
  		  this.add(jl1);
  		  this.add(jf);
  		  this.add(jb);
    }
    
    /**
     * ��ѯս������
     */
    public void ja_append(){
    	String s="";
    	int a=0;
    	int b=0;
    	float c=0;
    	String d="";
    	try {
			DataTable dt=DataBaseOracle.executeQuery("select * from F_User order by usergrades desc");
			for(int i=0;i<dt.getRowCount();i++){
				a=Integer.parseInt(dt.getValue(i, 6).toString());
				b=Integer.parseInt(dt.getValue(i, 7).toString());
				if(b!=0){
                   c = (float)(a*100)/(a+b);
				   DecimalFormat df = new DecimalFormat("0.00");//��ʽ��С��������Ĳ�0
				   d = df.format(c);//���ص���String���͵�
				}else{
					DecimalFormat df = new DecimalFormat("0.00");//��ʽ��С��������Ĳ�0
					   d = df.format(0);//���ص���String���͵�
				}
				
				s=dt.getValue(i, 1).toString()
				+OrclFunction.Str(10, dt.getValue(i, 3).toString())
				+OrclFunction.Str(10, dt.getValue(i, 6).toString())
				+OrclFunction.Str(10, dt.getValue(i, 7).toString())
				+OrclFunction.Str(10, d+"%");
				this.ja.append(s+'\n');	
			}
	
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    
    /**
     * ��ѯ������ʷս��
     * @param uname �ǳ�
     */
    public void fund(String uname){
    	String s;
    	try {
			DataTable dt=DataBaseOracle.executeQuery("select h.historydate,h.historycom,h.historyresult from F_History h join F_User u on h.userid=u.userid where u.username='"+uname+"'order by h.historyid desc");
			for(int i=0;i<dt.getRowCount();i++){
				s=dt.getValue(i, 0).toString()
				+OrclFunction.Str(10,"��ս"+dt.getValue(i, 1).toString())
				+OrclFunction.Str(10,dt.getValue(i, 2).toString());
				this.ja.append(s+'\n');      
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.jb){
			this.ja.setText("");
			this.jl.setText(this.jf.getText()+"����ʷս��:");
			this.fund(this.jf.getText());
			
		}
		if(e.getSource()==this.jb2){
			this.dispose();
		}
	}
}
