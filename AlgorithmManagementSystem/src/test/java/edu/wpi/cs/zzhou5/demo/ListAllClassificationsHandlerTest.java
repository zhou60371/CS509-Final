package edu.wpi.cs.zzhou5.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import edu.wpi.cs.zzhou5.demo.LambdaTest;
import edu.wpi.cs.zzhou5.demo.ListAllClassificationsHandler;
import edu.wpi.cs.zzhou5.demo.http.AllClassificationsResponse;
import edu.wpi.cs.zzhou5.demo.model.Classification;

public class ListAllClassificationsHandlerTest extends LambdaTest{
	@Test
    public void testGetList() throws IOException {
		ListAllClassificationsHandler handler = new ListAllClassificationsHandler();

		AllClassificationsResponse resp = handler.handleRequest(null, createContext("list"));
        
        boolean hasE = false;
        for (Classification c : resp.list) {
        	System.out.println("found constant " + c);
        	if (c.name.equals("Greedy Algorithm")) { hasE = true; }
        }
        Assert.assertTrue("e Needs to exist in the constants table (from tutorial) for this test case to work.", hasE);
        Assert.assertEquals(200, resp.statusCode);
    }
}
