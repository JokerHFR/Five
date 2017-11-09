package TCP;

import java.io.*;
import java.net.*;

import Listener.TCPSocketListener;


public class TCPSocket implements Runnable{
/**
 * TCP����ͨ��
 * @author hu
 */
	
    private BufferedReader in=null;//�ַ�������
    private PrintWriter out=null;//�ַ������
    private Socket client=null;
    private TCPSocketListener handle=null;
    private String uname;
    private int i;
    private int type;
    
    /**
     * �˿ڼ����¼���
     * @param handle
     */
    public void addTCPSocketListener(TCPSocketListener handle){
    this.handle=handle;
    }
    
    /**
     * �ͻ�������ͨ�Ź��캯��
     * @param IP������IP��ַ
     * @param iPort�������˿�
     * @throws Exception
     */
   public TCPSocket(String IP,int iPort) throws Exception{
	   this(new Socket(IP,iPort));
	 
  
    }
   /**
    *  ������������ͨ�Ź��캯��
    * @param 
    * @throws Exception
    */
   public TCPSocket(Socket client) throws Exception{
	   this.client=client;
	   this.in=new BufferedReader(new InputStreamReader(this.client.getInputStream()));
		this.out=new PrintWriter(this.client.getOutputStream());
		
   }
   
   /**
    * �߳̿���
    */
   public void start(){
	   new Thread(this).start();
   }
   
   /**
    * �ر��߳�
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
    * ������Ϣ
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
     * ������Ϣ
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
			if(s.equals("�Ͽ�����")||s.equals("�Ѿ��Ͽ�����")){
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
