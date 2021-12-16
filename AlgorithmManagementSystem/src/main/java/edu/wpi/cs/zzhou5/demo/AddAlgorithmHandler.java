package edu.wpi.cs.zzhou5.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.zzhou5.demo.db.AlgorithmsDAO;
import edu.wpi.cs.zzhou5.demo.http.AddAlgorithmRequest;
import edu.wpi.cs.zzhou5.demo.http.AddAlgorithmResponse;
import edu.wpi.cs.zzhou5.demo.model.Algorithm;

public class AddAlgorithmHandler implements RequestHandler<AddAlgorithmRequest,AddAlgorithmResponse>{
	LambdaLogger logger;
	
	boolean createAlgorithm(String name, int classification,String description) throws Exception { 
		if (logger != null) { logger.log("in add Classification"); }
		AlgorithmsDAO dao = new AlgorithmsDAO();
		
		// check if present
		Algorithm exist = dao.getAlgorithm(name);
		Algorithm algo = new Algorithm (name,classification,description);
		if (exist == null) {
			return dao.addAlgorithm(algo);
		} else {
			return false;
		}
	}
	
	@Override 
	public AddAlgorithmResponse handleRequest(AddAlgorithmRequest req, Context context)  {
		logger = context.getLogger();
		logger.log(req.toString());

		AddAlgorithmResponse response;
		try {
			if (createAlgorithm(req.arg1,req.arg2,req.arg3)) {
				response = new AddAlgorithmResponse(req.arg1);
			} 
			else {
				response = new AddAlgorithmResponse(req.arg1, 422);
			}
		}catch (Exception e) {
			response = new AddAlgorithmResponse("Unable to create classification: " + req.arg1 + "(" + e.getMessage() + ")", 400);
		}
			

		return response;
	}
}
