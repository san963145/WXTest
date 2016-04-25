package com.wx.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.wx.dao.FeedBackDao;
import com.wx.dao.MyConnManager;
import com.wx.dao.StudentInfoDao;

public class FeedBackDaoImpl implements FeedBackDao{

	@Override
	public boolean add(long lessonID, String sid, String content, String time) {
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
			PreparedStatement ps=conn.prepareStatement("insert into FeedBack values(?,?,?,?)");
			ps.setLong(1, lessonID);
			ps.setString(2, sid);
			ps.setString(3, content);
			ps.setString(4, time);
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

	@Override
	public ArrayList<String> getListByLesson(long lessonID) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection(); 	
		try
		{
			PreparedStatement ps=conn.prepareStatement("select * from FeedBack where LESSONID=? order by TIME asc");
			ps.setLong(1, lessonID);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			ArrayList<String> result=new ArrayList<String>();
			StudentInfoDao dao=new StudentInfoDaoImpl();
			while(rs.next())
			{
				String sid=rs.getString("SID");
				String sName=dao.getNameById(sid);
				String content=rs.getString("CONTENT");
				result.add(sName+":"+content);
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
		  return new ArrayList<String>();
	}

}
