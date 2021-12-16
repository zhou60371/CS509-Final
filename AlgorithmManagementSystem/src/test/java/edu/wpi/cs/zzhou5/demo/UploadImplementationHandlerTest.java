package edu.wpi.cs.zzhou5.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs.zzhou5.demo.http.UploadImplementationRequest;
import edu.wpi.cs.zzhou5.demo.http.UploadImplementationResponse;

public class UploadImplementationHandlerTest extends LambdaTest{
	void testSuccessInput(String incoming) throws IOException {
		UploadImplementationHandler handler = new UploadImplementationHandler();
		UploadImplementationRequest req = new Gson().fromJson(incoming, UploadImplementationRequest.class);
       
		UploadImplementationResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.httpCode);
    }
	
//    void testFailInput(String incoming, int failureCode) throws IOException {
//    	AddAlgorithmHandler handler = new AddAlgorithmHandler();
//    	AddAlgorithmRequest req = new Gson().fromJson(incoming, AddAlgorithmRequest.class);
//
//    	AddAlgorithmResponse resp = handler.handleRequest(req, createContext("create"));
//        Assert.assertEquals(failureCode, resp.httpCode);
//    }
    
    @Test
    public void testShouldBeOk() {
    	String arg1 = "java";
    	String arg2 = "code context /n";
    	int arg3 = 43;
    	
    	UploadImplementationRequest ccr = new UploadImplementationRequest(arg1,arg2,arg3);
        String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);  
        
        try {
        	testSuccessInput(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
        
        UploadImplementationResponse up = new UploadImplementationResponse("s", 200);
        
        UploadImplementationResponse up1 = new UploadImplementationResponse("s");
        up1.toString();
    }

}
