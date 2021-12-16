package edu.wpi.cs.zzhou5.demo.http;

public class UploadActivityResponse {
	public String response;
	public int httpCode;
	
	public UploadActivityResponse (String s, int code) {
		this.response = s;
		this.httpCode = code;
	}
	
	// 200 means success
	public UploadActivityResponse (String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	
	public String toString() {
		return "Response(" + response + ")";
	}
}
