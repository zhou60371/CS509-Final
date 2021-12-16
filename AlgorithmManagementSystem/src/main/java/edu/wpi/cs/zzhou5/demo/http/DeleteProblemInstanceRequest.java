package edu.wpi.cs.zzhou5.demo.http;

public class DeleteProblemInstanceRequest {
	public int id;
//	
//	public void setId (int id) {this.id = id;}
//	public int getId() {return id;}
	
	public DeleteProblemInstanceRequest() {}
	public DeleteProblemInstanceRequest(int id) {
		this.id = id;
	}
	
	public String toString() {
		return "Delete ("+ id +") problem instance";
	}
}
