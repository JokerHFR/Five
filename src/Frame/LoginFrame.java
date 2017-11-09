package Frame;
	import java.awt.event.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

import OracleClass.Commond;
import OracleClass.DataBaseOracle;


    /**
     * 登入界面
     * @author 胡
     *
     */
	public class LoginFrame extends JFrame implements ActionListener { 
		/**
		 * 
		 */
		private static final long serialVersionUID = -4077850198321009706L;
		
		private JLabel lUserName=new JLabel("用户账号:");
		private JTextField tUserName=new JTextField("king");
		
		private JLabel lPassWord=new JLabel("用户密码:");
		private JPasswordField tPassWord=new JPasswordField("123");
		
		private JButton bEnter=new JButton("登录");
		private JButton bExit=new JButton("退出");

		
		
		public LoginFrame() { 
			
			this.setTitle("登录窗体");
			this.setSize(222, 160);//设置窗体的大小
			//this.setLocation(500, 500);//设置窗体的位置
			this.setLocationRelativeTo(null);//设置窗体居中显示
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗体的关闭方式（默认值为窗体的隐藏）
			this.setResizable(false);//设置不能通过窗体的拖拽，改变窗体的大小
			
			this.setLayout(null);//设置窗体的布局方式（按照坐标进行布局）
			
			lUserName.setSize(80, 24);//设置大小
			lUserName.setLocation(12, 12);//设置位置
			this.add(lUserName);
			
			tUserName.setSize(128, 24);//设置大小
			tUserName.setLocation(75, 12);//设置位置
			this.add(tUserName);
			
			lPassWord.setSize(80, 24);//设置大小
			lPassWord.setLocation(12, 48);//设置位置
			this.add(lPassWord);
			
			tPassWord.setSize(128, 24);//设置大小
			tPassWord.setLocation(75, 48);//设置位置
			this.add(tPassWord);
			
			bEnter.setSize(90, 24);//设置大小
			bEnter.setLocation(12, 84);//设置位置
			this.add(bEnter);
			this.bEnter.addActionListener(this);
			
			bExit.setSize(90, 24);//设置大小
			bExit.setLocation(112, 84);//设置位置
			this.add(bExit);
			this.bExit.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()==bEnter) { //登录处理
				String userWord=new String(tPassWord.getPassword());
				if (login(this.tUserName.getText(),userWord)==1) { 			
                    this.loginsuccess(this.tUserName.getText());
                    ClientFrame client=new ClientFrame(this.tUserName.getText());
					this.dispose();//关闭窗体，并释放窗体的内存空间。
					client.setVisible(true);
				} else if(login(this.tUserName.getText(),userWord)==0){ 
					JOptionPane.showMessageDialog(this, "用户名或密码输入错误！","消息提示",JOptionPane.ERROR_MESSAGE);//显示消息提示框
				}else{
					JOptionPane.showMessageDialog(this, "用户已登录！","消息提示",JOptionPane.ERROR_MESSAGE);//显示消息提示框
				}
			} else if(e.getSource()==bExit){ //退出处理
				System.exit(0);//直接结束进程，退出程序。
			}
		}
		/**
		 * 登入判断
		 * @param uname 账户名
		 * @param uword  账户密码
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
		 * 得到该账户的登入状态
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


