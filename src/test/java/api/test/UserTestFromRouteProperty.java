package api.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPointsFromRouteProperty;
import api.payload.User;
import api.utilities.AppConfig;
import api.utilities.ExcelUtility;
import io.restassured.response.Response;

public class UserTestFromRouteProperty {
	private Faker faker;
	private User userPayload;
	private List<User> users;
	@BeforeClass
	public void fetchUserInformation() {
		String key=new AppConfig().getProperty("XLPATH");
		users=new ExcelUtility(key).retriveUserDetails();
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
		}
	}
	@Test(priority=1)
	public void testPostUser() {
		Response response=UserEndPointsFromRouteProperty.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	@Test(priority=2)
	public void testGetUser() {
		Response response=UserEndPointsFromRouteProperty.getUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	@Test(priority=3)
	public void testPutUser(){
		userPayload.setUserStatus(1);
		userPayload.setEmail("kiran.kiranjmps@gmail.com");
		Response response=UserEndPointsFromRouteProperty.updateUser(userPayload,userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
		//After updatation
		//UserEndPoints.getUser(this.userPayload.getUsername()).then().log().body().statusCode(200);
	}
	@Test(priority=4)
	public void testDeleteUser() {
		Response response=UserEndPointsFromRouteProperty.deleteUser(userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
}
