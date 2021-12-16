package edu.wpi.cs.zzhou5.demo;

import java.io.ByteArrayInputStream;
import java.util.Base64;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import edu.wpi.cs.zzhou5.demo.db.ProblemInstancesDAO;
import edu.wpi.cs.zzhou5.demo.http.UploadProblemInstanceRequest;
import edu.wpi.cs.zzhou5.demo.http.UploadProblemInstanceResponse;
import edu.wpi.cs.zzhou5.demo.model.ProblemInstance;

public class UploadProblemInstanceHandler implements RequestHandler<UploadProblemInstanceRequest,UploadProblemInstanceResponse>{
	LambdaLogger logger;
	
	private AmazonS3 s3 = null;
	
	public static final String REAL_BUCKET = "problemInstances/";
	
	boolean createProblemInstance(UploadProblemInstanceRequest req) throws Exception{
		if (logger != null) { logger.log("in upload Implementation"); }
		ProblemInstancesDAO dao = new ProblemInstancesDAO();
		
		String link = "https://nereidproject.s3.amazonaws.com/problemInstances/";
		String fileAdress = link + req.algo + "-" + req.caseType + "-" + req.user;
		int caseType = req.caseType;
		int algo = req.algo;
		String user = req.user;
		
		ProblemInstance PI = new ProblemInstance(fileAdress, caseType, algo, user);
		
		if(dao.uploadProblemInstance(PI)) {
//			if(logger != null){ logger.log("in createSysProblemInstance"); }
//			
//			if (s3 == null) {
//				logger.log("attach to S3 request");
//				s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
//				logger.log("attach to S3 succeed");
//			}
//			
//			String bucket = REAL_BUCKET;
//			String name = req.algo + "-" + req.caseType + "-" + req.user;
//			byte[] contents = Base64.getDecoder().decode(req.content);
//			ByteArrayInputStream bais = new ByteArrayInputStream(contents);
//			ObjectMetadata omd = new ObjectMetadata();
//			omd.setContentLength(contents.length);
//			
//			// makes the object publicly visible
//			PutObjectResult res = s3.putObject(new PutObjectRequest("nereidproject", bucket + name, bais, omd)
//					.withCannedAcl(CannedAccessControlList.PublicRead));
//			
//			// if we ever get here, then whole thing was stored
//			return true;
		}
//		else {
			return false;
//		}
	}
	
	@Override 
	public UploadProblemInstanceResponse handleRequest(UploadProblemInstanceRequest req, Context context)  {
		logger = context.getLogger();
		logger.log(req.toString());

		UploadProblemInstanceResponse response;
		try {
			if(createProblemInstance(req)) {
				response = new UploadProblemInstanceResponse("Sucessfull upload problem instance");
			}else {
				response = new UploadProblemInstanceResponse("Unable to upload problem Instance, you have already upload this type",422);
			}
		}catch(Exception e) {
			response = new UploadProblemInstanceResponse("Unable to upload problem instance: " + "(" + e.getMessage() + ")", 400);
		}	

		return response;
	}
}

