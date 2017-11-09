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
         * 静态构造函数，类创建时自动调用
         */
        static{
        	driver=PropertiesReader.getProperties("driver");
        	url=PropertiesReader.getProperties("url");
			uname=PropertiesReader.getProperties("uname");
			uword=PropertiesReader.getProperties("uword");
			try {
				Class.forName(DataBaseOracle.driver);//加载数据库驱动
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
         * 连接数据库驱动
         * @return 数据库连接对象
         */
        public static Connection getConnection(){
        	Connection conn=null;
        	try {
				conn=DriverManager.getConnection(DataBaseOracle.url,DataBaseOracle.uname,DataBaseOracle.uword);
			} catch (SQLException e) {
				System.out.println("数据库连接失败："+e.getMessage());
			}
			return conn;
        	
        }
        
        /**
         * 创建数据库操作对象
         * @param conn数据库连接对象
         * @param sqlSQL语句
         * @param paramsSQL参数
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
				System.out.println("数据库对象创建失败："+e.getMessage());
			}
            return stmt;
        }
        

        /**
         * 数据库查询操作
         * @param sqlSQL语句
         * @param paramsSQL参数
         * @return 数据类
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
         //无参的查询类
        public static DataTable executeQuery(String sql) throws SQLException   {
        	return DataBaseOracle.executeQuery(sql, null);
        } 
        /**
         * 
         * @param cmd SQL修改的参数类
         * @return 修改的行数
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
