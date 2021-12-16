package edu.wpi.cs.zzhou5.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.zzhou5.demo.db.ClassificationsDAO;
import edu.wpi.cs.zzhou5.demo.http.DeleteClassificationRequest;
import edu.wpi.cs.zzhou5.demo.http.DeleteClassificationResponse;

public class DeleteClassificationHandler  implements RequestHandler<DeleteClassificationRequest,DeleteClassificationResponse>{

	public LambdaLogger logger = null;
	
	@Override
	public DeleteClassificationResponse handleRequest(DeleteClassificationRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to delete classification");
		
		DeleteClassificationResponse response = null;
		logger.log(req.toString());
		
		ClassificationsDAO dao = new ClassificationsDAO();
		
		try {
			if(dao.deleteClassification(req.id)) {
				response = new DeleteClassificationResponse(200);
			}
//			else {
//				response = new DeleteClassificationResponse(422,"Unable to delete benchmark");
//			}
		}catch(Exception e){
			response = new DeleteClassificationResponse(403, "Unable to delete benchmark: (" + e.getMessage() + ")");
		}
		
		return response;
	}
}