package edu.wpi.cs.zzhou5.demo.http;

public class DeleteImplementationRequest {
	public int id;
	
	public void setId (int id) {this.id = id;}
	public int getId() {return id;}
	
	public DeleteImplementationRequest() {}
	public DeleteImplementationRequest(int id) {
		this.id = id;
	}
	
	public String toString() {
		return "Delete ("+ id +") implementation";
	}
}
