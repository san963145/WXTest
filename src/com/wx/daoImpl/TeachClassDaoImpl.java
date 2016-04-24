package com.wx.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.wx.dao.MyConnManager;
import com.wx.dao.TeachClassDao;

public class TeachClassDaoImpl implements TeachClassDao{

	@Override
	public Map<String,String> getClassListByTid(String Tid) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection(); 	
		try
		{
			PreparedStatement ps=conn.prepareStatement("select * from TeachClass where TID=?");
			ps.setString(1, Tid);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			String classID="null";
			String className="null";
			Map<String,String> map=new HashMap<String,String>();
			while(rs.next())
			{
				classID=rs.getString("TCLASSID");
				className=rs.getString("TCLASSNAME");
				map.put(classID, className);
			}	
			return  map;
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
		  return new HashMap<String,String>();
	}

	@Override
	public String getNameAndTotalbyClass(String tClassID) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection(); 	
		try
		{
			PreparedStatement ps=conn.prepareStatement("select * from TeachClass where TCLASSID=?");
			ps.setString(1, tClassID);
			ps.execute();
			ResultSet rs=ps.getResultSet();		
			String result="null";
			if(rs.next())
			{							
				String className=rs.getString("TCLASSNAME");
				Integer total=rs.getInt("TOTAL");
				result=className+"#"+total.toString();
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
		  return "null";
	}

	@Override
	public void setTutorID(String tClassID, String tutorID) {
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
			PreparedStatement ps=conn.prepareStatement("update TeachClass set TUTORID=? where TCLASSID=?");
			ps.setString(1, tutorID);
			ps.setString(2, tClassID);
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
	public Map<String, String> getClassListByTutorID(String tutorID) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection(); 	
		try
		{
			PreparedStatement ps=conn.prepareStatement("select * from TeachClass where TUTORID=?");
			ps.setString(1, tutorID);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			String classID="null";
			String className="null";
			Map<String,String> map=new HashMap<String,String>();
			while(rs.next())
			{
				classID=rs.getString("TCLASSID");
				className=rs.getString("TCLASSNAME");
				map.put(classID, className);
			}	
			return  map;
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
		  return new HashMap<String,String>();
	}

}
