package edu.wpi.cs.zzhou5.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.zzhou5.demo.db.ClassificationsDAO;
import edu.wpi.cs.zzhou5.demo.http.MergeSiblingClassificationRequest;
import edu.wpi.cs.zzhou5.demo.http.MergeSiblingClassificationResponse;

public class MergeSiblingClassificationHandler implements RequestHandler<MergeSiblingClassificationRequest,MergeSiblingClassificationResponse>{
	
	public LambdaLogger logger = null;
	
	@Override
	public MergeSiblingClassificationResponse handleRequest(MergeSiblingClassificationRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to merge sibiling classification");
		
		MergeSiblingClassificationResponse response = null;
		logger.log(req.toString());
		
		ClassificationsDAO dao = new ClassificationsDAO();
		
		try {
			if(dao.mergeSiblingClassification(req.class1, req.class2)) {
				response = new MergeSiblingClassificationResponse(200);
			}else {
				response = new MergeSiblingClassificationResponse(422,"Unable to merge sibiling classification");
			}
		}catch(Exception e){
			response = new MergeSiblingClassificationResponse(403, "Unable to sibling classification: (" + e.getMessage() + ")");
		}
		
		return response;
	}
}
