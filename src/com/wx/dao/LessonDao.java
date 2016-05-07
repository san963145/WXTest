package com.wx.dao;

/**
 * 
 * Lesson记录表 ,一行记录为一堂课
 *
 */
public interface LessonDao {

	/**
	 *   根据当前tClass添加一个Lesson,LessonID根据当前时间生成
	 */
	public void add(String classID);
	
	/**
	 *   查询tClass中距离当前时间最近的一个Lesson,返回LessonID
	 */
	public long getMaxLessonID(String classID);
	
	public void delete(long lessonID);


}
