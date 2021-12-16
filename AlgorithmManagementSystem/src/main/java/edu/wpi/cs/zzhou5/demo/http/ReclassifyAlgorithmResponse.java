package edu.wpi.cs.zzhou5.demo.http;

public class ReclassifyAlgorithmResponse {
	public int httpCode;
	public String error;
	
	public ReclassifyAlgorithmResponse() {}
	public ReclassifyAlgorithmResponse(int httpCode) {
		this.error = "";
		this.httpCode = httpCode;
	}
	
	public ReclassifyAlgorithmResponse(int httpCode,String error) {
		this.error = error;
		this.httpCode = httpCode;
	}
	
	public String toString() {
		if(httpCode == 200) {
			return "Reclassify algorithm successfully.";
		}else {
			return "Reclassify algorithm not successfully.";
		}
	}
}
