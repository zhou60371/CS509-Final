package edu.wpi.cs.zzhou5.demo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import edu.wpi.cs.zzhou5.demo.db.ClassificationsDAO;
import edu.wpi.cs.zzhou5.demo.http.AllClassificationsResponse;
import edu.wpi.cs.zzhou5.demo.model.Classification;

public class ListAllClassificationsHandler implements RequestHandler<Object,AllClassificationsResponse> {
	public LambdaLogger logger;

	// Note: this works, but it would be better to move this to environment/configuration mechanisms
	// which you don't have to do for this project.
	
	/** Load from RDS, if it exists
	 * 
	 * @throws Exception 
	 */
	List<Classification> getClassifications() throws Exception {
		logger.log("in getConstants");
		ClassificationsDAO dao = new ClassificationsDAO();
		
		return dao.getAllClassifications();
	}
	
	// I am leaving in this S3 code so it can be a LAST RESORT if the constant is not in the database
	
	/**
	 * Retrieve all SYSTEM constants. This code is surprisingly dangerous since there could
	 * be an incredible number of objects in the bucket. Ignoring this for now.
	 */
	
	@Override
	public AllClassificationsResponse handleRequest(Object input, Context context)  {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all constants");

		AllClassificationsResponse response;
		try {
			// get all user defined constants AND system-defined constants.
			// Note that user defined constants override system-defined constants.
			List<Classification> list = getClassifications();
			response = new AllClassificationsResponse(list, 200);
		} catch (Exception e) {
			response = new AllClassificationsResponse(403, e.getMessage());
		}
		
		return response;
	}
}
