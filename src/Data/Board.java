package Data;


/**
 * 
 * @author 胡
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
      * 返回五子棋获胜的算法
      * @param xIndex 棋的x轴坐标
      * @param yIndex 棋的y轴坐标
      * @param color  棋的颜色
      * @return
      */
     public boolean win(int xIndex,int yIndex,int color){
    	 int continueCount=1;
    	 //横向向西寻找  
         for(int x=xIndex-1;x>=0;x--){   
             if(this.getChess()[x][yIndex].getColor()==color){  
                 continueCount++;  
             }else  
                 break;  
         }  
        //横向向东寻找  
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
           
         //继续另一种搜索纵向  
         //向上搜索  
         for(int y=yIndex-1;y>=0;y--){  
 
             if(this.getChess()[xIndex][y].getColor()==color){  
                 continueCount++;  
             }else  
                 break;  
         }  
         //纵向向下寻找  
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
           
           
         //继续另一种情况的搜索：斜向  
         //东北寻找  
         for(int x=xIndex+1,y=yIndex-1;y>=0&&x<=14;x++,y--){  

             if(this.getChess()[x][y].getColor()==color){  
                 continueCount++;  
             }  
             else break;  
         }  
         //西南寻找  
         for(int x=xIndex-1,y=yIndex+1;x>=0&&y<=14;x--,y++){  

             if(this.getChess()[x][y].getColor()==color){  
                 continueCount++;  
             }  
             else break;  
         }  
         if(continueCount>=5)  
             return true;  
         else continueCount=1;  
           
           
         //继续另一种情况的搜索：斜向  
         //西北寻找  
         for(int x=xIndex-1,y=yIndex-1;x>=0&&y>=0;x--,y--){  
 
             if(this.getChess()[x][y].getColor()==color)  
                 continueCount++;  
             else break;  
         }  
         //东南寻找  
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
      * 清除棋盘
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
