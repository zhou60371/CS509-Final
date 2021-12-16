package edu.wpi.cs.zzhou5.demo.http;

import java.util.ArrayList;
import java.util.Map;

import edu.wpi.cs.zzhou5.demo.model.ProblemInstance;

public class GetProblemInstancesResponse {
	public Map<String,ArrayList<ProblemInstance>> pis;
	public int httpCode;
	public String error;
	
	public GetProblemInstancesResponse(Map<String,ArrayList<ProblemInstance>> map, int httpCode ) {
		this.pis = map;
		this.httpCode = httpCode;
		this.error = "";
	}
	
	public GetProblemInstancesResponse(int httpCode,String error) {
		this.pis = null;
		this.httpCode = httpCode;
		this.error = error;
	}
	
	public String toString() {
		if(pis == null) {return "Empty Problem Instance";}
		return "It has problem instances";
	}
}
