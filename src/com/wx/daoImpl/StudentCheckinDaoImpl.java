package com.wx.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wx.dao.MyConnManager;
import com.wx.dao.StudentCheckinDao;

public class StudentCheckinDaoImpl implements StudentCheckinDao{

	@Override
	public List<String> getAbsenceListByLesson(long lessonID) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection(); 	
		try
		{
			PreparedStatement ps=conn.prepareStatement("select t.SID sid from Lesson l, TClassStudent t where l.TCLASSID=t.TCLASSID and l.LESSONID=?");
			ps.setLong(1, lessonID);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			ArrayList<String> sNameList=new ArrayList<String>();
			while(rs.next())
			{
				String s=rs.getString("sid");
				sNameList.add(s);
			}	
			return  sNameList;
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

	@Override
	public void add(String sid, long lessonID, String checkTime,int seq) {
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
			PreparedStatement ps=conn.prepareStatement("insert into StudentCheckin values(?,?,?,?,?)");
			long checkID=System.currentTimeMillis();
			ps.setLong(1, checkID);
			ps.setString(2, sid);
			ps.setLong(3, lessonID);
			ps.setString(4, checkTime);
			ps.setInt(5, seq);
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
	public int getMaxSeqBylessonID(long lessonID) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection(); 	
		try
		{
			PreparedStatement ps=conn.prepareStatement("select max(SEQ) seq from StudentCheckin where LESSONID=?");
			ps.setLong(1, lessonID);
			ps.execute();
			ResultSet rs=ps.getResultSet();			
			if(rs.next())
			{
				return rs.getInt("seq");
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
		  return 0;
	}

}
