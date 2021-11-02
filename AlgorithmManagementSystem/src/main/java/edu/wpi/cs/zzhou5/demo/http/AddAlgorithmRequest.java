package edu.wpi.cs.zzhou5.demo.http;

public class AddAlgorithmRequest {
	public String arg1; //name
	public String arg2;//description
	public int arg3;  //stars
	public int arg4; //classification
	
	
	public String getArg1() {
		return arg1;
	}
	 
	public void setArg1(String arg1) {
		this.arg1 = arg1;
	}
	public String getArg2() {
		return arg2;
	}
	 
	public void setArg2(String arg2) {
		this.arg2 = arg2;
	}
	public int getArg3() {
		return arg3;
	}
	 
	public void setArg3(int arg3) {
		this.arg3 = arg3;
	}
	public int getArg4() {
		return arg4;
	}
	 
	public void setArg4(int arg4) {
		this.arg4 = arg4;
	}
	
	public AddAlgorithmRequest() {}
	public AddAlgorithmRequest(String arg1,String arg2, int arg3, int arg4) {
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.arg3 = arg3;
		this.arg4 = arg4;
	}
}
