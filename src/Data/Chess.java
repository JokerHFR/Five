package Data;
/**
 * ������
 * @author ��
 *
 */
public class Chess {
	/**
	 * x 
	 * y      λ��
	 * color    ��ɫ
	 */
     private int x;
     private int y;
     private int color=0;
     
     public Chess(){
    	 
     }
     /**
      * �вι��캯��
      * @param x  ������
      * @param y  ������
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
