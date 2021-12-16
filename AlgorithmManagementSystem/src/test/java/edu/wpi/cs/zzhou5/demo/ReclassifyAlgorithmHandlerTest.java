package edu.wpi.cs.zzhou5.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs.zzhou5.demo.http.ReclassifyAlgorithmRequest;
import edu.wpi.cs.zzhou5.demo.http.ReclassifyAlgorithmResponse;

public class ReclassifyAlgorithmHandlerTest extends LambdaTest{
	void testFailInput(String incoming) throws IOException {
		ReclassifyAlgorithmHandler handler = new ReclassifyAlgorithmHandler();
		ReclassifyAlgorithmRequest req = new Gson().fromJson(incoming, ReclassifyAlgorithmRequest.class);
		
		ReclassifyAlgorithmResponse response = handler.handleRequest(req, createContext("reclassify"));
		Assert.assertEquals(200, response.httpCode);
	}
	
	@Test
	public void testShouldBeOk() {
		int algoId = 43;
		int classId = 106;
		ReclassifyAlgorithmRequest ccr = new ReclassifyAlgorithmRequest(algoId,classId);
		String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
		
		try {
			testFailInput(SAMPLE_INPUT_STRING);
		}catch(IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}
		
		ReclassifyAlgorithmResponse re = new ReclassifyAlgorithmResponse(200, "error");
		re.toString();
	}
}
