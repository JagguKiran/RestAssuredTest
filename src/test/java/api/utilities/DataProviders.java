package api.utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import api.payload.User;

public class DataProviders {

	@DataProvider(name="UserDetails")
	public Iterator<User> getAllUsers() {
		AppConfig config=new AppConfig();
		String path=config.getProperty("XLPATH");
		List<User> users=new ExcelUtility(path).retriveUserDetails();
		return users.iterator();
	}
	
	@DataProvider(name="UserName")
	public Iterator<String> getUserName(){
		AppConfig config=new AppConfig();
		String path=config.getProperty("XLPATH");
		List<User> users=new ExcelUtility(path).retriveUserDetails();
		List<String> al=new ArrayList<String>();
		for(User user:users)al.add(user.getUsername());
		return al.iterator();
	}
}
