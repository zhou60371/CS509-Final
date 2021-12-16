package edu.wpi.cs.zzhou5.demo.http;

public class DeleteClassificationRequest {
	public int id;
	
	public void setId (int id) {this.id = id;}
	public int getId() {return id;}
	
	public DeleteClassificationRequest() {}
	public DeleteClassificationRequest(int id) {
		this.id = id;
	}
	
	public String toString() {
		return "Delete ("+ id +") benchmark";
	}
}
