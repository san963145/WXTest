package com.wx.dao;

import java.util.ArrayList;

/**
 * 
 * Lesson缺勤学生记录表  
 *
 */
public interface LessonAbsenceSidListDao {
	
	/**
	 *   添加一条记录
	 */
	public void add(long lessonID,String tClassID,String sid);
	
	/**
	 *   统计tClass所有缺勤学生的学号、姓名、旷课次数,结果为3个字段的列表
	 */
	public ArrayList<ArrayList<String>> getByClass(String tClassID);
	
	/**
	 *   删除Lesson的所有缺勤学生记录（用于多次签到）
	 */
    public void delete(long lessonID);
}
