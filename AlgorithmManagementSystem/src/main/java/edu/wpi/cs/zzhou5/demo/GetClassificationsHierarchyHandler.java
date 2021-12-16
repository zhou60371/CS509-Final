package edu.wpi.cs.zzhou5.demo;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.zzhou5.demo.db.ClassificationsDAO;
import edu.wpi.cs.zzhou5.demo.http.GetClassificationsHierarchyResponse;
import edu.wpi.cs.zzhou5.demo.model.Classification;

public class GetClassificationsHierarchyHandler implements RequestHandler<Object,GetClassificationsHierarchyResponse> {
	public LambdaLogger logger;

	Map<String,Classification> getClassificationsHierarchy() throws Exception {
		logger.log("in Get Classifications Hierarchy");
		ClassificationsDAO dao = new ClassificationsDAO();
		
		return dao.getClassificationsHierarchy();
	}
	
	// I am leaving in this S3 code so it can be a LAST RESORT if the constant is not in the database
	
	/**
	 * Retrieve all SYSTEM constants. This code is surprisingly dangerous since there could
	 * be an incredible number of objects in the bucket. Ignoring this for now.
	 */
	
	@Override
	public GetClassificationsHierarchyResponse handleRequest(Object input, Context context)  {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all constants");

		GetClassificationsHierarchyResponse response;
		try {
			// get all user defined constants AND system-defined constants.
			// Note that user defined constants override system-defined constants.
			Map<String,Classification> map = getClassificationsHierarchy();
			response = new GetClassificationsHierarchyResponse(map, 200);
		} catch (Exception e) {
			response = new GetClassificationsHierarchyResponse(403, e.getMessage());
		}
		
		return response;
	}
}
