package edu.wpi.cs.zzhou5.demo.http;

public class DeleteImplementationResponse {
	public int httpCode;
	public String error;
	
	public DeleteImplementationResponse() {}
	public DeleteImplementationResponse(int httpCode) {
		this.httpCode = httpCode;
		this.error = "";
	}
	
	public DeleteImplementationResponse(int httpCode,String error) {
		this.httpCode = httpCode;
		this.error = error;
	}
	
	public String toString() {
		if(httpCode == 200) {
			return "Delete Implementation successfully.";
		}else {
			return "Delete Implementation not successfully.";
		}
	}
}
