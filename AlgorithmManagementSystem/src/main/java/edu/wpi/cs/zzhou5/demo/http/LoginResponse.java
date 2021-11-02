package edu.wpi.cs.zzhou5.demo.http;

public class LoginResponse {
	public String result;
	public int statusCode;
	public String error;
	public int role;
	
	//success
	public LoginResponse (boolean value, int statusCode,int role) {
		this.result = "" + value; // doesn't matter since error
		this.statusCode = statusCode;
		this.error = "";
		this.role = role;
	}
	
	//fail
	public LoginResponse (int statusCode, String errorMessage) {
		this.result = "false"; // doesn't matter since error
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (statusCode / 100 == 2) {  // too cute?
			return "Result(" + result + ")";
		} else {
			return "ErrorResult(" + statusCode + ", err=" + error + ")";
		}
	}
}
