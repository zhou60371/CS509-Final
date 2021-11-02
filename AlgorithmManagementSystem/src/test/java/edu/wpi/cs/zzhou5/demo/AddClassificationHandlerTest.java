package edu.wpi.cs.zzhou5.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs.zzhou5.demo.AddClassificationHandler;
import edu.wpi.cs.zzhou5.demo.http.AddClassificationRequest;
import edu.wpi.cs.zzhou5.demo.http.AddClassificationResponse;

public class AddClassificationHandlerTest extends LambdaTest{
	void testSuccessInput(String incoming) throws IOException {
		AddClassificationHandler handler = new AddClassificationHandler();
		AddClassificationRequest req = new Gson().fromJson(incoming, AddClassificationRequest.class);
       
		AddClassificationResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.httpCode);
    }
	
    void testFailInput(String incoming, int failureCode) throws IOException {
    	AddClassificationHandler handler = new AddClassificationHandler();
    	AddClassificationRequest req = new Gson().fromJson(incoming, AddClassificationRequest.class);

    	AddClassificationResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(failureCode, resp.httpCode);
    }
    
    @Test
    public void testShouldBeOk() {
    	int rndNum = (int)(990*(Math.random()));
    	String var = "throwAway" + rndNum;
    	int[] arg2 = new int[] {};
    	int arg3 = 0;
//    	String var = "Greedy Algorithm";
    	
    	AddClassificationRequest ccr = new AddClassificationRequest(var,arg2,arg3);
        String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);  
        
        try {
        	testSuccessInput(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
    
//    @Test
//    public void testShouldBefail() {
//    	int rndNum = 418;
//    	String var = "throwAway" + rndNum;
//    	int arg2 = 9;
//    	int arg3 = 1;
//    	
//    	AddClassificationRequest ccr = new AddClassificationRequest(var,arg2,arg3);
//        String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
//        int SAMPLE_FAIL_CODE = 422; 
//        
//        try {
//        	testFailInput(SAMPLE_INPUT_STRING,SAMPLE_FAIL_CODE);
//        } catch (IOException ioe) {
//        	Assert.fail("Invalid:" + ioe.getMessage());
//        }
//    }
}
