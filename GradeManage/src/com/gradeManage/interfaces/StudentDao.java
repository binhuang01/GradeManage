package com.gradeManage.interfaces;

import java.sql.ResultSet;

import com.gradeManage.entities.Student;

public interface StudentDao {
	
	
		public static boolean studentCheck(String userName,String passWord) {
			return false;
		}//学生登录验证
		
		static ResultSet studentIfo(String userName) {//查询学生信息
			return null;
		};
		
		static ResultSet studentGrade(String userName) {//查询学生成绩
			return null;
		};
		
		static boolean addStu(Student stu) {//添加学生
			return false;
		}
}
