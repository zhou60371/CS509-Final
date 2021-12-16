package edu.wpi.cs.zzhou5.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs.zzhou5.demo.http.DeleteAlgorithmRequest;
import edu.wpi.cs.zzhou5.demo.http.DeleteAlgorithmResponse;

public class DeleteAlgorithmHandlerTest extends LambdaTest{
	void testFailInput(String incoming) throws IOException {
		DeleteAlgorithmHandler handler = new DeleteAlgorithmHandler();
		DeleteAlgorithmRequest req = new Gson().fromJson(incoming, DeleteAlgorithmRequest.class);
		
		DeleteAlgorithmResponse response = handler.handleRequest(req, createContext("delete"));
		Assert.assertEquals(422, response.httpCode);
	}
	
	@Test
	public void testShouldBeOk() {
		int id = 39;
		DeleteAlgorithmRequest ccr = new DeleteAlgorithmRequest(id);
		String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
		
		try {
			testFailInput(SAMPLE_INPUT_STRING);
		}catch(IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}
		
		DeleteAlgorithmResponse delete1 = new DeleteAlgorithmResponse(200);
		delete1.toString();
	}
}