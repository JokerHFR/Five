package OracleClass;

public class Commond {
	private String sql="";
	private Object[]params=null;
	public Commond(String sql, Object[] params) {
		this.sql=sql;
		this.params=params;
	}
	public Object[] getParams() {
		return params;
	}
	public void setParams(Object[] params) {
		this.params = params;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	
	
}
