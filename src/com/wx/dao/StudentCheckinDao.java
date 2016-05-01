package com.wx.dao;

import java.util.List;

/**
 * 学生签到记录表
 */
public interface StudentCheckinDao {
	
	/**
	 * 查询当前Lesson最大签到序号
	 */
	public int getMaxSeqBylessonID(long lessonID);
	
	/**
	 * 添加一行记录
	 */
	public void add(String sid,long lessonID,String checkTime,int seq);
	
	/**
	 * 获取缺勤学生列表
	 */
	public List<String> getAbsenceListByLesson(long lessonID);
	
	/**
	 * 删除Lesson对应所有签到记录
	 */
	public void delete(long lessonID);

}
