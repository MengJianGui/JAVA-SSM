package com.ssm.domain;

import java.io.Serializable;

public class Tutor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;   //导师id
	private String name;  //导师姓名
	private String title; //导师职称
	private String phone; //电话
	private String field; //研究领域
	private String email; //邮箱
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Tutor [id=" + id + ", name=" + name + ", title=" + title + ", phone=" + phone + ", field=" + field
				+ ", email=" + email + "]";
	}
	

}
