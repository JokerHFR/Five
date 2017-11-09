package OracleClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DataBaseOracle {
        private static String driver="";
        private static String url="";
        private static String uname="";
        private static String uword="";
        
        /**
         * ��̬���캯�����ഴ��ʱ�Զ�����
         */
        static{
        	driver=PropertiesReader.getProperties("driver");
        	url=PropertiesReader.getProperties("url");
			uname=PropertiesReader.getProperties("uname");
			uword=PropertiesReader.getProperties("uword");
			try {
				Class.forName(DataBaseOracle.driver);//�������ݿ�����
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
        }
        /**
         * 
         */
        public DataBaseOracle(){
        
        }
        /**
         * �������ݿ�����
         * @return ���ݿ����Ӷ���
         */
        public static Connection getConnection(){
        	Connection conn=null;
        	try {
				conn=DriverManager.getConnection(DataBaseOracle.url,DataBaseOracle.uname,DataBaseOracle.uword);
			} catch (SQLException e) {
				System.out.println("���ݿ�����ʧ�ܣ�"+e.getMessage());
			}
			return conn;
        	
        }
        
        /**
         * �������ݿ��������
         * @param conn���ݿ����Ӷ���
         * @param sqlSQL���
         * @param paramsSQL����
         * @return
         */
        public static PreparedStatement getCOmmand(Connection conn,String sql,Object[]params){
        	PreparedStatement stmt = null;
        	try {
        		stmt=conn.prepareStatement(sql);
        		if(params!=null&&params.length>0){
        			for(int i=0;i<params.length;i++){
        				if(params[i] instanceof java.util.Date){
        					java.util.Date temp=(java.util.Date)params[i];
        					java.sql.Date date=new java.sql.Date(temp.getTime());
        					stmt.setDate(i+1, date);
        				}else{
        					stmt.setObject(i+1, params[i]);
        				}
        			}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("���ݿ���󴴽�ʧ�ܣ�"+e.getMessage());
			}
            return stmt;
        }
        

        /**
         * ���ݿ��ѯ����
         * @param sqlSQL���
         * @param paramsSQL����
         * @return ������
         * @throws SQLException
         */
        

        public static DataTable executeQuery(String sql,Object[]params) throws SQLException{
        	
        	Connection conn=DataBaseOracle.getConnection();
        	PreparedStatement cmd=DataBaseOracle.getCOmmand(conn, sql, params);
        	ResultSet rs=cmd.executeQuery();
        	DataTable dt=new DataTable(rs);
        	DataBaseOracle.Close(conn);
        	return dt;
        }
         //�޲εĲ�ѯ��
        public static DataTable executeQuery(String sql) throws SQLException   {
        	return DataBaseOracle.executeQuery(sql, null);
        } 
        /**
         * 
         * @param cmd SQL�޸ĵĲ�����
         * @return �޸ĵ�����
         * @throws SQLException
         */
        public static int UpDate(Commond[]cmd) throws SQLException{
        	Connection conn=DataBaseOracle.getConnection();
        	conn.setAutoCommit(false);
             int result=0;
				try {
					for(int i=0;i<cmd.length;i++){
						 PreparedStatement stmt=DataBaseOracle.getCOmmand(conn, cmd[i].getSql(), cmd[i].getParams());
					int rs=stmt.executeUpdate();
					result+=rs;
					}
					conn.commit();
					return result;
				} catch (SQLException e) {
					conn.rollback();
					throw e;
				}finally{
					conn.setAutoCommit(true);
					DataBaseOracle.Close(conn);
				} 	
        }
        
        public static void Insert(Commond[]cmd) throws SQLException{
        	Connection conn=DataBaseOracle.getConnection();
        	conn.setAutoCommit(false);
				try {
					for(int i=0;i<cmd.length;i++){
						 PreparedStatement stmt=DataBaseOracle.getCOmmand(conn, cmd[i].getSql(), cmd[i].getParams());
                     stmt.executeUpdate();
					}
					conn.commit();
				} catch (SQLException e) {
					conn.rollback();
					throw e;
				}finally{
					conn.setAutoCommit(true);
					DataBaseOracle.Close(conn);
				} 	
    	}
    	
        
        public static void Delete(Commond[]cmd) throws SQLException{
        	Connection conn=DataBaseOracle.getConnection();
        	conn.setAutoCommit(false);
				try {
					for(int i=0;i<cmd.length;i++){
						 PreparedStatement stmt=DataBaseOracle.getCOmmand(conn, cmd[i].getSql(), cmd[i].getParams());
					stmt.executeUpdate();
					}
					conn.commit();
				} catch (SQLException e) {
					conn.rollback();
					throw e;
				}finally{
					conn.setAutoCommit(true);
					DataBaseOracle.Close(conn);
				} 	
    	}
    	
        public static void Close(Connection conn){
        	if(conn!=null){
    			try {
    				conn.close();
    			} catch (SQLException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}		
        }  
      }
}
