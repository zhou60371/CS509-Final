package edu.wpi.cs.zzhou5.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.zzhou5.demo.db.BenchmarksDAO;
import edu.wpi.cs.zzhou5.demo.http.DeleteBenchmarkRequest;
import edu.wpi.cs.zzhou5.demo.http.DeleteBenchmarkResponse;
import edu.wpi.cs.zzhou5.demo.model.Benchmark;

public class DeleteBenchmarkHandler implements RequestHandler<DeleteBenchmarkRequest,DeleteBenchmarkResponse>{

		public LambdaLogger logger = null;
		
		@Override
		public DeleteBenchmarkResponse handleRequest(DeleteBenchmarkRequest req, Context context) {
			logger = context.getLogger();
			logger.log("Loading Java Lambda handler to delete benchmark");
			
			DeleteBenchmarkResponse response = null;
			logger.log(req.toString());
			
			BenchmarksDAO dao = new BenchmarksDAO();
			
			try {
				if(dao.deleteBenchamrk(req.id)) {
					response = new DeleteBenchmarkResponse(200);
				}else {
					response = new DeleteBenchmarkResponse(422,"Unable to delete benchmark");
				}
			}catch(Exception e){
				response = new DeleteBenchmarkResponse(403, "Unable to delete benchmark: (" + e.getMessage() + ")");
			}
			
			return response;
		}
}
