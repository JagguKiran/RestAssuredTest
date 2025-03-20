package api.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.AppConfig;
import api.utilities.ExcelUtility;
import io.restassured.response.Response;

public class UserTest {
	private Faker faker;
	private User userPayload;
	private List<User> users;
	@BeforeClass
	public void fetchUserInformation() {
		/*
		faker=new Faker();
		userPayload=new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setUsername(faker.name().username());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(6,10));
		userPayload.setUserStatus(faker.number().randomDigit());
		*/
		String key=new AppConfig().getProperty("XLPATH");
		users=new ExcelUtility(key).retriveUserDetails();
		System.out.println(users);
		if(users.size()==0) {
			userPayload=new User();
			userPayload.setId(faker.idNumber().hashCode());
			userPayload.setFirstName(faker.name().firstName());
			userPayload.setUsername(faker.name().username());
			userPayload.setLastName(faker.name().lastName());
			userPayload.setEmail(faker.internet().safeEmailAddress());
			userPayload.setPassword(faker.internet().password(6,10));
			userPayload.setUserStatus(faker.number().randomDigit());
		}else {
			userPayload=users.get(0);
			System.out.println(users);
			System.out.println(userPayload);
		}
	}
	@Test(priority=1)
	public void testPostUser() {
		Response response=UserEndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	@Test(priority=2)
	public void testGetUser() {
		Response response=UserEndPoints.getUser(this.userPayload.getUsername());
		response.then().log().all();
		//Assert.assertEquals(response.getStatusCode(),200);
	}
	@Test(priority=3)
	public void testPutUser(){
		userPayload.setUserStatus(1);
		userPayload.setEmail("kiran.kiranjmps@gmail.com");
		Response response=UserEndPoints.updateUser(userPayload,userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
		//After updatation
		//UserEndPoints.getUser(this.userPayload.getUsername()).then().log().body().statusCode(200);
	}
	@Test(priority=4)
	public void testDeleteUser() {
		Response response=UserEndPoints.deleteUser(userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
}
