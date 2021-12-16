package edu.wpi.cs.zzhou5.demo.http;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.cs.zzhou5.demo.model.Classification;

public class GetClassificationsHierarchyResponse {
	public final Map<String,Classification> map;
	public final int statusCode;
	public final String error;
	
	public GetClassificationsHierarchyResponse (Map<String,Classification> map, int code) {
		this.map = map;
		this.statusCode = code;
		this.error = "";
	}
	
	public GetClassificationsHierarchyResponse (int code, String errorMessage) {
		this.map = new HashMap<>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (map == null) { return "EmptyConstants"; }
		return "AllConstants(" + map.size() + ")";
	}
}
