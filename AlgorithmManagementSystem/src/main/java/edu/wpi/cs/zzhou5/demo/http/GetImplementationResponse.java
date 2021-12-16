package edu.wpi.cs.zzhou5.demo.http;

import edu.wpi.cs.zzhou5.demo.model.Implementation;

public class GetImplementationResponse {
	public final Implementation imple;
	public final int httpCode;
	public final String error;
	
	public GetImplementationResponse(Implementation imple, int httpCode) {
		this.imple = imple;
		this.httpCode = httpCode;
		this.error = "";
	}
	
	public GetImplementationResponse(int httpCode, String errorMessage) {
		this.imple = null;
		this.httpCode = httpCode;
		this.error = errorMessage;
	}
	
	public String toString() {
		if(imple == null) {return "Empty implementation";}
		return imple.language +" implementaion";
	}
}
