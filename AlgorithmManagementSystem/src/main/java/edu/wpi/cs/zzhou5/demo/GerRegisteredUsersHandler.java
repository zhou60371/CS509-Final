package edu.wpi.cs.zzhou5.demo;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.zzhou5.demo.db.UsersDAO;
import edu.wpi.cs.zzhou5.demo.http.GetRegisteredUsersResponse;
import edu.wpi.cs.zzhou5.demo.model.User;

public class GerRegisteredUsersHandler implements RequestHandler<Object,GetRegisteredUsersResponse> {
	public LambdaLogger logger;

	public ArrayList<User> getUsers() throws Exception {
		logger.log("in get users");
		UsersDAO dao = new UsersDAO();
		
		return dao.getUsers();
	}
	
	@Override
	public GetRegisteredUsersResponse handleRequest(Object input, Context context)  {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to get all algorithms bu specific id");

		GetRegisteredUsersResponse response;
		try {
			// get all user defined constants AND system-defined constants.
			// Note that user defined constants override system-defined constants.
			ArrayList<User> users = getUsers();
			response = new GetRegisteredUsersResponse(users, 200);
		} catch (Exception e) {
			response = new GetRegisteredUsersResponse(403, e.getMessage());
		}
		
		return response;
	}
}
