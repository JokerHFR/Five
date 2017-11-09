package Frame;

import java.awt.GridLayout;
import java.awt.event.*;


import javax.swing.*;

import Listener.JPanellistener;
import Listener.TCPSocketListener;


import TCP.TCPSocket;


import Data.Board;
import Function.OrclFunction;
    /**
     * ������Ϸ���
     * @author ��
     *
     */
public class GamePanel extends JPanel implements ActionListener,MouseListener,TCPSocketListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5294600215654009940L;
	
	private JButton[][]chessbutton=new JButton[15][15];
	private ImageIcon chess=null; //�ҷ�����ͼƬ
	private ImageIcon otherchess=null; //��������ͼƬ
	
	private boolean iswhite =true;
	private boolean[][]blank=new boolean[15][15];
	private Board board=new Board();
	
	private int iPort=10001;
    private TCPSocket client=null;
    
    private int type;
    private int othertype;
    
    private int bushu=1;
    
    private String uname;
    private String duishou;

    public void setDuishou(String duishou) {
		this.duishou = duishou;
	}



	private String now="";
    private String other="";
    
    private JPanellistener jpl=null; 
   /**
    * ��Ӽ�����
    * @param jpl 
    */
	public void addJPanellistener( JPanellistener jpl){
		this.jpl=jpl;
	}
	
    public GamePanel(String uname){
    	this.uname=uname;
    	
    	this.setSize(452, 452);
    	this.setOpaque(false);
 		this.setLayout(new GridLayout(15,15));//���ô���Ĳ��ַ�ʽ�����񲼾֣�
 		
 		
    	  
 		for(int i=0;i<chessbutton.length;i++){
 			for(int j=0;j<chessbutton[0].length;j++){
 				this.chessbutton[i][j]=new JButton();
 				this.chessbutton[i][j].addActionListener(this);
 				this.chessbutton[i][j].addMouseListener(this);
 				this.chessbutton[i][j].setContentAreaFilled(false); 
 				this.chessbutton[i][j].setBorderPainted(false);
 				this.add(chessbutton[i][j]);
 				this.blank[i][j]=true;
 			}
 		}
 		try {
			this.client=new TCPSocket(TCPSocket.getIPAdress(),this.iPort);
			this.client.addTCPSocketListener(this);
			this.client.start();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
    /**
     * ��굥���¼�
     */
	@Override
	public void mousePressed(MouseEvent e) {
		
		if(this.iswhite){

			for(int i=0;i<chessbutton.length;i++){
	 			for(int j=0;j<chessbutton[0].length;j++){
	 				if(e.getSource()==this.chessbutton[i][j]&&this.blank[i][j]){
	 					this.chessbutton[i][j].setIcon(this.chess);
	 					this.iswhite=false;
	 					this.blank[i][j]=false;
	 					this.board.getChess()[i][j].setColor(this.type);
	 					this.client.send(i+","+j);
	 					this.bushu+=1;
	 					this.jpl.outtype(otherchess,this.bushu);
	 					if(this.board.win(i, j, this.type)){
	 						this.bushu=1;
	 						this.client.send("2:"+OrclFunction.updateID(this.uname)+"_"+this.duishou);
	 						JOptionPane.showMessageDialog(this, this.now+"��ʤ!"+"\n"+"��ϲ����ʤ����","��ʾ",JOptionPane.PLAIN_MESSAGE);
	 						this.board.clear();
	 						this.clear();
	 					}
	 					break;
	 				}
	 			}
}
}		
}

    /**
     * ����������
     * ��ʼ��
     */
	public void clear(){
		for(int i=0;i<chessbutton.length;i++){
 			for(int j=0;j<chessbutton[0].length;j++){
 				this.chessbutton[i][j].setIcon(null);
 				this.blank[i][j]=true;
 			}
		}
	}
    
	@Override
	public void input(TCPSocket tcp, String msg) {
        if(msg.equals("�Ѿ��Ͽ�����")){
        	this.client.close();
        }else  if(msg.equals("0")){
        	JOptionPane.showMessageDialog(this, "��Ķ����Ѿ�����","��ʾ",JOptionPane.PLAIN_MESSAGE);
        }
		String[] s=msg.split(",");
		String[] u=msg.split("-");
		if(u.length==2){
			
			    this.duishou=u[1];
				this.type=Integer.parseInt(u[0]);
				this.jpl.outuname(u[1],this.type);
				
		    	if(type==1){
		    	this.chess=new ImageIcon("png/white.png");
		    	this.otherchess=new ImageIcon("png/black.png");
		    	this.now="��ɫ";
		    	this.other="��ɫ";
		    	this.othertype=2;
		    	this.iswhite=true;
		    	}
		    	else{
		    		this.chess=new ImageIcon("png/black.png");
		    		this.otherchess=new ImageIcon("png/white.png");
		    		this.now="��ɫ";
		        	this.other="��ɫ";
		        	this.othertype=1;
		    		this.iswhite=false;
		}
		}
		else if(s.length==2){
		           int i=Integer.parseInt(s[0]);
		           int j=Integer.parseInt(s[1]);
 					this.chessbutton[i][j].setIcon(this.otherchess);
 					this.iswhite=true;
 					this.blank[i][j]=false;
 					this.board.getChess()[i][j].setColor(this.othertype);
 					this.bushu+=1;
 					this.jpl.outtype(chess,this.bushu);
 					if(this.board.win(i, j, this.othertype)){
 						this.bushu=1;
 						JOptionPane.showMessageDialog(this, this.other+"��ʤ!"+"\n"+"���ź��������ˣ�","��ʾ",JOptionPane.PLAIN_MESSAGE);	
 						this.board.clear();
 						this.clear();
 					}
 				}
	     }


	public TCPSocket getClient() {
		return client;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


 }
   
