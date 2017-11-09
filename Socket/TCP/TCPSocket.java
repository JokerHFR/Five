package TCP;

import java.io.*;
import java.net.*;

import Listener.TCPSocketListener;


public class TCPSocket implements Runnable{
/**
 * TCP网络通信
 * @author hu
 */
	
    private BufferedReader in=null;//字符输入流
    private PrintWriter out=null;//字符输出流
    private Socket client=null;
    private TCPSocketListener handle=null;
    private String uname;
    private int i;
    private int type;
    
    /**
     * 端口监听事件绑定
     * @param handle
     */
    public void addTCPSocketListener(TCPSocketListener handle){
    this.handle=handle;
    }
    
    /**
     * 客户端网络通信构造函数
     * @param IP服务器IP地址
     * @param iPort服务器端口
     * @throws Exception
     */
   public TCPSocket(String IP,int iPort) throws Exception{
	   this(new Socket(IP,iPort));
	 
  
    }
   /**
    *  服务器端网络通信构造函数
    * @param 
    * @throws Exception
    */
   public TCPSocket(Socket client) throws Exception{
	   this.client=client;
	   this.in=new BufferedReader(new InputStreamReader(this.client.getInputStream()));
		this.out=new PrintWriter(this.client.getOutputStream());
		
   }
   
   /**
    * 线程开启
    */
   public void start(){
	   new Thread(this).start();
   }
   
   /**
    * 关闭线程
    */
   public void close(){
	   try {
			this.in.close();
			this.out.close();
			this.client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
   /**
    * 发送消息
    * @param msg
    */
    public void send(String msg){
    	out.println(msg);
		out.flush();
		
    }
    
    public static String getIPAdress(){
    	String ip="";
    	try {
			ip=InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return ip;
    }
    /**
     * 接收消息
     */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
    	while(true){
    		
    		String s=this.in.readLine();
			if(this.handle!=null){
    			this.handle.input(this, s);
    			}
			if(s.equals("断开连接")||s.equals("已经断开连接")){
				break;
			}
			}	
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}




    
 
}
