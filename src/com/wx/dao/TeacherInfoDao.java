package com.wx.dao;

/**
 *  ��ʦ������Ϣ��
 */
public interface TeacherInfoDao {

	/**
	 *  ��ʦ��¼�ж�
	 */
	public String checkTeacherLogin(String tid,String tpwd);
	
	/**
	 *  ��ʦ��¼�ж�
	 */
	public String getRole(String tid);
	
	/**
	 *  �жϽ�ʦID�Ƿ����
	 */
	public boolean checkTid(String tid);
	
	/**
	 *  ���һ�м�¼�����̣�
	 */
	public boolean add(String userID,String password,int role);
}
