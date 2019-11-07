package com.gradeManage.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gradeManage.entities.Student;
import com.gradeManage.interfaces.StudentDao;

public class StudentDaoImpl implements StudentDao {

	public static boolean studentCheck(String uname,String upwd) {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getConnection();
		try {
			Statement  stmt = conn.createStatement();
			String sql = "select userName,password from student where userName="+uname+" and password="+upwd;
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return false;
	}

	public static ResultSet studentIfo(String userName) {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getConnection();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select stuId,stuName,stuSex,birthday,student.phone,address,class_name,depar_name from student,class,deparment where userName='"+userName+"' and student.class_id=class.class_id and Deparment.depar_id=class.depar_id";
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
	
	public static ResultSet studentGrade(String userName) {
		Connection conn = DBConnection.getConnection();
		try {
			Statement stmt = conn.createStatement();
			String sql = "SELECT student.StuId,StuName,Course_name,Grade " + 
					"from student,course,studentgrade " + 
					"where studentgrade.Course_id = course.Course_id and student.StuId = studentgrade.Stu_id and userName='"+userName+"'"+ 
					"GROUP BY  student.StuId,StuName,Course_name,Grade";
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean addStu(Student stu) {//Ìí¼ÓÑ§Éú
			Connection conn = DBConnection.getConnection();
			try {
				Statement stmt = conn.createStatement();
				String sql = "select class_id from class where class_name='"+stu.getClazz()+"'";
				ResultSet rs = stmt.executeQuery(sql);
				String class_id = "";
				if(rs.next()) {
					class_id = rs.getString("class_id");
				}
				
				String sql2 = "insert into student(StuId,StuName,StuSex,Birthday,Phone,Address,Class_id,userName,password) values('"+stu.getStuId()+"','"+stu.getStuName()+"','"+stu.getSex()+"','"+stu.getBirth()+" 00:00:01','"+stu.getPhone()+"','"+"  "+"','"+class_id+"','"
						+stu.getUserName()+"','"+stu.getPassword()+"')";
				stmt.executeUpdate(sql2);		
					return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
	}
	
	public static ResultSet getStuClazz() {
		Connection conn = DBConnection.getConnection();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select class_name from class";
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
