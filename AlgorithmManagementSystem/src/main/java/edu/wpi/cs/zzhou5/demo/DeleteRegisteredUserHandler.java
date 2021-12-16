package edu.wpi.cs.zzhou5.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.zzhou5.demo.db.UsersDAO;
import edu.wpi.cs.zzhou5.demo.http.DeleteRegisteredUserRequest;
import edu.wpi.cs.zzhou5.demo.http.DeleteRegisteredUserResponse;

public class DeleteRegisteredUserHandler implements RequestHandler<DeleteRegisteredUserRequest,DeleteRegisteredUserResponse>{

	public LambdaLogger logger = null;
	
	@Override
	public DeleteRegisteredUserResponse handleRequest(DeleteRegisteredUserRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to delete problem instance.");
		
		DeleteRegisteredUserResponse response = null;
		logger.log(req.toString());
		
		UsersDAO dao = new UsersDAO();
		
		try {
			if(dao.deleteUser(req.name)) {
				response = new DeleteRegisteredUserResponse(200);
			}
//			else {
//				response = new DeleteRegisteredUserResponse(422,"Unable to delete problem instace");
//			}
		}catch(Exception e){
			response = new DeleteRegisteredUserResponse(403, "Unable to delete problem instance: (" + e.getMessage() + ")");
		}
		
		return response;
	}
}
