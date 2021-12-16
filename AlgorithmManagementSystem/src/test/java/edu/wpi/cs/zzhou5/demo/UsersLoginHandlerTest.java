package edu.wpi.cs.zzhou5.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;

import edu.wpi.cs.zzhou5.demo.LambdaTest;
import edu.wpi.cs.zzhou5.demo.http.*;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class UsersLoginHandlerTest extends LambdaTest{

    void testInput(String incoming, String outgoing) throws IOException {
    	UsersLoginHandler handler = new UsersLoginHandler();
    	LoginRequest req = new Gson().fromJson(incoming, LoginRequest.class);
        LoginResponse response = handler.handleRequest(req, createContext("compute"));

        Assert.assertEquals(outgoing, response.result);
//        Assert.assertEquals(200, response.statusCode);
    }
	
    void testFailInput(String incoming, String outgoing) throws IOException {
    	UsersLoginHandler handler = new UsersLoginHandler();
    	LoginRequest req = new Gson().fromJson(incoming, LoginRequest.class);

    	LoginResponse response = handler.handleRequest(req, createContext("compute"));

        Assert.assertEquals(400, response.statusCode);
    }
    
    @Test
    public void testCalculatorSimple() {
    	String SAMPLE_INPUT_STRING = "{\"arg1\": \"throwAway553\", \"arg2\": \"666\"}";
        String RESULT = "true";
        
        try {
        	testInput(SAMPLE_INPUT_STRING, RESULT);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
    
    @Test
    public void invalidUser() {
    	String SAMPLE_INPUT_STRING = "{\"arg1\": \"zihao\", \"arg2\": \"ggg4568\"}";
        String RESULT = "false";
        
        try {
        	testFailInput(SAMPLE_INPUT_STRING, RESULT);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
        
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.toString();
        
        LoginRequest loginRequest1 = new LoginRequest("1","2");
        
        LoginResponse loginResponse = new LoginResponse(200,"11");
        loginResponse.toString();
        LoginResponse loginResponse1 = new LoginResponse(201,"11");
        loginResponse1.toString();
    }
}
