package com.wx.dao;

/**
 * 
 * Lesson��¼�� ,һ�м�¼Ϊһ�ÿ�
 *
 */
public interface LessonDao {

	/**
	 *   ���ݵ�ǰtClass���һ��Lesson,LessonID���ݵ�ǰʱ������
	 */
	public void add(String classID);
	
	/**
	 *   ��ѯtClass�о��뵱ǰʱ�������һ��Lesson,����LessonID
	 */
	public long getMaxLessonID(String classID);
	
	public void delete(long lessonID);


}
