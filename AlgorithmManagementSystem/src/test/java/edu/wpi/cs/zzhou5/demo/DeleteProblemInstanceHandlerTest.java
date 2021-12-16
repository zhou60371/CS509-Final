package edu.wpi.cs.zzhou5.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs.zzhou5.demo.http.DeleteProblemInstanceRequest;
import edu.wpi.cs.zzhou5.demo.http.DeleteProblemInstanceResponse;

public class DeleteProblemInstanceHandlerTest extends LambdaTest{
	void testFailInput(String incoming) throws IOException {
		DeleteProblemInstanceHandler handler = new DeleteProblemInstanceHandler();
		DeleteProblemInstanceRequest req = new Gson().fromJson(incoming, DeleteProblemInstanceRequest.class);
		
		DeleteProblemInstanceResponse response = handler.handleRequest(req, createContext("delete"));
		Assert.assertEquals(422, response.httpCode);
	}
	
	@Test
	public void testShouldBeFail() {
		int id = 1;
		DeleteProblemInstanceRequest ccr = new DeleteProblemInstanceRequest(id);
		String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
		
		try {
			testFailInput(SAMPLE_INPUT_STRING);
		}catch(IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}
		
		DeleteProblemInstanceResponse de = new DeleteProblemInstanceResponse(200);
		de.toString();
	}
}
