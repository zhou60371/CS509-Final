package edu.wpi.cs.zzhou5.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.zzhou5.demo.db.ProblemInstancesDAO;
import edu.wpi.cs.zzhou5.demo.http.DeleteProblemInstanceRequest;
import edu.wpi.cs.zzhou5.demo.http.DeleteProblemInstanceResponse;

public class DeleteProblemInstanceHandler implements RequestHandler<DeleteProblemInstanceRequest,DeleteProblemInstanceResponse>{

	public LambdaLogger logger = null;
	
	@Override
	public DeleteProblemInstanceResponse handleRequest(DeleteProblemInstanceRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to delete problem instance.");
		
		DeleteProblemInstanceResponse response = null;
		logger.log(req.toString());
		
		ProblemInstancesDAO dao = new ProblemInstancesDAO();
		
		try {
			if(dao.deleteProblemInstance(req.id)) {
				response = new DeleteProblemInstanceResponse(200);
			}else {
				response = new DeleteProblemInstanceResponse(422,"Unable to delete problem instace");
			}
		}catch(Exception e){
			response = new DeleteProblemInstanceResponse(403, "Unable to delete problem instance: (" + e.getMessage() + ")");
		}
		
		return response;
	}
}
