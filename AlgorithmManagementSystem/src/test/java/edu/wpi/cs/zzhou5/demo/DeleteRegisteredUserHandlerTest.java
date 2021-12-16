package edu.wpi.cs.zzhou5.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs.zzhou5.demo.http.DeleteRegisteredUserRequest;
import edu.wpi.cs.zzhou5.demo.http.DeleteRegisteredUserResponse;

public class DeleteRegisteredUserHandlerTest extends LambdaTest{
	void testFailInput(String incoming) throws IOException {
		DeleteRegisteredUserHandler handler = new DeleteRegisteredUserHandler();
		DeleteRegisteredUserRequest req = new Gson().fromJson(incoming, DeleteRegisteredUserRequest.class);
		
		DeleteRegisteredUserResponse response = handler.handleRequest(req, createContext("delete"));
		Assert.assertEquals(200, response.httpCode);
	}
	
	@Test
	public void testShouldBeFail() {
		String name = "testing";
		DeleteRegisteredUserRequest ccr = new DeleteRegisteredUserRequest(name);
		String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
		
		try {
			testFailInput(SAMPLE_INPUT_STRING);
		}catch(IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}
		
		DeleteRegisteredUserResponse de = new DeleteRegisteredUserResponse(200, "error");
		de.toString();
	}
}