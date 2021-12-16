package edu.wpi.cs.zzhou5.demo.http;

public class GetAlgorithmsRequest {
	public String arg1; //request Classification ID
	
	public String getArg1() { return arg1; }
	public void setArg1(String arg1) { this.arg1 = arg1; }
	
	public GetAlgorithmsRequest() {}
	public GetAlgorithmsRequest(String arg1) {
		this.arg1 = arg1;
	}
	
	public String toString() {
		return "Get classification (" + arg1 + ") alsogrithms";
	}
}
