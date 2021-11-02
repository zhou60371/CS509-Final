package edu.wpi.cs.zzhou5.demo.http;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.zzhou5.demo.model.Classification;

public class AllClassificationsResponse {
	public final List<Classification> list;
	public final int statusCode;
	public final String error;
	
	public AllClassificationsResponse (List<Classification> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public AllClassificationsResponse (int code, String errorMessage) {
		this.list = new ArrayList<Classification>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (list == null) { return "EmptyConstants"; }
		return "AllConstants(" + list.size() + ")";
	}
}
