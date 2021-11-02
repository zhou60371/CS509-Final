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


import edu.wpi.cs.zzhou5.demo.db.ClassificationsDAO;
import edu.wpi.cs.zzhou5.demo.http.AddClassificationRequest;
import edu.wpi.cs.zzhou5.demo.http.AddClassificationResponse;
import edu.wpi.cs.zzhou5.demo.model.Classification;

public class AddClassificationHandler implements RequestHandler<AddClassificationRequest,AddClassificationResponse>{
	LambdaLogger logger;
	
	boolean createClassification(String name,int[] childrenID, int level) throws Exception { 
		if (logger != null) { logger.log("in add Classification"); }
		ClassificationsDAO dao = new ClassificationsDAO();
		
		// check if present
		Classification exist = dao.getClassification(name);
		Classification user = new Classification (name,childrenID,level);
		if (exist == null) {
			return dao.addClassification(user);
		} else {
			return false;
		}
	}
	
	@Override 
	public AddClassificationResponse handleRequest(AddClassificationRequest req, Context context)  {
		logger = context.getLogger();
		logger.log(req.toString());

		AddClassificationResponse response;
		try {
			if (createClassification(req.arg1,req.arg2,req.arg3)) {
				response = new AddClassificationResponse(req.arg1);
			} else {
				response = new AddClassificationResponse(req.arg1, 422);
			}
		}catch (Exception e) {
			response = new AddClassificationResponse("Unable to create classification: " + req.arg1 + "(" + e.getMessage() + ")", 400);
		}
			

		return response;
	}
}
