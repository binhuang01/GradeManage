package com.gradeManage.interfaces;

import java.sql.ResultSet;

import com.gradeManage.entities.Student;

public interface StudentDao {
	
	
		public static boolean studentCheck(String userName,String passWord) {
			return false;
		}//ѧ����¼��֤
		
		static ResultSet studentIfo(String userName) {//��ѯѧ����Ϣ
			return null;
		};
		
		static ResultSet studentGrade(String userName) {//��ѯѧ���ɼ�
			return null;
		};
		
		static boolean addStu(Student stu) {//���ѧ��
			return false;
		}
}
