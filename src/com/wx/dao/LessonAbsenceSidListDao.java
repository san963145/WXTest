package com.wx.dao;

import java.util.ArrayList;

/**
 * 
 * Lessonȱ��ѧ����¼��  
 *
 */
public interface LessonAbsenceSidListDao {
	
	/**
	 *   ���һ����¼
	 */
	public void add(long lessonID,String tClassID,String sid);
	
	/**
	 *   ͳ��tClass����ȱ��ѧ����ѧ�š����������δ���,���Ϊ3���ֶε��б�
	 */
	public ArrayList<ArrayList<String>> getByClass(String tClassID);
	
	/**
	 *   ɾ��Lesson������ȱ��ѧ����¼�����ڶ��ǩ����
	 */
    public void delete(long lessonID);
}
