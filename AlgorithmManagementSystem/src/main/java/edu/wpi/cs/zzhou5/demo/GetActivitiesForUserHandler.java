package edu.wpi.cs.zzhou5.demo;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.zzhou5.demo.db.UsersDAO;
import edu.wpi.cs.zzhou5.demo.http.GetActivitiesForUserRequest;
import edu.wpi.cs.zzhou5.demo.http.GetActivitiesForUserResponse;
import edu.wpi.cs.zzhou5.demo.model.Activity;

public class GetActivitiesForUserHandler implements RequestHandler<GetActivitiesForUserRequest,GetActivitiesForUserResponse> {
	public LambdaLogger logger;

	public ArrayList<Activity> getActivitiesForUser(String name) throws Exception {
		logger.log("in get activities for user");
		UsersDAO dao = new UsersDAO();
		
		return dao.getActivityForUser(name);
	}
	
	@Override
	public GetActivitiesForUserResponse handleRequest(GetActivitiesForUserRequest req, Context context)  {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to get all algorithms bu specific id");

		GetActivitiesForUserResponse response;
		try {
			ArrayList<Activity> activities = getActivitiesForUser(req.name);
			response = new GetActivitiesForUserResponse(200,activities);
		} catch (Exception e) {
			response = new GetActivitiesForUserResponse(403, e.getMessage());
		}
		
		return response;
	}
}
