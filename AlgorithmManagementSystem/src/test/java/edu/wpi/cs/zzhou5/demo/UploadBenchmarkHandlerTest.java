package edu.wpi.cs.zzhou5.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs.zzhou5.demo.http.UploadBenchmarkRequest;
import edu.wpi.cs.zzhou5.demo.http.UploadBenchmarkResponse;

public class UploadBenchmarkHandlerTest extends LambdaTest{
	void testSuccessInput(String incoming) throws IOException {
		UploadBenchamrkHandler handler = new UploadBenchamrkHandler();
		UploadBenchmarkRequest req = new Gson().fromJson(incoming, UploadBenchmarkRequest.class);
       
		UploadBenchmarkResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.httpCode);
    }
	
	@Test
	public void testShouldOk() {
		String arg1 = "I7"; //cpu
		String arg2 = "2017-8-9"; //date
		String arg3 = "28"; //threas
		String arg4 = "5"; //cores
		String arg5 = "29MB";
		String arg6 = "39MB";
		String arg7 = "49MB";
		String arg8 = "90MB";
		String arg9 = "2000";
		int arg10 = 6;//imples
		int arg11 = 3;//problem Instance
				
		
		UploadBenchmarkRequest ccr = new UploadBenchmarkRequest(arg1, arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11);
		String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);  
		
		try {
        	testSuccessInput(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
		
		UploadBenchmarkResponse up = new UploadBenchmarkResponse("s", 200);
		up.toString();
	}
}
