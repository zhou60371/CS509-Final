package edu.wpi.cs.zzhou5.demo.http;

public class UploadBenchmarkRequest {
	public String cpu;
	public String date;
	public String threads;
	public String cores;
	public String L1;
	public String L2;
	public String L3;
	public String cache;
	public String runningTime;
	public int imple;
	public int problemInstance;
	public int getImple() {
		return imple;
	}
	public void setImple(int imple) {
		this.imple = imple;
	}
	public int getProblemInstance() {
		return problemInstance;
	}
	public void setProblemInstance(int problemInstance) {
		this.problemInstance = problemInstance;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getThread() {
		return threads;
	}
	public void setThread(String threads) {
		this.threads = threads;
	}
	public String getCores() {
		return cores;
	}
	public void setCores(String cores) {
		this.cores = cores;
	}
	public String getL1() {
		return L1;
	}
	public void setL1(String l1) {
		L1 = l1;
	}
	public String getL2() {
		return L2;
	}
	public void setL2(String l2) {
		L2 = l2;
	}
	public String getL3() {
		return L3;
	}
	public void setL3(String l3) {
		L3 = l3;
	}
	public String getCache() {
		return cache;
	}
	public void setCache(String cache) {
		this.cache = cache;
	}
	public String getRunningTime() {
		return runningTime;
	}
	public void setRunningTime(String runningTime) {
		this.runningTime = runningTime;
	}
	
	public UploadBenchmarkRequest() {}
	public UploadBenchmarkRequest(String cpu,String date, String threads, String cores, String l1, String l2, String l3,
			String cache, String runningTime, int imple, int problemInstance) {
		this.date = date;
		this.cpu = cpu;
		this.threads = threads;
		this.cores = cores;
		this.L1 = l1;
		this.L2 = l2;
		this.L3 = l3;
		this.cache = cache;
		this.runningTime = runningTime;
		this.imple = imple;
		this.problemInstance = problemInstance;
	}
}
