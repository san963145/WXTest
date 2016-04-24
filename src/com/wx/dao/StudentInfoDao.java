package com.wx.dao;

public interface StudentInfoDao {
	
	public String checkStudentLogin(String sid,String spwd);
    public boolean checkUserId(String userId);
    public String getNameById(String userId);
	public boolean insert(String userId,String userName,String spwd,String className,String sex,String openID);

}
