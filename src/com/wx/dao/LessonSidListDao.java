package com.wx.dao;


/**
 * 
 * @author Administrator
 *  
 *  ��ͬLesson��ѧ��ѧ����OpenID�󶨼�¼��
 */
public interface LessonSidListDao {  
	
	public void add(long lessonID,String sid, String openID);
	public String checkOpenID(long lessonID,String openID);
	public int getCountByLesson(long lessonID);
	public boolean checkSid(long lessonID,String sid);

}
