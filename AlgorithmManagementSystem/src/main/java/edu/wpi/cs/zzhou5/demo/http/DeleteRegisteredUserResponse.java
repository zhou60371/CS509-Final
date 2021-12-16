package edu.wpi.cs.zzhou5.demo.http;

public class DeleteRegisteredUserResponse {
	public int httpCode;
	public String error;
	
	public DeleteRegisteredUserResponse() {}
	public DeleteRegisteredUserResponse(int httpCode) {
		this.httpCode = httpCode;
		this.error = "";
	}
	
	public DeleteRegisteredUserResponse(int httpCode,String error) {
		this.httpCode = httpCode;
		this.error = error;
	}
	
	public String toString() {
		if(httpCode == 200) {
			return "Delete user successfully.";
		}else {
			return "Delete user not successfully.";
		}
	}
}
