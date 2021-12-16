package edu.wpi.cs.zzhou5.demo.http;

public class UploadImplementationRequest {
	public String arg1; //language
	public String arg2;//content
	public int arg3;//algorithm
	
	public String getArg1(){
		return this.arg1;
	}
	
	public void setArg1(String arg1) {
		this.arg1 = arg1;
	}
	
	public String getArg2(String content){
		return this.arg2;
	}
	
	public void setArg2(String arg2) {
		this.arg2 = arg2;
	}
	public int getArg3(){
		return this.arg3;
	}
	
	public void setArg3(int arg3) {
		this.arg3 = arg3;
	}
	
	public UploadImplementationRequest(){}
	public UploadImplementationRequest(String arg1, String arg2,int arg3){
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.arg3 = arg3;
	}
}
