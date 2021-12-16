package edu.wpi.cs.zzhou5.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.zzhou5.demo.db.AlgorithmsDAO;
import edu.wpi.cs.zzhou5.demo.http.ReclassifyAlgorithmRequest;
import edu.wpi.cs.zzhou5.demo.http.ReclassifyAlgorithmResponse;

public class ReclassifyAlgorithmHandler implements RequestHandler<ReclassifyAlgorithmRequest,ReclassifyAlgorithmResponse>{

	public LambdaLogger logger = null;
	
	@Override
	public ReclassifyAlgorithmResponse handleRequest(ReclassifyAlgorithmRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to reclassify alogrithm");
		
		ReclassifyAlgorithmResponse response = null;
		logger.log(req.toString());
		
		AlgorithmsDAO dao = new AlgorithmsDAO();
		
		try {
			if(dao.reclassifyAlgorithm(req.algoId, req.classiId)) {
				response = new ReclassifyAlgorithmResponse(200);
			}else {
				response = new ReclassifyAlgorithmResponse(422,"Unable to reclassify algorithm");
			}
		}catch(Exception e){
			response = new ReclassifyAlgorithmResponse(403, "Unable to reclassify algorithm: (" + e.getMessage() + ")");
		}
		
		return response;
	}
}
