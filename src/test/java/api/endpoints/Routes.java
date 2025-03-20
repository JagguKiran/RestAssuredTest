package api.endpoints;

/*
 	Swagger URI /Base URL-> https://petstore.swagger.io/v2
  	Create User (Post) /EndPoint ->https://petstore.swagger.io/v2/user
  	Get User (Get) /EndPoint -->https://petstore.swagger.io/v2/user/{username}
  	Update User (Put)/EndPoint -->https://petstore.swagger.io/v2/user/{username}
  	Delete User (Delete)/EndPoint -->https://petstore.swagger.io/v2/user/{username}

 */
public class Routes {
	public static String base_url="https://petstore.swagger.io/v2";
	//User module (There are other modules store and pet) 
	public static String post_url=base_url+"/user";
	public static String get_url=base_url+"/user/{username}";
	public static String put_url=base_url+"/user/{username}";
	public static String delete_url=base_url+"/user/{username}";
}
