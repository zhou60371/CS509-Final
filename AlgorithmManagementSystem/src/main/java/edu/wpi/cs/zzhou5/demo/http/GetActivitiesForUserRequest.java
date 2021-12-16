package edu.wpi.cs.zzhou5.demo.http;

public class GetActivitiesForUserRequest {
	public String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public GetActivitiesForUserRequest() {}
	public GetActivitiesForUserRequest(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "GetActivitiesForUserRequest [name=" + name + "]";
	}
	
	
}
