package edu.wpi.cs.zzhou5.demo.http;

import java.util.ArrayList;

import edu.wpi.cs.zzhou5.demo.model.User;

public class GetRegisteredUsersResponse {
	public ArrayList<User> users;
	public int httpCode;
	public String error;
	
	public GetRegisteredUsersResponse() {}
	public GetRegisteredUsersResponse(ArrayList<User> users, int code) {
		this.users = users;
		this.httpCode = code;
		this.error = "";
	}
	
	public GetRegisteredUsersResponse(int code, String errorMessage) {
		this.httpCode= code;
		this.error = errorMessage;
	}
}
