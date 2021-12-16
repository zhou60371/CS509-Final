package edu.wpi.cs.zzhou5.demo.http;

public class DeleteClassificationResponse {
	public int httpCode;
	public String error;
	
	public DeleteClassificationResponse() {}
	public DeleteClassificationResponse(int httpCode) {
		this.error = "";
		this.httpCode = httpCode;
	}
	public DeleteClassificationResponse(int httpCode, String error) {
		this.error = error;
		this.httpCode = httpCode;
	}
	public String toString() {
		if(httpCode == 200) {
			return "Delete classification successfully.";
		}else {
			return "Delete classification not successfully.";
		}
	}
}
