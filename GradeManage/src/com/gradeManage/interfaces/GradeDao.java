package com.gradeManage.interfaces;

import java.util.ArrayList;

public interface GradeDao {
	
	boolean addGrade(int grade);//��ӳɼ�
	
	boolean deleteGrade();//ɾ���ɼ�
	
	ArrayList<String> gradeStatics();//�ɼ�ͳ��
	
	ArrayList<String> gradeRecord();//�ɼ���ѯ
}
