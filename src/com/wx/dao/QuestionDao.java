package com.wx.dao;

import java.util.ArrayList;

/**
 * 
 * ����ģ��  ��Ŀ��¼��
 *
 */
public interface QuestionDao {
	
	/**
	 *   ���һ����¼
	 */
	public boolean add(long qid,String tid,long lessonID,int number,String title,String answer,String content);
    
	/**
	 *   ��ѯ��ǰLesson������Ŀ��������
	 */
	public int getMaxNumByLesson(long lessonID);
	
	/**
	 *   ��ȡ��ǰLesson������Ŀ�ı���
	 */
    public ArrayList<String> getTitlesByLesson(long lessonID);
    
    /**
	 *   ������Ŀ��Ż�ȡ��ĿID
	 */
    public long getQidByNum(long lessonID,int number);
    
    /**
	 *   ��ѯ��Ŀ�Ĵ�
	 */
    public String getAnswerByQid(long qid);
    
    /**
	 *   ��ѯ��Ŀ�ı���
	 */
    public String getTitleByQid(long qid);
	
}
