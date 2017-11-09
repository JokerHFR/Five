package Frame;
	import java.awt.event.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

import OracleClass.Commond;
import OracleClass.DataBaseOracle;


    /**
     * �������
     * @author ��
     *
     */
	public class LoginFrame extends JFrame implements ActionListener { 
		/**
		 * 
		 */
		private static final long serialVersionUID = -4077850198321009706L;
		
		private JLabel lUserName=new JLabel("�û��˺�:");
		private JTextField tUserName=new JTextField("king");
		
		private JLabel lPassWord=new JLabel("�û�����:");
		private JPasswordField tPassWord=new JPasswordField("123");
		
		private JButton bEnter=new JButton("��¼");
		private JButton bExit=new JButton("�˳�");

		
		
		public LoginFrame() { 
			
			this.setTitle("��¼����");
			this.setSize(222, 160);//���ô���Ĵ�С
			//this.setLocation(500, 500);//���ô����λ��
			this.setLocationRelativeTo(null);//���ô��������ʾ
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ô���Ĺرշ�ʽ��Ĭ��ֵΪ��������أ�
			this.setResizable(false);//���ò���ͨ���������ק���ı䴰��Ĵ�С
			
			this.setLayout(null);//���ô���Ĳ��ַ�ʽ������������в��֣�
			
			lUserName.setSize(80, 24);//���ô�С
			lUserName.setLocation(12, 12);//����λ��
			this.add(lUserName);
			
			tUserName.setSize(128, 24);//���ô�С
			tUserName.setLocation(75, 12);//����λ��
			this.add(tUserName);
			
			lPassWord.setSize(80, 24);//���ô�С
			lPassWord.setLocation(12, 48);//����λ��
			this.add(lPassWord);
			
			tPassWord.setSize(128, 24);//���ô�С
			tPassWord.setLocation(75, 48);//����λ��
			this.add(tPassWord);
			
			bEnter.setSize(90, 24);//���ô�С
			bEnter.setLocation(12, 84);//����λ��
			this.add(bEnter);
			this.bEnter.addActionListener(this);
			
			bExit.setSize(90, 24);//���ô�С
			bExit.setLocation(112, 84);//����λ��
			this.add(bExit);
			this.bExit.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()==bEnter) { //��¼����
				String userWord=new String(tPassWord.getPassword());
				if (login(this.tUserName.getText(),userWord)==1) { 			
                    this.loginsuccess(this.tUserName.getText());
                    ClientFrame client=new ClientFrame(this.tUserName.getText());
					this.dispose();//�رմ��壬���ͷŴ�����ڴ�ռ䡣
					client.setVisible(true);
				} else if(login(this.tUserName.getText(),userWord)==0){ 
					JOptionPane.showMessageDialog(this, "�û����������������","��Ϣ��ʾ",JOptionPane.ERROR_MESSAGE);//��ʾ��Ϣ��ʾ��
				}else{
					JOptionPane.showMessageDialog(this, "�û��ѵ�¼��","��Ϣ��ʾ",JOptionPane.ERROR_MESSAGE);//��ʾ��Ϣ��ʾ��
				}
			} else if(e.getSource()==bExit){ //�˳�����
				System.exit(0);//ֱ�ӽ������̣��˳�����
			}
		}
		/**
		 * �����ж�
		 * @param uname �˻���
		 * @param uword  �˻�����
		 * @return
		 */
		public int login(String uname,String uword) { 
			int flag=0;
				try {
					PreparedStatement stmt=DataBaseOracle.getCOmmand(DataBaseOracle.getConnection(),
					"select * from F_User where  usernum=? and userword =?",new Object[]{uname,uword});
					ResultSet rs=stmt.executeQuery();
					if( rs.next()){ 
						if(Integer.parseInt(rs.getString(5))==0){
							flag=1;
						}else{
							flag=2;
						}
					}else{
						flag=0;
					}
				} 
				catch (SQLException e1) {
					e1.printStackTrace();
				}
				return flag;
	  }
		
		
		/**
		 * �õ����˻��ĵ���״̬
		 * @param uname
		 */
		public void loginsuccess(String uname){
			try {
				DataBaseOracle.UpDate(new Commond[]{
		        		new Commond("update F_User set UserState=? where Usernum=?",new Object[]{1,uname})
				});
			    }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


