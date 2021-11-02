package edu.wpi.cs.zzhou5.demo.model;

public class Implementation {
	public final String language;
	public final String context;
	public int id;
	public final int algorithm;
	
	public Implementation (String lan, String con,int algo) {
		this.context = con;
		this.language = lan;
		this.algorithm = algo;
		this.id = -1;
	}
	
	public Implementation (String lan, String con, int id,int algo) {
		this.context = con;
		this.language = lan;
		this.id = id;
		this.algorithm = algo;
	}
}
