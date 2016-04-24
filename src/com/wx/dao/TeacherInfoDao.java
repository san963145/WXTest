package com.wx.dao;


public interface TeacherInfoDao {

	public String checkTeacherLogin(String tid,String tpwd);
	public boolean checkTid(String tid);
	public boolean add(String userID,String password,int role);
}
