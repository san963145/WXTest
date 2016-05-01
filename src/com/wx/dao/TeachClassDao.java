package com.wx.dao;

import java.util.Map;

/**
 *   tClass表,teacher、course对应关系表
 */
public interface TeachClassDao {
	
	/**
	 *   查询教师对应的所有课程
	 */
	public Map<String,String> getClassListByTid(String Tid);
	
	/**
	 *   查询课程名及总人数
	 */
    public String getNameAndTotalbyClass(String tClassID);
    
    /**
	 *   为课程分配助教
	 */
    public void setTutorID(String tClassID,String tutorID);
    
    /**
	 *   查询助教对应的所有课程
	 */
    public Map<String,String> getClassListByTutorID(String tutorID);
}
