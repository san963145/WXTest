package com.wx.dao;

/**
 *  教师基本信息表
 */
public interface TeacherInfoDao {

	/**
	 *  教师登录判断
	 */
	public String checkTeacherLogin(String tid,String tpwd);
	
	/**
	 *  判断教师ID是否存在
	 */
	public boolean checkTid(String tid);
	
	/**
	 *  添加一行记录（助教）
	 */
	public boolean add(String userID,String password,int role);
}
