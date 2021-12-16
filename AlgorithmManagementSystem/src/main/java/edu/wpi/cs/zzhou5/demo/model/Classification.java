package edu.wpi.cs.zzhou5.demo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Classification {
	public final String name;
	public final int id;
	public final int[] childrenID;
	public final int fatherID;
	public final int level;
	public Map<String,Classification> childern;
	public List<Algorithm> algos;
	
	public Classification(String name,int id,int[] childrenID, int level, int fatherId) {
		this.name = name;
		this.id = id;
		this.childrenID = childrenID;
		this.fatherID = fatherId;
		this.level = level;
		this.childern = new HashMap<String, Classification>();
		this.algos = new ArrayList<>();
	}
	
	public Classification(String name,int[] childrenID, int level) {
		this.name = name;
		this.id = -1;
		this.childrenID = childrenID;
		this.fatherID = -1;
		this.level = level;
		this.childern = new HashMap<String, Classification>();
		this.algos = new ArrayList<>();
	}
	
	public Classification(String name,int fatherID, int level) {
		this.name = name;
		this.id = -1;
		this.childrenID = null;
		this.fatherID = fatherID;
		this.level = level;
		this.childern = new HashMap<String, Classification>();
		this.algos = new ArrayList<>();
	}
}
