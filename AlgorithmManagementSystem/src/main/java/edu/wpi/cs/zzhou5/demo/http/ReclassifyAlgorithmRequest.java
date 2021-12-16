package edu.wpi.cs.zzhou5.demo.http;

public class ReclassifyAlgorithmRequest {
	public int algoId;
	public int classiId;
	
	public ReclassifyAlgorithmRequest() {}
	public ReclassifyAlgorithmRequest(int algo, int classi) {
		this.algoId = algo;
		this.classiId = classi;
	}
	
	public String toString() {
		return "Reclassify ("+ algoId +") algorithm to " + classiId + "classification";
	}
	
}
