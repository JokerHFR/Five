package OracleClass;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;



public class DataTable {
  private ArrayList<String> commlisum =new ArrayList<String>();
  private ArrayList<ArrayList<Object>> datalist=new ArrayList<ArrayList<Object>>();
  
  public DataTable(ResultSet rs) throws SQLException{
	  ResultSetMetaData rsmd=rs.getMetaData();
	  for(int i=1;i<=rsmd.getColumnCount();i++){
		  this.commlisum.add(rsmd.getColumnClassName(i));
	  }
	  
	  while(rs.next()){
		  ArrayList<Object>row =new ArrayList<Object>();
		  for(int i=1;i<=commlisum.size();i++){
			  row.add(rs.getObject(i));
		  }
		  this.datalist.add(row);
	  }
  }
  


public Object getValue(int row,int col){
	  return datalist.get(row).get(col);
  }
public int getRowCount(){
	return datalist.size();
}

public int getColCount(){
	return commlisum.size();
}
}


