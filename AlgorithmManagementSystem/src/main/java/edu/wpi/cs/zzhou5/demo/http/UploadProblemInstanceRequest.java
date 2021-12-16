package edu.wpi.cs.zzhou5.demo.http;

import edu.wpi.cs.zzhou5.demo.model.Implementation;

public class UploadProblemInstanceRequest {
	public int caseType;
	public String content;
	public int algo;
	public String user;
	
//	public int getCaseType() {
//		return caseType;
//	}
//	public void setCaseType(int caseType) {
//		this.caseType = caseType;
//	}
//	public String getContent() {
//		return content;
//	}
//	public void setContent(String content) {
//		this.content = content;
//	}
//	public int getAlgo() {
//		return algo;
//	}
//	public void setAlgo(int algo) {
//		this.algo = algo;
//	}
//	public String getUser() {
//		return user;
//	}
//	public void setUser(String user) {
//		this.user = user;
//	}
	public UploadProblemInstanceRequest() {}
	public UploadProblemInstanceRequest(int caseType, String content, int algo, String user) {
		// TODO Auto-generated constructor stub
		this.caseType = caseType;
		this.content = content;
		this.algo = algo;
		this.user = user;
	}
}
