package com.wx.dao;

public interface LessonDao {
	
	public void add(String classID);
	public long getMaxLessonID(String classID);


}
