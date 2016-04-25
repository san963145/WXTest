package com.wx.dao;

import java.util.ArrayList;

public interface LessonSignRecordDao {
	
	public void add(long lessonID,String tClassID,int signCount,int unsignCount);
	public ArrayList<ArrayList<String>> getLessonRecordByClass(String tClassID,int total);
	public Double getAvgSignNumClass(String tClassID);
	public String getLessonCount(String tClassID);
	public void delete(long lessonID);
	public boolean checkByLesson(long lessonID);
}
