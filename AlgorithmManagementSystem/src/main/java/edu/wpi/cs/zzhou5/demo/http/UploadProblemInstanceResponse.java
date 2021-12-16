package edu.wpi.cs.zzhou5.demo.http;

public class UploadProblemInstanceResponse {
	public String response;
	public int httpCode;
	
	public UploadProblemInstanceResponse(String s, int code) {
		this.response = s;
		this.httpCode = code;
	}
	
	public UploadProblemInstanceResponse(String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}
}
