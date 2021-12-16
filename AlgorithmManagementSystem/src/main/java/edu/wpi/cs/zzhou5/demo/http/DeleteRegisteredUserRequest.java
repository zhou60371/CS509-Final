package edu.wpi.cs.zzhou5.demo.http;

public class DeleteRegisteredUserRequest {
	public String name;
	
	public void setName (String name) {this.name = name;}
	public String getName() {return name;}
	
	public DeleteRegisteredUserRequest() {}
	public DeleteRegisteredUserRequest(String name) {
		this.name = name;
	}
	
	public String toString() {
		return "Delete ("+ name +") user";
	}
}
