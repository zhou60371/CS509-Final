package edu.wpi.cs.zzhou5.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.zzhou5.demo.db.ImplementationsDAO;
import edu.wpi.cs.zzhou5.demo.http.UploadImplementationRequest;
import edu.wpi.cs.zzhou5.demo.http.UploadImplementationResponse;
import edu.wpi.cs.zzhou5.demo.model.Implementation;

public class UploadImplementationHandler implements RequestHandler<UploadImplementationRequest,UploadImplementationResponse>{
	LambdaLogger logger;
	
	boolean uploadImple(String lan,String context, int algo) throws Exception { 
		if (logger != null) { logger.log("in upload Implementation"); }
		ImplementationsDAO dao = new ImplementationsDAO();
		
		Implementation imple = new Implementation (lan, context,algo);
		return dao.uploadImplementation(imple);
	}
	
	@Override 
	public UploadImplementationResponse handleRequest(UploadImplementationRequest req, Context context)  {
		logger = context.getLogger();
		logger.log(req.toString());

		UploadImplementationResponse response;
		try {
			if (uploadImple(req.arg1,req.arg2,req.arg3)) {
				response = new UploadImplementationResponse(req.arg1);
			} else {
				response = new UploadImplementationResponse(req.arg1, 422);
			}
		}catch (Exception e) {
			response = new UploadImplementationResponse("Unable to uploadImplementation: " + req.arg1 + "(" + e.getMessage() + ")", 400);
		}
			

		return response;
	}
}
