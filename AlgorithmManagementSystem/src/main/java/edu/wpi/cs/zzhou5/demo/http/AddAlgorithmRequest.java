package edu.wpi.cs.zzhou5.demo.http;

public class AddAlgorithmRequest {
	public String arg1; //name
	public int arg2;  //classification
	public String arg3;//description
	

	public String getArg1() {
		return arg1;
	}
	 
	public void setArg1(String arg1) {
		this.arg1 = arg1;
	}
	public int getArg2() {
		return arg2;
	}
	 
	public void setArg2(int arg2) {
		this.arg2 = arg2;
	}
	public String getArg3() {
		return arg3;
	}
	 
	public void setArg3(String arg3) {
		this.arg3 = arg3;
	}
	
	public AddAlgorithmRequest() {}
	public AddAlgorithmRequest(String arg1,int arg2, String arg3) {
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.arg3 = arg3;
	}
}
