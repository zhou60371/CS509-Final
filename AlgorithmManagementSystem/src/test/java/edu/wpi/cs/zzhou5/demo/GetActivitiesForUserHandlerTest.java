package edu.wpi.cs.zzhou5.demo;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs.zzhou5.demo.http.GetActivitiesForUserRequest;
import edu.wpi.cs.zzhou5.demo.http.GetActivitiesForUserResponse;
import edu.wpi.cs.zzhou5.demo.model.Activity;

public class GetActivitiesForUserHandlerTest extends LambdaTest{
	void testFailInput(String incoming) throws IOException {
		GetActivitiesForUserHandler handler = new GetActivitiesForUserHandler();
		GetActivitiesForUserRequest req = new Gson().fromJson(incoming, GetActivitiesForUserRequest.class);
		
		GetActivitiesForUserResponse response = handler.handleRequest(req, createContext("get activities for user"));
		Assert.assertEquals(200, response.httpCode);
	}
	
	@Test
	public void testShouldBeOk() {
		String name = "zihao";
		GetActivitiesForUserRequest ccr = new GetActivitiesForUserRequest(name);
		String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
		
		try {
			testFailInput(SAMPLE_INPUT_STRING);
		}catch(IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}
		
		GetActivitiesForUserResponse get1 = new GetActivitiesForUserResponse(200,"3");
		get1.toString();
		
		GetActivitiesForUserResponse get2 = new GetActivitiesForUserResponse(200, new ArrayList<Activity>());
		Activity ac = new Activity("1","2","3");
		
		GetActivitiesForUserRequest geta = new GetActivitiesForUserRequest("kk");
		geta.toString();
	}
	
}