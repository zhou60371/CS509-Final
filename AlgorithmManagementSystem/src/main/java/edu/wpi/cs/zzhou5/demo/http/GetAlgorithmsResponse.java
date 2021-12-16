package edu.wpi.cs.zzhou5.demo.http;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.zzhou5.demo.model.Algorithm;

public class GetAlgorithmsResponse {
	public final List<Algorithm> list;
	public final int statusCode;
	public final String error;
	
	public GetAlgorithmsResponse(List<Algorithm> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public GetAlgorithmsResponse(int code, String errorMessage) {
		this.list = new ArrayList<Algorithm>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (list == null) { return "EmptyAlgorithms"; }
		return "AllAlgorithms(" + list.size() + ")";
	}
}
