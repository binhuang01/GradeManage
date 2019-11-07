package com.gradeManage.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * 	@author bin huang
 * 	@version 1.0
 * 
 * 
 */

	public class DBConnection {
		
		private static final String url = "jdbc:mysql://localhost:3306/GradeManage?useSSL=false&serverTimezone=UTC&characterEncoding=utf8&user=root&password=root";
		
		static {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.println("ok");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static Connection getConnection(){
			try {
				Connection conn = DriverManager.getConnection(url);
				return conn;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		
		
	}
