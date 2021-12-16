package edu.wpi.cs.zzhou5.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.zzhou5.demo.db.AlgorithmsDAO;
import edu.wpi.cs.zzhou5.demo.http.DeleteAlgorithmRequest;
import edu.wpi.cs.zzhou5.demo.http.DeleteAlgorithmResponse;

public class DeleteAlgorithmHandler implements RequestHandler<DeleteAlgorithmRequest,DeleteAlgorithmResponse>{

	public LambdaLogger logger = null;
	
	@Override
	public DeleteAlgorithmResponse handleRequest(DeleteAlgorithmRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to delete benchmark");
		
		DeleteAlgorithmResponse response = null;
		logger.log(req.toString());
		
		AlgorithmsDAO dao = new AlgorithmsDAO();
		
		try {
			if(dao.deleteAlgorithm(req.id)) {
				response = new DeleteAlgorithmResponse(200);
			}else {
				response = new DeleteAlgorithmResponse(422,"Unable to delete benchmark");
			}
		}catch(Exception e){
			response = new DeleteAlgorithmResponse(403, "Unable to delete benchmark: (" + e.getMessage() + ")");
		}
		
		return response;
	}
}
