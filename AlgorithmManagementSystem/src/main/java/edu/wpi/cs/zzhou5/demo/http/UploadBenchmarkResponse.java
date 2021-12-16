package edu.wpi.cs.zzhou5.demo.http;

public class UploadBenchmarkResponse {
	public int httpCode;
	public String response;
	
	public UploadBenchmarkResponse(String s) {
		this.response = s;
		this.httpCode = 200;	
	}
	
	public UploadBenchmarkResponse(String s,int code) {
		this.response =s;
		this.httpCode =code;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}
}
