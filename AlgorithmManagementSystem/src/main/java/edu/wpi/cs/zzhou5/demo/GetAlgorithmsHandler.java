package edu.wpi.cs.zzhou5.demo;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.zzhou5.demo.db.AlgorithmsDAO;
import edu.wpi.cs.zzhou5.demo.http.GetAlgorithmsRequest;
import edu.wpi.cs.zzhou5.demo.http.GetAlgorithmsResponse;
import edu.wpi.cs.zzhou5.demo.model.Algorithm;

public class GetAlgorithmsHandler implements RequestHandler<GetAlgorithmsRequest,GetAlgorithmsResponse> {
	public LambdaLogger logger;

	// Note: this works, but it would be better to move this to environment/configuration mechanisms
	// which you don't have to do for this project.
	
	/** Load from RDS, if it exists
	 * 
	 * @throws Exception 
	 */
	List<Algorithm> getAlgorithms(String id) throws Exception {
		logger.log("in getAlgorithms");
		AlgorithmsDAO dao = new AlgorithmsDAO();
		
		return dao.getAlgorithms(id);
	}
	
	// I am leaving in this S3 code so it can be a LAST RESORT if the constant is not in the database
	
	/**
	 * Retrieve all SYSTEM constants. This code is surprisingly dangerous since there could
	 * be an incredible number of objects in the bucket. Ignoring this for now.
	 */
	
	@Override
	public GetAlgorithmsResponse handleRequest(GetAlgorithmsRequest req, Context context)  {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to get all algorithms bu specific id");

		GetAlgorithmsResponse response;
		try {
			// get all user defined constants AND system-defined constants.
			// Note that user defined constants override system-defined constants.
			List<Algorithm> list = getAlgorithms(req.getArg1());
			response = new GetAlgorithmsResponse(list, 200);
		} catch (Exception e) {
			response = new GetAlgorithmsResponse(403, e.getMessage());
		}
		
		return response;
	}
}
