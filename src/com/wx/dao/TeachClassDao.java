package com.wx.dao;

import java.util.Map;

public interface TeachClassDao {
	
	public Map<String,String> getClassListByTid(String Tid);
    public String getNameAndTotalbyClass(String tClassID);
    public void setTutorID(String tClassID,String tutorID);
    public Map<String,String> getClassListByTutorID(String tutorID);
}
