package edu.wpi.cs.zzhou5.demo.model;

public class Benchmark {
	
	public int id;
	public String date;
	public String runingTime;
	public String L1;
	public String L2;
	public String L3;
	public String threads;
	public String cores;
	public String cache;
	public String cpu;
	public int imple;
	public int pi;
	
	public Benchmark(String date, String runningTime, String L1,String L2,String L3,String threads,String cores,String cache,String cpu, int imple, int pi){
		this.date = date;
		this.runingTime = runningTime;
		this.L1 = L1;
		this.L2 = L2;
		this.L3 = L3;
		this.threads = threads;
		this.cores = cores;
		this.cache = cache;
		this.cpu = cpu;
		this.id = -1;
		this.imple = imple;
		this.pi = pi;
	}
	
	public Benchmark(int id,String date, String runningTime, String L1,String L2,String L3,String threads,String cores,String cache,String cpu, int imple, int pi){
		this.id = id;
		this.date = date;
		this.runingTime = runningTime;
		this.L1 = L1;
		this.L2 = L2;
		this.L3 = L3;
		this.threads = threads;
		this.cores = cores;
		this.cache = cache;
		this.cpu = cpu;
		this.imple = imple;
		this.pi = pi;
	}
}
