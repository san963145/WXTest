package com.wx.dao;


/**
 * 
 * 
 *  
 *  ��ͬLesson��ѧ��ѧ����OpenID�󶨼�¼��, LessonID��ѧ����������
 */
public interface LessonSidListDao {  
	
	/**
	 * ���һ�м�¼
	 */
	public void add(long lessonID,String sid, String openID);
	
	/**
	 * �ж�ѧ��OpenID�Ƿ��Ѽ�¼��Lesson
	 */
	public String checkOpenID(long lessonID,String openID);
	
	/**
	 * ��ѯLesson������ɰ󶨵�ѧ����������������������
	 */
	public int getCountByLesson(long lessonID);
	
	/**
	 * �ж�ѧ��ѧ���Ƿ��Ѽ�¼��Lesson
	 */
	public boolean checkSid(long lessonID,String sid);

}
