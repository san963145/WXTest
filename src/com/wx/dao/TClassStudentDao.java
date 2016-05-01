package com.wx.dao;

/**
 * 
 * 学生、tClass对应关系表
 *
 */
public interface TClassStudentDao {
	
	/**
	 *  判断tClass中是否包含某学生
	 */
	public boolean checkUserId(String classID,String userID);

}
