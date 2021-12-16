package edu.wpi.cs.zzhou5.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import edu.wpi.cs.zzhou5.demo.GetClassificationsHierarchyHandler;
import edu.wpi.cs.zzhou5.demo.http.GetClassificationsHierarchyResponse;
import edu.wpi.cs.zzhou5.demo.model.Classification;

public class GetClassificationsHierarchyHandlerTset extends LambdaTest{
	@Test
    public void testGetList() throws IOException {
		GetClassificationsHierarchyHandler handler = new GetClassificationsHierarchyHandler();

		GetClassificationsHierarchyResponse resp = handler.handleRequest(null, createContext("hierarchy"));
        
        boolean hasE = false;
        if(resp.map.get("70") != null) {
        	hasE = true;
        }
//        for (Classification c : resp.map) {
//        	System.out.println("found constant " + c);
//        	if (c.name.equals("Greedy Algorithm")) { hasE = true; }
//        }
//        if
        Assert.assertTrue("e Needs to exist in the constants table (from tutorial) for this test case to work.", hasE);
        Assert.assertEquals(200, resp.statusCode);
//    }
        
        GetClassificationsHierarchyResponse getc = new GetClassificationsHierarchyResponse(200, "2");
        getc.toString();
	}
}