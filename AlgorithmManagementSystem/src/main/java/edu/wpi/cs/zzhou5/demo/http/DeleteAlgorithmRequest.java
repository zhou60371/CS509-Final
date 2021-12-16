package edu.wpi.cs.zzhou5.demo.http;

public class DeleteAlgorithmRequest {
	public int id;
	
//	public void setId (int id) {this.id = id;}
//	public int getId() {return id;}
	
	public DeleteAlgorithmRequest(){}
	public DeleteAlgorithmRequest(int id){
		this.id =id;
	}

	public String toString() {
		return "Delete ("+ id +") algorithm";
	}
}
