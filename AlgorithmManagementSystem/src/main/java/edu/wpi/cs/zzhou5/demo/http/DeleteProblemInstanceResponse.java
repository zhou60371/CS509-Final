package edu.wpi.cs.zzhou5.demo.http;

public class DeleteProblemInstanceResponse {
	public int httpCode;
	public String error;
	
	public DeleteProblemInstanceResponse() {}
	public DeleteProblemInstanceResponse(int httpCode) {
		this.httpCode = httpCode;
		this.error = "";
	}
	
	public DeleteProblemInstanceResponse(int httpCode,String error) {
		this.httpCode = httpCode;
		this.error = error;
	}
	
	public String toString() {
		if(httpCode == 200) {
			return "Delete problem instance successfully.";
		}else {
			return "Delete problem instance not successfully.";
		}
	}
}
