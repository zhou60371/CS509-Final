package edu.wpi.cs.zzhou5.demo.model;

import java.util.HashMap;
import java.util.Map;

public class Classification {
	public final String name;
	public final int id;
	public final int[] childrenID;
	public final int level;
	public Map<String,Classification> childern;
	
	public Classification(String name,int id,int[] childrenID, int level) {
		this.name = name;
		this.id = id;
		this.childrenID = childrenID;
		this.level = level;
		this.childern = new HashMap<String, Classification>();
	}
	
	public Classification(String name,int[] childrenID, int level) {
		this.name = name;
		this.id = -1;
		this.childrenID = childrenID;
		this.level = level;
		this.childern = new HashMap<String, Classification>();
	}
}
