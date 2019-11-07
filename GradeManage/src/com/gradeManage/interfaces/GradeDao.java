package com.gradeManage.interfaces;

import java.util.ArrayList;

public interface GradeDao {
	
	boolean addGrade(int grade);//添加成绩
	
	boolean deleteGrade();//删除成绩
	
	ArrayList<String> gradeStatics();//成绩统计
	
	ArrayList<String> gradeRecord();//成绩查询
}
