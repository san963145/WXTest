package com.wx.dao;

import java.util.ArrayList;

/**
 * 
 * ���ⷴ����¼��  
 *
 */
public interface FeedBackDao {
	
	/**
	 * ���һ����¼
	 * 
	 */
	public boolean add(long lessonID,String sid,String content,String time);
	/**
	 * ��ȡLesson���������ⷴ����¼
	 * 
	 */
	public ArrayList<String> getListByLesson(long lessonID);
	
	

}
