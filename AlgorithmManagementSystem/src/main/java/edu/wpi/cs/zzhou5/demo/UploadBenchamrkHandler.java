package edu.wpi.cs.zzhou5.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.zzhou5.demo.db.BenchmarksDAO;
import edu.wpi.cs.zzhou5.demo.http.UploadBenchmarkRequest;
import edu.wpi.cs.zzhou5.demo.http.UploadBenchmarkResponse;
import edu.wpi.cs.zzhou5.demo.model.Benchmark;

public class UploadBenchamrkHandler implements RequestHandler<UploadBenchmarkRequest,UploadBenchmarkResponse>{
	LambdaLogger logger;
	
	boolean uploadBenchmark(UploadBenchmarkRequest req) throws Exception { 
		if (logger != null) { logger.log("in uplad benchmark"); }
		BenchmarksDAO dao = new BenchmarksDAO();
		
		Benchmark bk = new Benchmark(req.date,  req.runningTime, req.L1,req.L2,req.L3,req.threads,req.cores,req.cache,req.cpu,req.imple,req.problemInstance);
		return dao.uploadBenchmark(bk);
	}
	
	@Override 
	public UploadBenchmarkResponse handleRequest(UploadBenchmarkRequest req, Context context)  {
		logger = context.getLogger();
		logger.log(req.toString());

		UploadBenchmarkResponse response;
		try {
			if (uploadBenchmark(req)) {
				response = new UploadBenchmarkResponse("sucess");
			} else {
				response = new UploadBenchmarkResponse("fail", 422);
			}
		}catch (Exception e) {
			response = new UploadBenchmarkResponse("Unable to upload benchmark:  + (" + e.getMessage() + ")", 400);
		}
			

		return response;
	}
}

