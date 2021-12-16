package edu.wpi.cs.zzhou5.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs.zzhou5.demo.http.UploadActivityRequest;
import edu.wpi.cs.zzhou5.demo.http.UploadActivityResponse;

public class UploadActivityHandlerTest extends LambdaTest{
	void testFailInput(String incoming) throws IOException {
		UploadActivityHandler handler = new UploadActivityHandler();
		UploadActivityRequest req = new Gson().fromJson(incoming, UploadActivityRequest.class);
		
		UploadActivityResponse response = handler.handleRequest(req, createContext("upload activity"));
		Assert.assertEquals(200, response.httpCode);
	}
	
	@Test
	public void testShouldBeOk() {
		String name = "throwAway553";
		String activity = "upload benchmark";
		String time = "2010-09-12";
		UploadActivityRequest ccr = new UploadActivityRequest(name,activity,time);
		String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
		
		try {
			testFailInput(SAMPLE_INPUT_STRING);
		}catch(IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}
		
		UploadActivityResponse up = new UploadActivityResponse("s", 200);
		up.toString();
	}
}
