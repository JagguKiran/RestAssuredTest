package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataProviderTest {
	@Test(priority=1, dataProvider="UserDetails", dataProviderClass=DataProviders.class)
	public void testPostUser(User user) {
		Response response=UserEndPoints.createUser(user);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	@Test(priority=2, dataProvider="UserName", dataProviderClass=DataProviders.class)
	public void testGetUser(String userName) {
		Response response=UserEndPoints.getUser(userName);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	@Test(priority=3,dataProvider="UserName",dataProviderClass=DataProviders.class)
	public void testDeleteUser(String userName) {
		Response response=UserEndPoints.deleteUser(userName);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
}
