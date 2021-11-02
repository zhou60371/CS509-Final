package edu.wpi.cs.zzhou5.demo.http;

public class UserRegistRequest {
	public String username;
	public String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public UserRegistRequest() {}
	public UserRegistRequest(String username,String password) {
		this.username = username;
		this.password = password;
	}
}
