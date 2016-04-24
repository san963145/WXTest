package com.wx.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.wx.dao.LessonSignRecordDao;
import com.wx.dao.MyConnManager;

public class LessonSignRecordDaoImpl implements LessonSignRecordDao{

	@Override
	public void add(long lessonID, String tClassID,int signCount, int unsignCount) {
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
			PreparedStatement ps=conn.prepareStatement("insert into LessonSignRecord values(?,?,?,?)");
			ps.setLong(1, lessonID);
			ps.setString(2, tClassID);
			ps.setInt(3, signCount);
			ps.setInt(4, unsignCount);
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
	public ArrayList<ArrayList<String>> getLessonRecordByClass(String tClassID,int total) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection(); 	
		try
		{
			PreparedStatement ps=conn.prepareStatement("select * from LessonSignRecord where TCLASSID=? order by LESSONID asc");
			ps.setString(1, tClassID);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			ArrayList<ArrayList<String>>result=new ArrayList<ArrayList<String>>();
			Integer count=1;
			while(rs.next())
			{
				ArrayList<String> list=new ArrayList<String>();
				list.add(count.toString());
				Integer signCount=rs.getInt("SIGNCOUNT");
                Double signRate=(double)signCount/(double)total;
				list.add(signCount.toString());
				list.add(String.format("%.2f", signRate*100.0)+"%");
				result.add(list);
				count++;
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
		  return new ArrayList<ArrayList<String>>();
	}

	@Override
	public Double getAvgSignNumClass(String tClassID) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection(); 	
		try
		{
			PreparedStatement ps=conn.prepareStatement("select avg(SIGNCOUNT) Avg from LessonSignRecord where TCLASSID=?");
			ps.setString(1, tClassID);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			Double result=null;
			if(rs.next())
			{
				result=rs.getDouble("Avg");
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
		  return null;
	}

	@Override
	public String getLessonCount(String tClassID) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection(); 	
		try
		{
			PreparedStatement ps=conn.prepareStatement("select count(*) Count from LessonSignRecord where TCLASSID=?");
			ps.setString(1, tClassID);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			Integer result=0;
			if(rs.next())
			{
				result=rs.getInt("Count");
			}	
			return  result.toString();
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
		  return "0";
	}
    
}
