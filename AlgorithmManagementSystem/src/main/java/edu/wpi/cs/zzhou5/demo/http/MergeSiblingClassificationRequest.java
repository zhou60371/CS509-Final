package edu.wpi.cs.zzhou5.demo.http;

public class MergeSiblingClassificationRequest {
	public int class1;
	public int class2;
	
	public MergeSiblingClassificationRequest() {}
	public MergeSiblingClassificationRequest(int class1, int class2) {
		this.class1 = class1;
		this.class2 = class2;
	}
	
	public String toSting() {
		return "merge ("+ class2 +") classification into " + class2 + "classification";
	}
}
