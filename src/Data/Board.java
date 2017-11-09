package Data;


/**
 * 
 * @author ��
 *
 */
public class Board {
 
     private Chess[][] chess=new Chess[15][15];
     
     public Board(){
    	for(int i=0;i<15;i++){
    		for(int j=0;j<15;j++){
    			chess[i][j]=new Chess();
    			chess[i][j].setdate(i, j, 0);
        	}
    	}
     }
     
     
     /**
      * �����������ʤ���㷨
      * @param xIndex ���x������
      * @param yIndex ���y������
      * @param color  �����ɫ
      * @return
      */
     public boolean win(int xIndex,int yIndex,int color){
    	 int continueCount=1;
    	 //��������Ѱ��  
         for(int x=xIndex-1;x>=0;x--){   
             if(this.getChess()[x][yIndex].getColor()==color){  
                 continueCount++;  
             }else  
                 break;  
         }  
        //������Ѱ��  
         for(int x=xIndex+1;x<=14;x++){  

            if(this.getChess()[x][yIndex].getColor()==color){  
               continueCount++;  
            }else  
               break;  
         }  
         if(continueCount>=5){  
               return true;  
         }else   
         continueCount=1;  
           
         //������һ����������  
         //��������  
         for(int y=yIndex-1;y>=0;y--){  
 
             if(this.getChess()[xIndex][y].getColor()==color){  
                 continueCount++;  
             }else  
                 break;  
         }  
         //��������Ѱ��  
         for(int y=yIndex+1;y<=14;y++){  

             if(this.getChess()[xIndex][y].getColor()==color)  
                 continueCount++;  
             else  
                break;  
           
         }  
         if(continueCount>=5)  
             return true;  
         else  
             continueCount=1;  
           
           
         //������һ�������������б��  
         //����Ѱ��  
         for(int x=xIndex+1,y=yIndex-1;y>=0&&x<=14;x++,y--){  

             if(this.getChess()[x][y].getColor()==color){  
                 continueCount++;  
             }  
             else break;  
         }  
         //����Ѱ��  
         for(int x=xIndex-1,y=yIndex+1;x>=0&&y<=14;x--,y++){  

             if(this.getChess()[x][y].getColor()==color){  
                 continueCount++;  
             }  
             else break;  
         }  
         if(continueCount>=5)  
             return true;  
         else continueCount=1;  
           
           
         //������һ�������������б��  
         //����Ѱ��  
         for(int x=xIndex-1,y=yIndex-1;x>=0&&y>=0;x--,y--){  
 
             if(this.getChess()[x][y].getColor()==color)  
                 continueCount++;  
             else break;  
         }  
         //����Ѱ��  
         for(int x=xIndex+1,y=yIndex+1;x<=14&&y<=14;x++,y++){  

             if(this.getChess()[x][y].getColor()==color)  
                 continueCount++;  
             else break;  
         }  
         if(continueCount>=5)  
             return true;  
         else continueCount=1;  
           
         return false;  
       } 
     
     /**
      * �������
      */
    public void clear(){
    	for(int i=0;i<15;i++){
    		for(int j=0;j<15;j++){
    			chess[i][j].setdate(i, j, 0);
        	}
    	}
    }
    
	public Chess[][] getChess() {
		return chess;
	}

	public void setChess(Chess[][] chess) {
		this.chess = chess;
	}
    	
}
