package com.wx.dao;

import java.util.ArrayList;

/**
 * 
 * Lesson��ѧ��ǩ��������ȱ������ͳ�Ʊ�,����LessonID
 *
 */
public interface LessonSignRecordDao {
	
	/**
	 * ���һ�м�¼
	 */
	public void add(long lessonID,String tClassID,int signCount,int unsignCount);
	
	/**
	 * ͳ��tClass��Ӧ��ʷLesson��Lesson��š�����������������,���Ϊ3���ֶε��б�
	 */
	public ArrayList<ArrayList<String>> getLessonRecordByClass(String tClassID,int total);
	
	/**
	 * ͳ��tClass��Ӧ��ʷLesson��ƽ��������
	 */
	public Double getAvgSignNumClass(String tClassID);
	
	/**
	 * ͳ��tClass��Ӧ����ʷLesson����Ŀ
	 */
	public String getLessonCount(String tClassID);
	
	/**
	 * ɾ����ǰLesson��Ӧ��һ�м�¼�����ڶ��ǩ����
	 */
	public void delete(long lessonID);
	
	/**
	 * �ж�Lesson��Ӧ��¼�Ƿ��Ѵ���
	 */
	public boolean checkByLesson(long lessonID);
}
