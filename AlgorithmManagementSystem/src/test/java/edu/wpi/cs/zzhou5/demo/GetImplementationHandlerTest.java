package edu.wpi.cs.zzhou5.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs.zzhou5.demo.db.ImplementationsDAO;
import edu.wpi.cs.zzhou5.demo.http.GetImplementationRequest;
import edu.wpi.cs.zzhou5.demo.http.GetImplementationResponse;
import edu.wpi.cs.zzhou5.demo.model.Implementation;

public class GetImplementationHandlerTest extends LambdaTest{

    public void testGetImple(String incoming) throws IOException {
		GetImplementationHandler handler = new GetImplementationHandler();
		GetImplementationRequest req = new Gson().fromJson(incoming, GetImplementationRequest.class);

		GetImplementationResponse resp = handler.handleRequest(req, createContext("list"));
        
        Assert.assertEquals(200, resp.httpCode);
    }
    
    @Test
    public void shouldBeOk() {
    	String SAMPLE_INPUT_STRING = "{\"id\": \"1\"}";
    	
    	try {
    		testGetImple(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    	
    	GetImplementationResponse get1 = new GetImplementationResponse(200, "e");
    	get1.toString();
    	
    	GetImplementationRequest get2 = new GetImplementationRequest(20);
    	get2.setID(20);
    }
}