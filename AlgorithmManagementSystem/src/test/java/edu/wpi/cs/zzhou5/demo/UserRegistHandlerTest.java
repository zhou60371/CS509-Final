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
	
    void testFailInput(String incoming, int failureCode) throws IOException {
    	UserRegistHandler handler = new UserRegistHandler();
    	UserRegistRequest req = new Gson().fromJson(incoming, UserRegistRequest.class);

    	UserRegistResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(failureCode, resp.httpCode);
    }

   
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
    }
    
    
//    @Test
//    public void testGarbageInput() {
//    	String SAMPLE_INPUT_STRING = "{\"sdsd\": \"e3\", \"hgfgdfgdfg\": \"this is not a number\"}";
//        
//        try {
//        	testFailInput(SAMPLE_INPUT_STRING, 400);
//        } catch (IOException ioe) {
//        	Assert.fail("Invalid:" + ioe.getMessage());
//        }
//    }
//    
//    // overwrites into it
//    @Test
//    public void testCreateSystemConstant() {
//    	// create constant
//    	int rndNum = (int)(990*(Math.random()));
//        CreateConstantRequest csr = new CreateConstantRequest("to-delete-again" + rndNum, 9.29837, true);
//        
//        CreateConstantResponse resp = new CreateConstantHandler().handleRequest(csr, createContext("create"));
//        Assert.assertEquals(200, resp.httpCode);
//    }
}
