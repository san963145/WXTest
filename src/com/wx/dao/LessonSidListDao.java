package com.wx.dao;


/**
 * 
 * 
 *  
 *  不同Lesson中学生学号与OpenID绑定记录表, LessonID与学号联合主键
 */
public interface LessonSidListDao {  
	
	/**
	 * 添加一行记录
	 */
	public void add(long lessonID,String sid, String openID);
	
	/**
	 * 判断学生OpenID是否已记录在Lesson
	 */
	public String checkOpenID(long lessonID,String openID);
	
	/**
	 * 查询Lesson中已完成绑定的学生人数（课堂在线人数）
	 */
	public int getCountByLesson(long lessonID);
	
	/**
	 * 判断学生学号是否已记录在Lesson
	 */
	public boolean checkSid(long lessonID,String sid);

}
