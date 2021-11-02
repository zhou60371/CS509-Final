package edu.wpi.cs.zzhou5.demo;

import com.amazonaws.services.lambda.runtime.Context;

public class LambdaTest {
	Context createContext(String apiCall) {
        TestContext ctx = new TestContext();
        ctx.setFunctionName(apiCall);
        return ctx;
    }
}
