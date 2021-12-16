package edu.wpi.cs.zzhou5.demo.model;

import java.util.ArrayList;

public class ProblemInstance {
	public int id;
	public String fileAdress;
	public int caseType;
	public int algorithm;
	public String user;
	public ArrayList<Benchmark> benchmarks;
	
	public ProblemInstance(int id,String fileAdress,int caseType,int algo,String user) {
		this.id = id;
		this.fileAdress = fileAdress;
		this.caseType = caseType;
		this.algorithm = algo;
		this.user = user;
	}
	public ProblemInstance(String fileAdress,int caseType,int algo,String user) {
		this.fileAdress = fileAdress;
		this.caseType = caseType;
		this.algorithm = algo;
		this.user = user;
	}
	
}
