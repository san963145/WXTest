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
			PreparedStatement ps=conn.prepareStatement("select * from TeacherInfo where TID=? and TPWD=?");
			ps.setString(1, tid);
			ps.setString(2, tpwd);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			String role="null";
			if(rs.next())
			{
				Integer r=rs.getInt("ROLE");
				role=r.toString();
			}	
			return  role;
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

	@Override
	public boolean checkTid(String tid) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection();					
		try
		{
			PreparedStatement ps=conn.prepareStatement("select * from TeacherInfo where TID=?");
			ps.setString(1, tid);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			String tName="null";
			if(rs.next())
			{
				tName=rs.getString("TNAME");
			}	
			if(!tName.equals("null"))
			{
				return true;
			}
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

	@Override
	public boolean add(String userID, String password, int role) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			PreparedStatement ps=conn.prepareStatement("insert into TeacherInfo values(?,?,?,?)");
			ps.setString(1, userID);
			ps.setString(2, userID);
			ps.setString(3, password);
			ps.setInt(4, role);
			ps.execute();
			conn.commit();
		    return true;
		}  catch(Exception e)
		  {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
