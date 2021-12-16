package edu.wpi.cs.zzhou5.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {
	public String name;
	public String description;
	public int id;
	public int classification;
	public List<Implementation> imples;
	
	public Algorithm(String name,int classification) {
		this.name = name;
		this.classification = classification;
		this.id = -1;
		this.imples = new ArrayList<>();
	}
	
	public Algorithm(String name,int classification,String description) {
		this.name = name;
		this.classification = classification;
		this.description = description;
		this.id = -1;
		this.imples = new ArrayList<>();
	}
	
	public Algorithm(String name,int classification,String description,int id) {
		this.name = name;
		this.classification = classification;
		this.description = description;
		this.id = id;
		this.imples = new ArrayList<>();
	}
	
}
