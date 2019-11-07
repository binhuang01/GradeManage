package com.gradeManage.interfaces;

import java.sql.ResultSet;

public interface TeacherDao {

	public static boolean teacherCheck() {//登录验证
		return false;
	}
	
	static ResultSet teacherIfo(String uname) {
		return null;
	}//教师信息
	
	boolean isTeacher();//是否是教师
}
