package api.test;



import static org.testng.Assert.assertEquals;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.UserPojo;
import freemarker.log.Logger;
import groovyjarjarantlr4.v4.runtime.misc.LogManager;
import io.restassured.response.Response;

public class UserTests 
{
	Faker faker;
	UserPojo userPayload;
	public org.apache.logging.log4j.Logger logger;
	@BeforeClass
	public void setupData()
	{
		faker= new Faker();
		userPayload = new UserPojo();
		int ramdomN = faker.number().numberBetween(0, 1);
	    String sex;
	    if (ramdomN == 0) {
	        sex = "Male";
	    } else {
	        sex = "Female";
	    }
		
		userPayload.setName(faker.name().username());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setGender(sex);
		userPayload.setStatus("active");
		
		logger= org.apache.logging.log4j.LogManager.getLogger(this.getClass());
	}
	@Test(priority = 1)
	public void testPostUser(ITestContext context)
	{
		logger.info("***************Creating user*******************");
		Response response=UserEndPoints.createUser(userPayload);
		response.then().log().all();
		
	    assertEquals(response.getStatusCode(), 201);
	    
	  int id =  response.jsonPath().getInt("id");
	  
	 context.setAttribute("id", id);   
	    logger.info("***************user is created*******************");	
	}
	@Test(priority = 2)
	public void testGetUserbyID(ITestContext context)
    {
	logger.info("***************Reading user*******************");
		int id = (int) context.getAttribute("id");
	Response response=UserEndPoints.readUser(id);
	response.then().log().all();
	
	assertEquals(response.getStatusCode(), 200);
	
	logger.info("***************Displayed user*******************");
		
    }
	
	@Test(priority = 3)
	public void  testEditUserById(ITestContext context) 
	
	{
		logger.info("***************Updating user*******************");
	     int id = (int) context.getAttribute("id");
		userPayload.setName(faker.name().username());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		Response response=UserEndPoints.editUser(id , userPayload);
		response.then().log().body();
		
		assertEquals(response.getStatusCode(), 200);
		
		Response responseAfrerUpdate=UserEndPoints.readUser(id);
		assertEquals(responseAfrerUpdate.getStatusCode(), 200);
		
		logger.info("***************upadated user*******************");
			
	}
	@Test(priority = 4)
	public void testDeleteUser(ITestContext context) 
	{
	logger.info("***************deleting user*******************");
		int id = (int) context.getAttribute("id");
	Response response =UserEndPoints.deleteUser(id);
	response.then().log().all();
	
	assertEquals(response.getStatusCode(), 204);
	logger.info("***************deleted user*******************");
	}
}
