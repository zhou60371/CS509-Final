package edu.wpi.cs.zzhou5.demo.http;

public class UserRegistResponse {
	public final String response;
	public final int httpCode;
	
	public UserRegistResponse (String s, int code) {
		this.response = s;
		this.httpCode = code;
	}
	
	// 200 means success
	public UserRegistResponse (String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	
	public String toString() {
		return "Response(" + response + ")";
	}
}
