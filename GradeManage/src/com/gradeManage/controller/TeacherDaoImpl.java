package com.gradeManage.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gradeManage.interfaces.TeacherDao;

/*
 * 	@author binhuang
 * 	@version 1.0
 * 	@date 2019-10-26
 * 
 */

public class TeacherDaoImpl implements TeacherDao {

	public static boolean teacherCheck(String uname,String upwd) {//教师登录验证
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getConnection();
		try {
			Statement  stmt = conn.createStatement();
			String sql = "select userName,password from teacher where userName="+uname+" and password="+upwd;
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
					return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static ResultSet teacherIfo(String uname) { //教师信息查询
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getConnection();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from teacher where userName="+uname;
			ResultSet rs  = stmt.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean isTeacher() { //判断是否是教师
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
