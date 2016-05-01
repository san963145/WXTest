package com.wx.dao;

/**
 * 
 * 学生基本信息表
 *
 */
public interface StudentInfoDao {
	
	/**
	 *  判断学号是否存在
	 */
    public boolean checkUserId(String userId);
    
    /**
	 *  根据学号查姓名
	 */
    public String getNameById(String userId);
    
    /**
	 *  添加一行记录
	 */
	public boolean insert(String userId,String userName,String spwd,String className,String sex,String openID);

}
