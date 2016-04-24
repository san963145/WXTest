package com.wx.dao;

import java.util.ArrayList;

public interface FeedBackDao {
	
	public boolean add(long lessonID,String sid,String content,String time);
	public ArrayList<String> getListByLesson(long lessonID);

}
