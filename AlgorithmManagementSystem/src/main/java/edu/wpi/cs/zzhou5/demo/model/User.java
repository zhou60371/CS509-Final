package edu.wpi.cs.zzhou5.demo.model;

public class User {
	public final String username;
	public final String password;
	public final int role;
	
	//register
	public User (String username, String password) {
		this.username = username;
		this.password = password;
		this.role = 1;
	}
	
	//login
	public User (String username, String password, int role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}
}
