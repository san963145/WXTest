package com.wx.dao;

import java.util.ArrayList;

public interface QuestionDao {
	
	public boolean add(long qid,String tid,long lessonID,int number,String title,String answer,String content);
    public int getMaxNumByLesson(long lessonID);
    public ArrayList<String> getTitlesByLesson(long lessonID);
    public long getQidByNum(long lessonID,int number);
    public String getAnswerByQid(long qid);
    public String getTitleByQid(long qid);
	
}
