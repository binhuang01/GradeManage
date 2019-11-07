package com.gradeManage.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FileDaoImpl {
	public static void addFile(String filePath,String fileName) {
		
		Connection conn = DBConnection.getConnection();
		
		try {
			String sql = "insert into filees values(?,?)";
			java.sql.PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,filePath);
			pstmt.setString(2,fileName);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ResultSet getFile() {
		Connection conn = DBConnection.getConnection();
		
		try {
			Statement stmt = conn.createStatement();
			String sql = "select file_Names from filees";
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static String findFile(String fileName) {
		Connection conn = DBConnection.getConnection();		
		try {
			Statement stmt = conn.createStatement();
			String sql = "select file_path from filees";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				String filePath = rs.getString("file_Path");
				return filePath+fileName;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
