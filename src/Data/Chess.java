package Data;
/**
 * 棋子类
 * @author 胡
 *
 */
public class Chess {
	/**
	 * x 
	 * y      位置
	 * color    颜色
	 */
     private int x;
     private int y;
     private int color=0;
     
     public Chess(){
    	 
     }
     /**
      * 有参构造函数
      * @param x  横坐标
      * @param y  纵坐标
      * @param color
      */
     public void setdate(int x,int y,int color){
    	 this.x=x;
    	 this.y=y;
    	 this.color=color;
     }
     
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
     
}
