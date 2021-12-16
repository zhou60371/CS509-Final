package edu.wpi.cs.zzhou5.demo.http;

public class DeleteBenchmarkResponse {
	public int httpCode;
	public String error;
	
	public DeleteBenchmarkResponse() {}
	public DeleteBenchmarkResponse(int httpCode) {
		this.error = "";
		this.httpCode = httpCode;
	}
	public DeleteBenchmarkResponse(int httpCode, String error) {
		this.httpCode = httpCode;
		this.error = error;
	}
	
	public String toString() {
		if(httpCode == 200) {
			return "Delete benchmark successfully.";
		}else {
			return "Delete benchmark not successfully.";
		}
	}
}
