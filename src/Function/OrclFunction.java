package Function;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import OracleClass.Commond;
import OracleClass.DataBaseOracle;
import OracleClass.DataTable;

public class OrclFunction {
	/**
	 * �����Լ����˺��ҵ��Լ����ǳ�
	 * @param uname �˺�
	 * @return �ǳ�
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
	 * �����Լ����˺��ҵ��Լ�������
	 * @param uname �˺�
	 * @return ����
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
	 * ��¼��Ӯʱ������
	 * @param uname �Լ����˺�
	 * @param i ��Ӯ�ж�
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
	 * �����ַ����ȹ̶�
	 * @param len  �趨���� �����ÿո�
	 * @param s  ���ع̶��õ�����
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
	        		new Commond("insert into F_history values(f_history_seq.nextval,?,?,'Ӯ',?)",new Object[]{OrclFunction.outputdate(),duishou,id})       		
			});}
			else{
				DataBaseOracle.UpDate(new Commond[]{
					new Commond("insert into F_history values(f_history_seq.nextval,?,?,'��',?)",new Object[]{OrclFunction.outputdate(),duishou,id})
});
			}
		    }catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
    /**
     * �����ǰ���¼�
     * @return ʱ��
     */
	public static String outputdate(){
		Date d=new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String dateString = formatter.format(d);
		return(dateString);
		}
}
