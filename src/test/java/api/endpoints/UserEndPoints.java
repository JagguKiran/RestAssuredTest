package api.endpoints;
import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


/*
 	User module class 
 	Implements CRUD Operations to the User module api 
 */
public class UserEndPoints{
	public static Response createUser(User payload){
		return given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when().post(Routes.post_url);
	}
	public static Response getUser(String userName){
		return given().pathParam("username",userName).when().get(Routes.get_url);
	}
	public static Response updateUser(User payload,String userName) {
		return given().contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("username",userName).body(payload).when().put(Routes.put_url);
	}
	public static Response deleteUser(String userName) {
		return given().pathParam("username",userName).when().delete(Routes.delete_url);
	}
}
