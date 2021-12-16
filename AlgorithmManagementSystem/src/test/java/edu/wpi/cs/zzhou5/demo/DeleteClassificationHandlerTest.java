package edu.wpi.cs.zzhou5.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs.zzhou5.demo.http.DeleteClassificationRequest;
import edu.wpi.cs.zzhou5.demo.http.DeleteClassificationResponse;


public class DeleteClassificationHandlerTest extends LambdaTest{
	void testSuccessInput(String incoming) throws IOException{
		DeleteClassificationHandler handler = new DeleteClassificationHandler();
		DeleteClassificationRequest req = new Gson().fromJson(incoming, DeleteClassificationRequest.class);
		
		DeleteClassificationResponse response = handler.handleRequest(req, createContext("delete"));
		Assert.assertEquals(200, response.httpCode);
	}
	
	@Test
	public void testShouldBeSuccess() {
		int id = 2;
		DeleteClassificationRequest ccr = new DeleteClassificationRequest(id);
		String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
		
		try {
			testSuccessInput(SAMPLE_INPUT_STRING);
		}catch(IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}
		
		DeleteClassificationResponse de = new DeleteClassificationResponse(200, "error");
		de.toString();
	}
}
