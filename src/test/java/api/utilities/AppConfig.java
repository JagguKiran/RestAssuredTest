package api.utilities;

import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class AppConfig {
	private String path="./src/test/resources/routes.properties";
	private FileInputStream fis;
	public String getProperty(String key){
		Properties prop=new Properties();
		try {
			fis=new FileInputStream(path);
			prop.load(fis);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(fis!=null)fis.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return prop.getProperty(key);
	}
	public static String currentDate() {
		LocalDateTime date=LocalDateTime.now();
		DateTimeFormatter format=DateTimeFormatter.ofPattern("ss_mm_hh_dd_MM_yyyy");
		return format.format(date);
	}
}
