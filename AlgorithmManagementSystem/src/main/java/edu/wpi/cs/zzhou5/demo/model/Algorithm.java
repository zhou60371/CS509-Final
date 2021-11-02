package edu.wpi.cs.zzhou5.demo.model;

public class Algorithm {
	public String name;
	public String description;
	public int stars;
	public int id;
	public int classification;
	
	public Algorithm(String name,int classification) {
		this.name = name;
		this.classification = classification;
		this.id = -1;
	}
	
	public Algorithm(String name,int classification,String description) {
		this.name = name;
		this.classification = classification;
		this.description = description;
		this.id = -1;
	}
	
	public Algorithm(String name,int classification, int stars) {
		this.name = name;
		this.classification = classification;
		this.stars = stars;
		this.id = -1;
	}
	
	public Algorithm(String name,String description,int stars,int classification) {
		this.name = name;
		this.classification = classification;
		this.description = description;
		this.stars = stars;
		this.id = -1;
	}
	public Algorithm(String name,int classification,String description,int id,int stars) {
		this.name = name;
		this.classification = classification;
		this.description = description;
		this.stars = stars;
		this.id = id;
	}
	
}
