package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPointsFromRouteProperty {
	public static ResourceBundle getURL() {
		return ResourceBundle.getBundle("routes");
	}
	public static Response createUser(User payload){
		return given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when().post(getURL().getString("POST_URL"));
	}
	public static Response getUser(String userName){
		return given().pathParam("username",userName).when().get(getURL().getString("GET_URL"));
	}
	public static Response updateUser(User payload,String userName) {
		return given().contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("username",userName).body(payload).when().put(getURL().getString("PUT_URL"));
	}
	public static Response deleteUser(String userName) {
		return given().pathParam("username",userName).when().delete(getURL().getString("DELETE_URL"));
	}
}
