package com.ssm.domain;

import java.io.Serializable;

public class Alarm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private int id;
	private String alarm_time;
	private String alarm_service;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAlarm_time() {
		return alarm_time;
	}
	public void setAlarm_time(String alarm_time) {
		this.alarm_time = alarm_time;
	}
	public String getAlarm_service() {
		return alarm_service;
	}
	public void setAlarm_service(String alarm_service) {
		this.alarm_service = alarm_service;
	}
	
	
	@Override
	public String toString() {
		return "Alarm [id=" + id + ", alarm_time=" + alarm_time + ", alarm_service=" + alarm_service + "]";
	}
	
}
