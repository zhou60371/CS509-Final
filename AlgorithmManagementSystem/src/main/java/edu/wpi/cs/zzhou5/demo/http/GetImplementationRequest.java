package edu.wpi.cs.zzhou5.demo.http;

public class GetImplementationRequest {
	public int id;
	
	public int getID(){
		return id;
	}
	public void setID(int id){
		this.id= id;
	}
	
	public GetImplementationRequest() {}
	public GetImplementationRequest(int id) {
		this.id = id;
	}
}
