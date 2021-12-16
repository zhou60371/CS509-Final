package edu.wpi.cs.zzhou5.demo;

import java.util.Scanner;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import edu.wpi.cs.zzhou5.demo.db.UsersDAO;
import edu.wpi.cs.zzhou5.demo.http.LoginRequest;
import edu.wpi.cs.zzhou5.demo.http.LoginResponse;
import edu.wpi.cs.zzhou5.demo.model.User;


public class UsersLoginHandler implements RequestHandler<LoginRequest,LoginResponse> {
	
	LambdaLogger logger;
	
	public User loadUser(String arg) throws Exception {
		try {
			User user = loadValueFromRDS(arg);
			return user;
		}catch(Exception e) {
			return null;
		}
	}
	
	User loadValueFromRDS(String arg) throws Exception {
		if (logger != null) { logger.log("in loadValue"); }
		UsersDAO dao = new UsersDAO();
		if (logger != null) { logger.log("retrieved DAO"); }
		User user = dao.getUser(arg);
		if (logger != null) { logger.log("retrieved User"); }
		return user;
	}
	
	@Override
	public LoginResponse handleRequest(LoginRequest req, Context context){
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		logger.log(req.toString());

		boolean fail = false;
		User user = null;
		String failMessage = "";
		String passwordRDS = "";
		try {
			user = loadUser(req.getArg1());
			passwordRDS = user.password;
			System.out.println("passwordRDS"+passwordRDS);
		} catch (Exception e) {
			failMessage = req.getArg1() + " is an invalid user.";
			fail = true;
		}

		String passwordBrower = "";
		try {
			passwordBrower= req.getArg2();
			System.out.println("passwordBrower"+passwordBrower);
			if(passwordRDS.equals(passwordBrower)) {
				fail = false;
			}else {
				fail = true;
				failMessage = "Wrong password.";
			}
		} catch (Exception e) {
			failMessage = "This is an invalid user.";
			fail = true;
		}

		// compute proper response and return. Note that the status code is internal to the HTTP response
		// and has to be processed specifically by the client code.
		LoginResponse response;
		if (fail) {
			response = new LoginResponse(400, failMessage);
		} else {
			response = new LoginResponse(true, 200, user.role);  // success
		}

		return response; 
	}
        

}
