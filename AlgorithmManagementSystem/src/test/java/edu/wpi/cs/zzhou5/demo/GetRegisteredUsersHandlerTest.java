package edu.wpi.cs.zzhou5.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import edu.wpi.cs.zzhou5.demo.http.GetRegisteredUsersResponse;

public class GetRegisteredUsersHandlerTest extends LambdaTest{
	@Test
    public void testGetList() throws IOException {
		GerRegisteredUsersHandler handler = new GerRegisteredUsersHandler();

		GetRegisteredUsersResponse resp = handler.handleRequest(null, createContext("get users"));
        
        boolean hasE = false;
        if(resp.users.get(1) != null) {
        	hasE = true;
        	System.out.println(resp.users.get(1));
        }
        Assert.assertEquals(true, hasE);
        Assert.assertEquals(200, resp.httpCode);
        
        GetRegisteredUsersResponse re = new GetRegisteredUsersResponse(200, "200");
        re.toString();
//    }
	}
}
