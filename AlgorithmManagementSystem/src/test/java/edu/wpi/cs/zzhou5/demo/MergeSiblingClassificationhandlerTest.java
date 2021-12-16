package edu.wpi.cs.zzhou5.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs.zzhou5.demo.http.MergeSiblingClassificationRequest;
import edu.wpi.cs.zzhou5.demo.http.MergeSiblingClassificationResponse;

public class MergeSiblingClassificationhandlerTest extends LambdaTest{
	void testFailInput(String incoming) throws IOException {
		MergeSiblingClassificationHandler handler = new MergeSiblingClassificationHandler();
		MergeSiblingClassificationRequest req = new Gson().fromJson(incoming, MergeSiblingClassificationRequest.class);
		
		MergeSiblingClassificationResponse response = handler.handleRequest(req, createContext("reclassify"));
		Assert.assertEquals(403, response.httpCode);
	}
	
	@Test
	public void testShouldBeOk() {
		int class1 = 2;
		int class2 = 3;
		MergeSiblingClassificationRequest ccr = new MergeSiblingClassificationRequest(class1,class2);
		String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
		
		try {
			testFailInput(SAMPLE_INPUT_STRING);
		}catch(IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}
		
		MergeSiblingClassificationResponse merge = new MergeSiblingClassificationResponse(200);
		merge.toString();
		
		MergeSiblingClassificationRequest me = new MergeSiblingClassificationRequest(1, 2);
		me.toSting();
	}
}