package edu.wpi.cs.zzhou5.demo;

import java.io.ByteArrayInputStream;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;


import edu.wpi.cs.zzhou5.demo.db.UsersDAO;
import edu.wpi.cs.zzhou5.demo.http.UserRegistRequest;
import edu.wpi.cs.zzhou5.demo.http.UserRegistResponse;
import edu.wpi.cs.zzhou5.demo.model.User;

public class UserRegistHandler implements RequestHandler<UserRegistRequest,UserRegistResponse>{
	
	LambdaLogger logger;
	
	boolean regist(String username, String password) throws Exception { 
		if (logger != null) { logger.log("in createConstant"); }
		UsersDAO dao = new UsersDAO();
		
		// check if present
		User exist = dao.getUser(username);
		User user = new User (username, password);
		if (exist == null) {
			return dao.addUser(user);
		} else {
			return false;
		}
	}
	
	@Override 
	public UserRegistResponse handleRequest(UserRegistRequest req, Context context)  {
		logger = context.getLogger();
		logger.log(req.toString());

		UserRegistResponse response;
		try {
			if (regist(req.username, req.password)) {
				response = new UserRegistResponse(req.username);
			} else {
				response = new UserRegistResponse(req.username, 422);
			}
		}catch (Exception e) {
			response = new UserRegistResponse("Unable to regist user: " + req.username + "(" + e.getMessage() + ")", 400);
		}
			
		return response;
	}
}
