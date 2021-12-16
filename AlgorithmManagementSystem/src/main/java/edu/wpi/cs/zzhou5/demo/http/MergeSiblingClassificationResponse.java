package edu.wpi.cs.zzhou5.demo.http;

public class MergeSiblingClassificationResponse {
	public int httpCode;
	public String error;
	
	public MergeSiblingClassificationResponse() {}
	public MergeSiblingClassificationResponse(int httpCode) {
		this.error = "";
		this.httpCode = httpCode;
	}
	
	public MergeSiblingClassificationResponse(int httpCode,String error) {
		this.error = error;
		this.httpCode = httpCode;
	}
	
	public String toString() {
		if(httpCode == 200) {
			return "Merge classification successfully.";
		}else {
			return "Merge classification not successfully.";
		}
	}
}
