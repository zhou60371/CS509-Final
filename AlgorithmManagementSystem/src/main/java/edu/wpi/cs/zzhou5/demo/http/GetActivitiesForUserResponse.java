package edu.wpi.cs.zzhou5.demo.http;

import java.util.ArrayList;

import edu.wpi.cs.zzhou5.demo.model.Activity;

public class GetActivitiesForUserResponse {
	public ArrayList<Activity> activities;
	public int httpCode;
	public String error;
	
	public GetActivitiesForUserResponse() {}
	public GetActivitiesForUserResponse(int httpCode,ArrayList<Activity> activities) {
		this.httpCode = httpCode;
		this.activities = activities;
		this.error = "";
	}

	public GetActivitiesForUserResponse(int httpCode, String error) {
		this.httpCode = httpCode;
		this.error = error;
	}

	@Override
	public String toString() {
		return "GetActivitiesForUserResponse [httpCode=" + httpCode + ", error=" + error + "]";
	}
	
	
}
