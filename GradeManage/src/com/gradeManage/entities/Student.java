package com.gradeManage.entities;


public class Student {
		
	private String stuName;//姓名
	private String stuId;//学号
	private String sex;//性别
	private String birth;//生日
	private String phone;//联系方式
	private String userName;//用户名
	private String password;//密码
	private String clazz;//班级
	
	public Student() {
		
	}
	
	

	public Student(String stuName, String stuId, String sex, String birth, String phone, String userName,
			String password) {
		super();
		this.stuName = stuName;
		this.stuId = stuId;
		this.sex = sex;
		this.birth = birth;
		this.phone = phone;
		this.userName = userName;
		this.password = password;
	}



	public String getClazz() {
		return clazz;
	}



	public void setClazz(String clazz) {
		this.clazz = clazz;
	}



	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
