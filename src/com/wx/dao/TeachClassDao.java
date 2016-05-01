package com.wx.dao;

import java.util.Map;

/**
 *   tClass��,teacher��course��Ӧ��ϵ��
 */
public interface TeachClassDao {
	
	/**
	 *   ��ѯ��ʦ��Ӧ�����пγ�
	 */
	public Map<String,String> getClassListByTid(String Tid);
	
	/**
	 *   ��ѯ�γ�����������
	 */
    public String getNameAndTotalbyClass(String tClassID);
    
    /**
	 *   Ϊ�γ̷�������
	 */
    public void setTutorID(String tClassID,String tutorID);
    
    /**
	 *   ��ѯ���̶�Ӧ�����пγ�
	 */
    public Map<String,String> getClassListByTutorID(String tutorID);
}
