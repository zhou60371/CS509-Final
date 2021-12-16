package edu.wpi.cs.zzhou5.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.zzhou5.demo.db.ProblemInstancesDAO;
import edu.wpi.cs.zzhou5.demo.http.GetProblemInstancesRequest;
import edu.wpi.cs.zzhou5.demo.http.GetProblemInstancesResponse;
import edu.wpi.cs.zzhou5.demo.model.ProblemInstance;

public class GetProblemInstancesHandler implements RequestHandler<GetProblemInstancesRequest,GetProblemInstancesResponse> {
	public LambdaLogger logger;
	
	/** Load from RDS, if it exists
	 * 
	 * @throws Exception 
	 */
	Map<String, ArrayList<ProblemInstance>> getPis(int id) throws Exception {
		logger.log("in get implementation");
		ProblemInstancesDAO dao = new ProblemInstancesDAO();
		
		return dao.getProblemInstance(id);
	}
	
	// I am leaving in this S3 code so it can be a LAST RESORT if the constant is not in the database
	
	/**
	 * Retrieve all SYSTEM constants. This code is surprisingly dangerous since there could
	 * be an incredible number of objects in the bucket. Ignoring this for now.
	 */
	
	@Override
	public GetProblemInstancesResponse handleRequest(GetProblemInstancesRequest req, Context context)  {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to get all algorithms bu specific id");

		GetProblemInstancesResponse response;
		try {
			// get all user defined constants AND system-defined constants.
			// Note that user defined constants override system-defined constants.
			Map<String, ArrayList<ProblemInstance>> pis = getPis(req.getID());
			response = new GetProblemInstancesResponse(pis, 200);
		} catch (Exception e) {
			response = new GetProblemInstancesResponse(403, e.getMessage());
		}
		
		return response;
	}
}
