package com.wx.dao;

import java.util.List;

/**
 * ѧ��ǩ����¼��
 */
public interface StudentCheckinDao {
	
	/**
	 * ��ѯ��ǰLesson���ǩ�����
	 */
	public int getMaxSeqBylessonID(long lessonID);
	
	/**
	 * ���һ�м�¼
	 */
	public void add(String sid,long lessonID,String checkTime,int seq);
	
	/**
	 * ��ȡȱ��ѧ���б�
	 */
	public List<String> getAbsenceListByLesson(long lessonID);
	
	/**
	 * ɾ��Lesson��Ӧ����ǩ����¼
	 */
	public void delete(long lessonID);

}
