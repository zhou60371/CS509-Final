package edu.wpi.cs.zzhou5.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs.zzhou5.demo.http.UploadProblemInstanceRequest;
import edu.wpi.cs.zzhou5.demo.http.UploadProblemInstanceResponse;

public class UploadProblemInstanceHandlerTest extends LambdaTest{
	public void testSuccessInput(String incoming) throws IOException {
		UploadProblemInstanceHandler handler = new UploadProblemInstanceHandler();
		UploadProblemInstanceRequest req = new Gson().fromJson(incoming, UploadProblemInstanceRequest.class);
		UploadProblemInstanceResponse resp = handler.handleRequest(req, createContext("uploadProblemInstance"));
		Assert.assertEquals(422, resp.httpCode);
	}
    
    @Test
    public void testShouldBeOk() {
    	int caseType = 1;
    	String content = "MTIz";
    	int algo = 40;
    	String user = "pineapple";
    	
    	UploadProblemInstanceRequest ccr = new UploadProblemInstanceRequest(caseType, content, algo, user);
        String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);  
        
        try {
        	testSuccessInput(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
        
        UploadProblemInstanceResponse up = new UploadProblemInstanceResponse("s", 200);
        up.toString();
    }

}

