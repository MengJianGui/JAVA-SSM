package com.ssm.domain;

import java.io.Serializable;

public class Service implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private User user;
	
	private int id;
	private String moment;
	private String location;
	private String behavior;
	private String state_duration;
	private String user_pressure;
	private String service;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getMoment() {
		return moment;
	}
	public void setMoment(String moment) {
		this.moment = moment;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getBehavior() {
		return behavior;
	}
	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}
	public String getState_duration() {
		return state_duration;
	}
	public void setState_duration(String state_duration) {
		this.state_duration = state_duration;
	}
	public String getUser_pressure() {
		return user_pressure;
	}
	public void setUser_pressure(String user_pressure) {
		this.user_pressure = user_pressure;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	@Override
	public String toString() {
		return "Service [id=" + id + ", user=" + user.getName() + ", moment=" + moment + ", location=" + location + ", behavior="
				+ behavior + ", state_duration=" + state_duration + ", user_pressure=" + user_pressure + ", service="
				+ service + "]";
	}
	
	
}
