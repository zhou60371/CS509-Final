package edu.wpi.cs.zzhou5.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.zzhou5.demo.db.ImplementationsDAO;
import edu.wpi.cs.zzhou5.demo.http.DeleteImplementationRequest;
import edu.wpi.cs.zzhou5.demo.http.DeleteImplementationResponse;

public class DeleteImplementationHandler implements RequestHandler<DeleteImplementationRequest,DeleteImplementationResponse>{

	public LambdaLogger logger = null;
	
	@Override
	public DeleteImplementationResponse handleRequest(DeleteImplementationRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to delete implementation");
		
		DeleteImplementationResponse response = null;
		logger.log(req.toString());
		
		ImplementationsDAO dao = new ImplementationsDAO();
		
		try {
			if(dao.deleteImplementation(req.id)) {
				response = new DeleteImplementationResponse(200);
			}else {
				response = new DeleteImplementationResponse(422,"Unable to delete implementation");
			}
		}catch(Exception e){
			response = new DeleteImplementationResponse(403, "Unable to delete implementation: (" + e.getMessage() + ")");
		}
		
		return response;
	}
}