package com.wx.dao;


/**
 * 
 * @author Administrator
 *  
 *  不同Lesson中学生学号与OpenID绑定记录表
 */
public interface LessonSidListDao {  
	
	public void add(long lessonID,String sid, String openID);
	public String checkOpenID(long lessonID,String openID);
	public int getCountByLesson(long lessonID);
	public boolean checkSid(long lessonID,String sid);

}
