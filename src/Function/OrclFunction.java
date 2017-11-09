package Function;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import OracleClass.Commond;
import OracleClass.DataBaseOracle;
import OracleClass.DataTable;

public class OrclFunction {
	/**
	 * 根据自己的账号找到自己的昵称
	 * @param uname 账号
	 * @return 昵称
	 */
	public  static String updateUname(String uname){
		DataTable dt = null;
		try {
			dt = DataBaseOracle.executeQuery("select UserName from F_User where Usernum='"+uname+"'");	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dt.getValue(0, 0).toString();
	}
	
	/**
	 * 根据自己的账号找到自己的主键
	 * @param uname 账号
	 * @return 主键
	 */
	public  static String updateID(String uname){
		DataTable dt = null;
		try {
			dt = DataBaseOracle.executeQuery("select UserID from F_User where Usernum='"+uname+"'");	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dt.getValue(0, 0).toString();
	}
	/**
	 * 记录输赢时的数据
	 * @param uname 自己的账号
	 * @param i 输赢判断
	 */
	public static void updatewing(String ID,int i){
	
		try {
			if(i==1){
			DataBaseOracle.UpDate(new Commond[]{
	        		new Commond("update F_User set win=win+? where UserID=?",new Object[]{1,ID}),
	        		new Commond("update F_User set UserGrades=UserGrades+? where UserID=?",new Object[]{5,ID})
			});}
			else{
				DataBaseOracle.UpDate(new Commond[]{
		        		new Commond("update F_User set shu=shu+? where UserID=?",new Object[]{1,ID}),
		        		new Commond("update F_User set UserGrades=UserGrades-? where UserID=?",new Object[]{2,ID})
				});
			}
		    }catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
	
	
	/**
	 * 数据字符长度固定
	 * @param len  设定长度 多余用空格补
	 * @param s  返回固定好的数据
	 * @return
	 */
	public static String Str(int len,String s){
    	StringBuffer sr=new StringBuffer();
    	for(int i=0;i<len-s.length();i++){
    		sr.append(" ");
    	}
    	sr.append(s);
    	return sr.toString();
    }
	
	
	public  static String updateuID(String uname){
		DataTable dt = null;
		try {
			dt = DataBaseOracle.executeQuery("select UserID from F_User where Username='"+uname+"'");	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dt.getValue(0, 0).toString();
	}
	
	
	public  static String updatename(String uname){
		DataTable dt = null;
		try {
			dt = DataBaseOracle.executeQuery("select UserName from F_User where UserID="+uname+"");	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dt.getValue(0, 0).toString();
	}
	
    public static void insertGrades(String duishou,String id,int i){
    	try {
			if(i==1){
			DataBaseOracle.UpDate(new Commond[]{
	        		new Commond("insert into F_history values(f_history_seq.nextval,?,?,'赢',?)",new Object[]{OrclFunction.outputdate(),duishou,id})       		
			});}
			else{
				DataBaseOracle.UpDate(new Commond[]{
					new Commond("insert into F_history values(f_history_seq.nextval,?,?,'输',?)",new Object[]{OrclFunction.outputdate(),duishou,id})
});
			}
		    }catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
    /**
     * 输出当前的事件
     * @return 时间
     */
	public static String outputdate(){
		Date d=new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String dateString = formatter.format(d);
		return(dateString);
		}
}
