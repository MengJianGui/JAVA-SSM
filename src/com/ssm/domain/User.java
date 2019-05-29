package com.ssm.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;       //学生id；
	private String name;      //学生姓名；
	private String eduid;     //学号；
	private Tutor tutor;      //导师信息;
	private String grade;     //年级；研一，研二，研三，已毕业；
	private String sex;       //性别；
	private String phone;     //电话号码；
	private String wechat;    //微信号；
	private String qq;        //qq号；
	private String email;     //邮箱；
	private String homeaddress;//家庭地址；
	private String field;     //研究领域；
	private String address;   //现住址；
	
	/**
	 *  使用@ModelAttribute接收参数时
	 *  form表单中有日期,Spring不知道该如何转换,
	 *  要在实体类的日期属性上加@DateTimeFormat(pattern="yyyy-MM-dd")注解 
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date birthday;    //出生日期；
	private String company;   //所在公司；
	private String remark;    //备注；
	
	
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
	public String getEduid() {
		return eduid;
	}
	public void setEduid(String eduid) {
		this.eduid = eduid;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHomeaddress() {
		return homeaddress;
	}
	public void setHomeaddress(String homeaddress) {
		this.homeaddress = homeaddress;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Tutor getTutor() {
		return tutor;
	}
	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", eduid=" + eduid + ", tutor=" + tutor + ", grade=" + grade
				+ ", sex=" + sex + ", phone=" + phone + ", wechat=" + wechat + ", qq=" + qq + ", email=" + email
				+ ", homeaddress=" + homeaddress + ", field=" + field + ", address=" + address + ", birthday="
				+ birthday + ", company=" + company + ", remark=" + remark + "]";
	}
	
}
