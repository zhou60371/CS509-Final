package edu.wpi.cs.zzhou5.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.zzhou5.demo.db.UsersDAO;
import edu.wpi.cs.zzhou5.demo.http.UploadActivityRequest;
import edu.wpi.cs.zzhou5.demo.http.UploadActivityResponse;

public class UploadActivityHandler implements RequestHandler<UploadActivityRequest,UploadActivityResponse>{
	LambdaLogger logger;
	
	boolean uploadActivity(UploadActivityRequest req) throws Exception { 
		if (logger != null) { logger.log("in uplad activity"); }
		UsersDAO dao = new UsersDAO();
		
		return dao.uploadActivity(req.name,req.activity,req.time);
	}
	
	@Override 
	public UploadActivityResponse handleRequest(UploadActivityRequest req, Context context)  {
		logger = context.getLogger();
		logger.log(req.toString());

		UploadActivityResponse response;
		try {
			if (uploadActivity(req)) {
				response = new UploadActivityResponse("sucess");
			} else {
				response = new UploadActivityResponse("fail", 422);
			}
		}catch (Exception e) {
			response = new UploadActivityResponse("Unable to upload activity:  + (" + e.getMessage() + ")", 400);
		}
			

		return response;
	}
}
