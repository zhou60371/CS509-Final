package edu.wpi.cs.zzhou5.demo.http;

public class AddClassificationRequest {
	public String arg1;//name
	public int arg2;//father ID
	public int arg3;//level
	
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
	public int getArg3() {
		return arg3;
	}
	 
	public void setArg3(int arg3) {
		this.arg3 = arg3;
	}
	
	public AddClassificationRequest() {}
	public AddClassificationRequest(String arg1,int arg2,int arg3) {
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.arg3 = arg3;
	}
}
