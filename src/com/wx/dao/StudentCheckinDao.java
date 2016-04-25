package com.wx.dao;

import java.util.List;

public interface StudentCheckinDao {
	
	public int getMaxSeqBylessonID(long lessonID);
	public void add(String sid,long lessonID,String checkTime,int seq);
	public List<String> getAbsenceListByLesson(long lessonID);
	public void delete(long lessonID);

}
