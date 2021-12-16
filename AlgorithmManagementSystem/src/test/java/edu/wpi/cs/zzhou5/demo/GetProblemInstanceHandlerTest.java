package edu.wpi.cs.zzhou5.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs.zzhou5.demo.http.GetProblemInstancesRequest;
import edu.wpi.cs.zzhou5.demo.http.GetProblemInstancesResponse;

public class GetProblemInstanceHandlerTest extends LambdaTest{
	public void testGetImple(String incoming) throws IOException {
		GetProblemInstancesHandler handler = new GetProblemInstancesHandler();
		GetProblemInstancesRequest req = new Gson().fromJson(incoming, GetProblemInstancesRequest.class);

		GetProblemInstancesResponse resp = handler.handleRequest(req, createContext("list"));
        
        Assert.assertEquals(200, resp.httpCode);
    }
    
    @Test
    public void shouldBeOk() {
    	String SAMPLE_INPUT_STRING = "{\"id\": \"40\"}";
    	
    	try {
    		testGetImple(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    	
    	GetProblemInstancesResponse ge = new GetProblemInstancesResponse(200, "2");
    	ge.toString();
    	
    	GetProblemInstancesRequest g1 = new GetProblemInstancesRequest(20);
    	g1.setID(20);
    	
    }
}
