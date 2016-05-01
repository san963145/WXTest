package com.wx.dao;

import java.util.ArrayList;

/**
 * 
 * Lesson中学生签到人数、缺勤人数统计表,主键LessonID
 *
 */
public interface LessonSignRecordDao {
	
	/**
	 * 添加一行记录
	 */
	public void add(long lessonID,String tClassID,int signCount,int unsignCount);
	
	/**
	 * 统计tClass对应历史Lesson的Lesson序号、出勤人数、出勤率,结果为3个字段的列表
	 */
	public ArrayList<ArrayList<String>> getLessonRecordByClass(String tClassID,int total);
	
	/**
	 * 统计tClass对应历史Lesson的平均出勤率
	 */
	public Double getAvgSignNumClass(String tClassID);
	
	/**
	 * 统计tClass对应的历史Lesson的数目
	 */
	public String getLessonCount(String tClassID);
	
	/**
	 * 删除当前Lesson对应的一行记录（用于多次签到）
	 */
	public void delete(long lessonID);
	
	/**
	 * 判断Lesson对应记录是否已存在
	 */
	public boolean checkByLesson(long lessonID);
}
