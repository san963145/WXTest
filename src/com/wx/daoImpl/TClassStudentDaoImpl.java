package com.wx.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wx.dao.MyConnManager;
import com.wx.dao.TClassStudentDao;

public class TClassStudentDaoImpl implements TClassStudentDao{

	@Override
	public boolean checkUserId(String classID, String userID) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection();					
		try
		{
			PreparedStatement ps=conn.prepareStatement("select * from TClassStudent where TClASSID=? and SID=?");
			ps.setString(1, classID);
			ps.setString(2, userID);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			boolean result=false;
			if(rs.next())
			{
				result=true;
			}	
			return result;
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
		  return false;
	}

}
