package edu.wpi.cs.zzhou5.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs.zzhou5.demo.http.DeleteBenchmarkRequest;
import edu.wpi.cs.zzhou5.demo.http.DeleteBenchmarkResponse;

public class DeleteBenchmarkHandlerTest extends LambdaTest{
	void testFailInput(String incoming) throws IOException {
		DeleteBenchmarkHandler handler = new DeleteBenchmarkHandler();
		DeleteBenchmarkRequest req = new Gson().fromJson(incoming, DeleteBenchmarkRequest.class);
		
		DeleteBenchmarkResponse response = handler.handleRequest(req, createContext("delete"));
		Assert.assertEquals(422, response.httpCode);
	}
	
	@Test
	public void testShouldBeOk() {
		int id = 2;
		DeleteBenchmarkRequest ccr = new DeleteBenchmarkRequest(id);
		String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
		
		try {
			testFailInput(SAMPLE_INPUT_STRING);
		}catch(IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}
		
		DeleteBenchmarkResponse de = new DeleteBenchmarkResponse(200);
		de.toString();
	}
}
