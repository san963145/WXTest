package com.wx.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wx.dao.LessonSidListDao;
import com.wx.dao.MyConnManager;


public class LessonSidListDaoImpl implements LessonSidListDao{

	@Override
	public void add(long lessonID, String sid, String openID) {
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
			PreparedStatement ps=conn.prepareStatement("insert into LessonSidList values(?,?,?)");
			ps.setLong(1, lessonID);
			ps.setString(2, sid);
			ps.setString(3, openID);
			ps.execute();
			conn.commit();
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
		
	}

	@Override
	public String checkOpenID(long lessonID,String openID) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection();					
		try
		{
			PreparedStatement ps=conn.prepareStatement("select * from LessonSidList where LESSONID=? and OPENID=?");
			ps.setLong(1, lessonID);
			ps.setString(2, openID);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			String sid="null";
			if(rs.next())
			{
				sid=rs.getString("SID");
			}	
			return sid;
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
	public int getCountByLesson(long lessonID) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection(); 	
		try
		{
			PreparedStatement ps=conn.prepareStatement("select count(*) Count from LessonSidList where LESSONID=?");
			ps.setLong(1, lessonID);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			Integer result=0;
			if(rs.next())
			{
				result=rs.getInt("Count");
			}	
			return  result;
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
		  return 0;
	}

	@Override
	public boolean checkSid(long lessonID, String sid) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection();					
		try
		{
			PreparedStatement ps=conn.prepareStatement("select * from LessonSidList where LESSONID=? and SID=?");
			ps.setLong(1, lessonID);
			ps.setString(2, sid);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			if(rs.next())
			{
				return true;
			}	
		} catch(Exception e)
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
