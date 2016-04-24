package com.wx.dao;

import java.util.ArrayList;

public interface StudentAnswerDao {
	
	public boolean add(String sid,long qid,String sName,String answer,int corrected,String time);
	public boolean checkSidByQid(long qid,String sid);
	public void updateAnswer(long qid,String sid,String answer,int corrected);
	public int getCountByQid(long qid);
    public ArrayList<String> getListByQid(long qid);
    public int getCorrectNumByQid(long qid);
    public ArrayList<ArrayList<String>> getAnswerListByQid(long qid);
}
