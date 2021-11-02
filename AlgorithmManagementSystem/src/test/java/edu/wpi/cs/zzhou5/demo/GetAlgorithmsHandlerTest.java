package edu.wpi.cs.zzhou5.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs.zzhou5.demo.db.AlgorithmsDAO;
import edu.wpi.cs.zzhou5.demo.http.GetAlgorithmsRequest;
import edu.wpi.cs.zzhou5.demo.http.GetAlgorithmsResponse;
import edu.wpi.cs.zzhou5.demo.model.Algorithm;

public class GetAlgorithmsHandlerTest extends LambdaTest{

    public void testGetList(String incoming) throws IOException {
		GetAlgorithmsHandler handler = new GetAlgorithmsHandler();
		GetAlgorithmsRequest req = new Gson().fromJson(incoming, GetAlgorithmsRequest.class);

		GetAlgorithmsResponse resp = handler.handleRequest(req, createContext("list"));
        
        Assert.assertEquals(200, resp.statusCode);
    }
    
    @Test
    public void shouldBeOk() {
    	String SAMPLE_INPUT_STRING = "{\"arg1\": \"18\"}";
    	
    	try {
    		testGetList(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
}

