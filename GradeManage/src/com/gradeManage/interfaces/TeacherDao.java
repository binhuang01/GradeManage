package com.gradeManage.interfaces;

import java.sql.ResultSet;

public interface TeacherDao {

	public static boolean teacherCheck() {//��¼��֤
		return false;
	}
	
	static ResultSet teacherIfo(String uname) {
		return null;
	}//��ʦ��Ϣ
	
	boolean isTeacher();//�Ƿ��ǽ�ʦ
}
