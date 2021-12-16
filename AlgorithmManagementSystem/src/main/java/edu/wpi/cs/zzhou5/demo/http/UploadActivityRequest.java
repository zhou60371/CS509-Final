package edu.wpi.cs.zzhou5.demo.http;

public class UploadActivityRequest {
	public String name;
	public String activity;
	public String time;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAvtivity() {
		return activity;
	}
	public void setAvtivity(String avtivity) {
		this.activity = avtivity;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public UploadActivityRequest() {}
	public UploadActivityRequest(String name, String avtivity, String time) {
		super();
		this.name = name;
		this.activity = avtivity;
		this.time = time;
	}
}
