package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payload.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints 
{
	
	public static Response createUser(UserPojo payload) 
	{
				
		Response response=given()
	    .header("Authorization","Bearer d30deb74bc6d046184a3f80b95e2cb1c88090fb8b971b39237a72627ab71ba6d")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(Routes.post_url);
		
		return response;
		
		
	}
	
	public static Response readUser(int userid) 
	{
				
		Response response=given()
	    .header("Authorization","Bearer d30deb74bc6d046184a3f80b95e2cb1c88090fb8b971b39237a72627ab71ba6d")
		.pathParam("userid", userid)
		.when()
		.get(Routes.get_url);
		return response;
		
		
	}
	
	public static Response  editUser(int userid, UserPojo payload) 
	{
				
		Response response= given()
	    .header("Authorization","Bearer d30deb74bc6d046184a3f80b95e2cb1c88090fb8b971b39237a72627ab71ba6d")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("userid", userid)
		.body(payload)
		.when()
		.put(Routes.put_url);
		
		return response;
		
		
	}
	
	public static Response deleteUser(int  id) 
	{
				
		Response response=given()
		.header("Authorization","Bearer d30deb74bc6d046184a3f80b95e2cb1c88090fb8b971b39237a72627ab71ba6d")
		.pathParam("userid", id)
		.when()
		.delete(Routes.delete_url);
		
		return response;
		
		
	}

}
