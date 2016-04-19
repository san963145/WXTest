package com.wx.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wx.dao.MyConnManager;
import com.wx.dao.TeacherInfoDao;

public class TeacherInfoDaoImpl implements TeacherInfoDao{
	
	public String checkTeacherLogin(String tid,String tpwd)
	{	
		Connection conn=MyConnManager.getConnection(); 	
		try
		{
			PreparedStatement ps=conn.prepareStatement("select * from TeacherInfo where TID=? and TPWDMD5=?");
			ps.setString(1, tid);
			ps.setString(2, tpwd);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			String userName="null";
			String openID="null";
			if(rs.next())
			{
				userName=rs.getString("TName");
				openID=rs.getString("OpenID");
			}	
			return  userName+"#"+openID;
		}  catch(Exception e)
		  {
			e.printStackTrace();
		  }		
		   finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		  return "null";
	}

}
