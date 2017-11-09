package Frame;

import java.awt.*;
import java.net.*;
import java.util.ArrayList;



import javax.swing.*;


import Function.OrclFunction;
import Listener.TCPSocketListener;
import TCP.TCPSocket;



    /**
     * 服务器端
     * @author Administrator
     *
     */
	public  class SeverFrame extends JFrame implements TCPSocketListener{
	    /**
		 * 设置服务器端
		 */
		private static final long serialVersionUID = 601490157857654075L;
		/***************************************************/
		 
		 private JPanel jp1=new JPanel();
		private JLabel jl=new JLabel("本机IP地址：");
		private JTextField IPlist=new JTextField();
		
		
		/***************************************************/
		private JScrollPane jp=new JScrollPane();
		 private JTextArea ja=new JTextArea();
		 
		 /***************************************************/
	    
        private int iPort=10001;
        private ServerSocket server=null;
        
        private ArrayList<TCPSocket> clientList=new ArrayList<TCPSocket>();
        private TCPSocket[][] clientList2=new TCPSocket[10][2];
        private int n=0;
        private int m=0;
        
		public SeverFrame(){
			for(int i=0;i<this.clientList2.length;i++){
				for(int j=0;j<this.clientList2[0].length;j++){
					this.clientList2[i][j]=null;
				}
			}
			String ip="0.0.0.0";
			try {
				ip=InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.setSize(500, 400);
			this.setTitle("服务器聊天台");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
			this.setLayout(new BorderLayout());
			
			this.jp1.setBackground(Color.LIGHT_GRAY);
		    this.jp1.setLayout(new FlowLayout(FlowLayout.LEFT));
		    this.IPlist.setPreferredSize(new Dimension(300,24));
            this.IPlist.setText(ip);
		    this.jp1.add(jl);
		    this.jp1.add(IPlist);
			this.add(jp1,BorderLayout.NORTH);
			
			
			this.jp.getViewport().add(this.ja);
			this.add(jp,BorderLayout.CENTER);
		
		}
		
        public void link(){
        	try {
				this.server=new ServerSocket(this.iPort);
			 	while(true){
			 		TCPSocket client=new TCPSocket(this.server.accept());
			 		client.addTCPSocketListener(this);
			 		this.clientList.add(client);
			 		client.start();
	        		this.ja.append("客户端连接成功"+"\n");	
	        	
	        		
	        	}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	

		@Override
		public void input(TCPSocket tcp, String msg) {
			// TODO Auto-generated method stub
			this.ja.append(msg+"\n");
			String[]s=msg.split(":");
			
			if(msg.equals("断开连接")){
					for(int i=0;i<2;i++){
				if(this.clientList2[tcp.getI()][i]==tcp){
							this.clientList2[tcp.getI()][i].send("已经断开连接");
							this.clientList2[tcp.getI()][i].close();
							this.clientList2[tcp.getI()][i]=null;
						
				}
				else if(this.clientList2[tcp.getI()][i]!=tcp&&this.clientList2[tcp.getI()][i]!=null){
							this.clientList2[tcp.getI()][i].send("0");
				}
			}
			}
			if(s.length==2){
				if(s[0].equals("1")){
    				tcp.setI(m);
    				tcp.setUname(s[1]);
    				TCPSocket client=tcp;
    				this.clientList2[m][n]=client;
    				n+=1;
    				if(n==2){
    					this.clientList2[m][0].send(1+"-"+this.clientList2[m][1].getUname());
    					this.clientList2[m][1].send(2+"-"+this.clientList2[m][0].getUname());
    					m+=1;
    					n=0;
    				}
    			}
				else  if(s[0].equals("2")){
    				String[]sr=s[1].split("_");
    				OrclFunction.insertGrades(sr[1],sr[0] , 1);
    				OrclFunction.updatewing(sr[0], 1);
    				OrclFunction.insertGrades(OrclFunction.updatename(sr[0]),OrclFunction.updateuID(sr[1]) , 2);
    				OrclFunction.updatewing(OrclFunction.updateuID(sr[1]), 2);
    			}
			}
			for(int i=0;i<2;i++){
				if(this.clientList2[tcp.getI()][i]!=tcp&&this.clientList2[tcp.getI()][i]!=null){
					this.clientList2[tcp.getI()][i].send(msg);
				
			}
			}
		}
	}
