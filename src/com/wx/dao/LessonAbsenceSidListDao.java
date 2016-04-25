package com.wx.dao;

import java.util.ArrayList;

public interface LessonAbsenceSidListDao {
	
	public void add(long lessonID,String tClassID,String sid);
	public ArrayList<ArrayList<String>> getByClass(String tClassID);
    public void delete(long lessonID);
}
