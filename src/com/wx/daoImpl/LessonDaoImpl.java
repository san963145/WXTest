package com.wx.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wx.dao.LessonDao;
import com.wx.dao.MyConnManager;


public class LessonDaoImpl implements LessonDao{

	@Override
	public void add(String classID) {
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
			PreparedStatement ps=conn.prepareStatement("insert into Lesson values(?,?,?)");
			long lessonID=System.currentTimeMillis();
			Date time=new Date(lessonID); 
			ps.setLong(1, lessonID);
			ps.setString(2, time.toString());
			ps.setString(3, classID);
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
	public long getMaxLessonID(String classID) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection(); 	
		try
		{
			PreparedStatement ps=conn.prepareStatement("select max(LESSONID) as LESSONID from Lesson where TCLASSID=?");
			ps.setString(1, classID);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			long maxLessonID=0;
			if(rs.next())
			{
				maxLessonID=rs.getLong("LESSONID");
			}	
			return  maxLessonID;
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
	public void delete(long lessonID) {
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
			PreparedStatement ps=conn.prepareStatement("delete from Lesson where LESSONID=?");
			ps.setLong(1, lessonID);
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

}
