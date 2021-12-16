package edu.wpi.cs.zzhou5.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs.zzhou5.demo.UserRegistHandler;
import edu.wpi.cs.zzhou5.demo.http.UserRegistRequest;
import edu.wpi.cs.zzhou5.demo.http.UserRegistResponse;

public class UserRegistHandlerTest extends LambdaTest{
	void testSuccessInput(String incoming) throws IOException {
    	UserRegistHandler handler = new UserRegistHandler();
    	UserRegistRequest req = new Gson().fromJson(incoming, UserRegistRequest.class);
       
    	UserRegistResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.httpCode);
    }
	
//    void testFailInput(String incoming, int failureCode) throws IOException {
//    	UserRegistHandler handler = new UserRegistHandler();
//    	UserRegistRequest req = new Gson().fromJson(incoming, UserRegistRequest.class);
//
//    	UserRegistResponse resp = handler.handleRequest(req, createContext("create"));
//        Assert.assertEquals(failureCode, resp.httpCode);
//    }

   
    // NOTE: this proliferates large number of constants! Be mindful
    @Test
    public void testShouldBeOk() {
    	int rndNum = (int)(990*(Math.random()));
    	String var = "throwAway" + rndNum;
    	
    	
    	UserRegistRequest ccr = new UserRegistRequest(var, "666");
        String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);  
        
        try {
        	testSuccessInput(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
        
        UserRegistResponse us = new UserRegistResponse("s", 200);
        us.toString();
    }
}
