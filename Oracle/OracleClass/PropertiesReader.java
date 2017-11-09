package OracleClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	public static String getProperties(String keyname){
		String keyValue=null;
		
		try {
			InputStream in=PropertiesReader.class.getClassLoader().getResourceAsStream("SqlData.properties");
			Properties format=new Properties();
			format.load(in);
			keyValue=format.getProperty(keyname);
		} catch (IOException e) {
			e.printStackTrace();
            System.out.println("δ�ҵ�properties�ļ���δ֪����"+e.getMessage());	
		}
		return keyValue;
	}
}
