package com.wx.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.wx.dao.LessonAbsenceSidListDao;
import com.wx.dao.MyConnManager;

public class LessonAbsenceSidListDaoImpl implements LessonAbsenceSidListDao{

	@Override
	public void add(long lessonID, String tClassID, String sid) {
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
			PreparedStatement ps=conn.prepareStatement("insert into LessonAbsenceSidList values(?,?,?)");
			ps.setLong(1, lessonID);
			ps.setString(2, tClassID);
			ps.setString(3, sid);
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
	public ArrayList<ArrayList<String>> getByClass(String tClassID) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection(); 	
		try
		{
			PreparedStatement ps=conn.prepareStatement("select l.SID Sid,s.SNAME SName,count(*) sCount from LessonAbsenceSidList l,StudentInfo s where l.SID=s.SID and l.TCLASSID=? group by l.SID order by sCount desc");
			ps.setString(1, tClassID);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			ArrayList<ArrayList<String>>result=new ArrayList<ArrayList<String>>();
			while(rs.next())
			{
				ArrayList<String> list=new ArrayList<String>();
				String sid=rs.getString("Sid");
				String sName=rs.getString("SName");
				Integer sCount=rs.getInt("sCount");
				list.add(sid);
				list.add(sName);
				list.add(sCount.toString());
				result.add(list);
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
			PreparedStatement ps=conn.prepareStatement("delete from LessonAbsenceSidList where LESSONID=?");
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
