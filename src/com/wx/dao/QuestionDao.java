package com.wx.dao;

import java.util.ArrayList;

/**
 * 
 * 答题模块  题目记录表
 *
 */
public interface QuestionDao {
	
	/**
	 *   添加一条记录
	 */
	public boolean add(long qid,String tid,long lessonID,int number,String title,String answer,String content);
    
	/**
	 *   查询当前Lesson所有题目的最大题号
	 */
	public int getMaxNumByLesson(long lessonID);
	
	/**
	 *   获取当前Lesson所有题目的标题
	 */
    public ArrayList<String> getTitlesByLesson(long lessonID);
    
    /**
	 *   根据题目序号获取题目ID
	 */
    public long getQidByNum(long lessonID,int number);
    
    /**
	 *   查询题目的答案
	 */
    public String getAnswerByQid(long qid);
    
    /**
	 *   查询题目的标题
	 */
    public String getTitleByQid(long qid);
	
}
