package com.wx.dao;

/**
 * 
 * ѧ��������Ϣ��
 *
 */
public interface StudentInfoDao {
	
	/**
	 *  �ж�ѧ���Ƿ����
	 */
    public boolean checkUserId(String userId);
    
    /**
	 *  ����ѧ�Ų�����
	 */
    public String getNameById(String userId);
    
    /**
	 *  ���һ�м�¼
	 */
	public boolean insert(String userId,String userName,String spwd,String className,String sex,String openID);

}
