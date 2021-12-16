package edu.wpi.cs.zzhou5.demo;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.zzhou5.demo.db.ImplementationsDAO;
import edu.wpi.cs.zzhou5.demo.http.GetImplementationRequest;
import edu.wpi.cs.zzhou5.demo.http.GetImplementationResponse;
import edu.wpi.cs.zzhou5.demo.model.Implementation;

public class GetImplementationHandler implements RequestHandler<GetImplementationRequest,GetImplementationResponse> {
	public LambdaLogger logger;
	
	/** Load from RDS, if it exists
	 * 
	 * @throws Exception 
	 */
	Implementation getImple(int id) throws Exception {
		logger.log("in get implementation");
		ImplementationsDAO dao = new ImplementationsDAO();
		
		return dao.getImplementation(id);
	}
	
	// I am leaving in this S3 code so it can be a LAST RESORT if the constant is not in the database
	
	/**
	 * Retrieve all SYSTEM constants. This code is surprisingly dangerous since there could
	 * be an incredible number of objects in the bucket. Ignoring this for now.
	 */
	
	@Override
	public GetImplementationResponse handleRequest(GetImplementationRequest req, Context context)  {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to get all algorithms bu specific id");

		GetImplementationResponse response;
		try {
			// get all user defined constants AND system-defined constants.
			// Note that user defined constants override system-defined constants.
			Implementation imple = getImple(req.getID());
			response = new GetImplementationResponse(imple, 200);
		} catch (Exception e) {
			response = new GetImplementationResponse(403, e.getMessage());
		}
		
		return response;
	}
}