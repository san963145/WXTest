package com.wx.dao;

import java.util.ArrayList;

/**
 * 
 * 答题模块  学生作答结果记录表
 *
 */
public interface StudentAnswerDao {
	
	/**
	 *  添加一行记录
	 */
	public boolean add(String sid,long qid,String sName,String answer,int corrected,String time);
	
	/**
	 *  判断学生是否已回答某问题
	 */
	public boolean checkSidByQid(long qid,String sid);
	
	/**
	 *  更新某个学生的答案
	 */
	public void updateAnswer(long qid,String sid,String answer,int corrected);
	
	/**
	 *  统计某个题目的回答人数
	 */
	public int getCountByQid(long qid);
	
	/**
	 *  获取题目的所有回答结果列表（学生姓名、作答结果）
	 */
    public ArrayList<String> getListByQid(long qid);
    
    /**
	 *  获取题目的答对人数
	 */
    public int getCorrectNumByQid(long qid);
    
    /**
	 *  统计题目回答结果（作答结果、人数）
	 */
    public ArrayList<ArrayList<String>> getAnswerListByQid(long qid);
    
    /**
	 *  在线学生中未答题学生列表
	 */
    public ArrayList<ArrayList<String>> getNoAnswerListByQid(long qid,long lessonID);
}
