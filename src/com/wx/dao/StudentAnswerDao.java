package com.wx.dao;

import java.util.ArrayList;

/**
 * 
 * ����ģ��  ѧ����������¼��
 *
 */
public interface StudentAnswerDao {
	
	/**
	 *  ���һ�м�¼
	 */
	public boolean add(String sid,long qid,String sName,String answer,int corrected,String time);
	
	/**
	 *  �ж�ѧ���Ƿ��ѻش�ĳ����
	 */
	public boolean checkSidByQid(long qid,String sid);
	
	/**
	 *  ����ĳ��ѧ���Ĵ�
	 */
	public void updateAnswer(long qid,String sid,String answer,int corrected);
	
	/**
	 *  ͳ��ĳ����Ŀ�Ļش�����
	 */
	public int getCountByQid(long qid);
	
	/**
	 *  ��ȡ��Ŀ�����лش����б�ѧ����������������
	 */
    public ArrayList<String> getListByQid(long qid);
    
    /**
	 *  ��ȡ��Ŀ�Ĵ������
	 */
    public int getCorrectNumByQid(long qid);
    
    /**
	 *  ͳ����Ŀ�ش�������������������
	 */
    public ArrayList<ArrayList<String>> getAnswerListByQid(long qid);
    
    /**
	 *  ����ѧ����δ����ѧ���б�
	 */
    public ArrayList<ArrayList<String>> getNoAnswerListByQid(long qid,long lessonID);
}
