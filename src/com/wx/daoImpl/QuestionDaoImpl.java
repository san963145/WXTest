package com.wx.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.wx.dao.MyConnManager;
import com.wx.dao.QuestionDao;



public class QuestionDaoImpl implements QuestionDao{

	@Override
	public boolean add(long qid, String tid, long lessonID, int number,
			String title, String answer, String content) {
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
			PreparedStatement ps=conn.prepareStatement("insert into Question values(?,?,?,?,?,?,?)");
			ps.setLong(1, qid);
			ps.setString(2, tid);
			ps.setLong(3, lessonID);
			ps.setInt(4, number);
			ps.setString(5, title);
			ps.setString(6, answer);
			ps.setString(7, content);				
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
	public int getMaxNumByLesson(long lessonID) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection(); 	
		try
		{
			PreparedStatement ps=conn.prepareStatement("select max(NUMBER) Max from Question where LESSONID=?");
			ps.setLong(1, lessonID);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			Integer result=0;
			if(rs.next())
			{
				result=rs.getInt("Max");
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
	public ArrayList<String> getTitlesByLesson(long lessonID) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection(); 	
		try
		{
			PreparedStatement ps=conn.prepareStatement("select * from Question where LESSONID=?");
			ps.setLong(1, lessonID);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			ArrayList<String>result=new ArrayList<String>();
			while(rs.next())
			{
				String title=rs.getString("TITLE");
				Integer num=rs.getInt("NUMBER");
				String number=num.toString();
				result.add(number+"."+title);
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
		  return new ArrayList<String>();
	}

	@Override
	public long getQidByNum(long lessonID, int number) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection(); 	
		try
		{
			PreparedStatement ps=conn.prepareStatement("select * from Question where LESSONID=? and NUMBER=?");
			ps.setLong(1, lessonID);
			ps.setInt(2, number);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			long result=0;
			if(rs.next())
			{
				result=rs.getLong("QID");
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
	public String getAnswerByQid(long qid) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection(); 	
		try
		{
			PreparedStatement ps=conn.prepareStatement("select * from Question where QID=?");
			ps.setLong(1, qid);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			String result="";
			if(rs.next())
			{
				result=rs.getString("RIGHTANSWER");
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
		  return "";
	}

	@Override
	public String getTitleByQid(long qid) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection(); 	
		try
		{
			PreparedStatement ps=conn.prepareStatement("select * from Question where QID=?");
			ps.setLong(1, qid);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			String title="";
			String content="";
			if(rs.next())
			{
				title=rs.getString("TITLE");
				content=rs.getString("CONTENT");
			}	
			return  title+"#"+content;
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
		  return "";
	}
    
}
