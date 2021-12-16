package edu.wpi.cs.zzhou5.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs.zzhou5.demo.http.DeleteImplementationRequest;
import edu.wpi.cs.zzhou5.demo.http.DeleteImplementationResponse;

public class DeleteImplementationHandlerTest extends LambdaTest{
	void testSuccessInput(String incoming) throws IOException {
		DeleteImplementationHandler handler = new DeleteImplementationHandler();
		DeleteImplementationRequest req = new Gson().fromJson(incoming, DeleteImplementationRequest.class);
		
		DeleteImplementationResponse response = handler.handleRequest(req, createContext("delete"));
		Assert.assertEquals(422, response.httpCode);
	}
	
	@Test
	public void testShouldBeSuccess() {
		int id = 1;
		DeleteImplementationRequest ccr = new DeleteImplementationRequest(id);
		String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
		
		try {
			testSuccessInput(SAMPLE_INPUT_STRING);
		}catch(IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}
		
		DeleteImplementationResponse de = new DeleteImplementationResponse(200);
		de.toString();
	}
}