package edu.wpi.cs.zzhou5.demo.http;

public class GetProblemInstancesRequest {
	public int id;
	
	public int getID(){
		return id;
	}
	public void setID(int id){
		this.id= id;
	}
	
	public GetProblemInstancesRequest() {}
	public GetProblemInstancesRequest(int id) {
		this.id = id;
	}
}
