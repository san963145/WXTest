package com.wx.dao;

import java.util.ArrayList;

/**
 * 
 * 问题反馈记录表  
 *
 */
public interface FeedBackDao {
	
	/**
	 * 添加一条记录
	 * 
	 */
	public boolean add(long lessonID,String sid,String content,String time);
	/**
	 * 获取Lesson的所有问题反馈记录
	 * 
	 */
	public ArrayList<String> getListByLesson(long lessonID);
	
	

}
