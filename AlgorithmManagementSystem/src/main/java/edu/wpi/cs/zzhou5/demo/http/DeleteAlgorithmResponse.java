package edu.wpi.cs.zzhou5.demo.http;

public class DeleteAlgorithmResponse {
	public int httpCode;
	public String error;
	
	public DeleteAlgorithmResponse() {}
	public DeleteAlgorithmResponse(int httpCode) {
		this.error = "";
		this.httpCode = httpCode;
	}
	
	public DeleteAlgorithmResponse(int httpCode, String error) {
		this.error = error;
		this.httpCode = httpCode;
	}
	
	public String toString() {
		if(httpCode == 200) {
			return "Delete algorithm successfully.";
		}else {
			return "Delete algorithm not successfully.";
		}
	}
}
